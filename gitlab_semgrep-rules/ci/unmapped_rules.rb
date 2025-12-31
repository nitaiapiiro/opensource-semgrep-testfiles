#!/usr/bin/env ruby

require 'yaml'
require 'gitlab'

# UnmappedRules is a model that includes the logic to detect and act on
# unmapped rules
module UnmappedRules
  # UnmappedRules::Commenter is used to issue feedback comments in MR context
  class Commenter
    attr_accessor :project_id, :branch_name, :token, :api, :commit_sha,
                  :lastupdate, :unmapped, :mr_iid, :client, :note_id,
                  :note_prefix

    def initialize(unmapped = [],
                   project_id = ENV['CI_PROJECT_ID'],
                   branch_name = ENV['CI_COMMIT_REF_NAME'],
                   token = ENV['UNMAPPED_RULES_TOKEN'],
                   api = ENV['CI_API_V4_URL'],
                   commit_sha = ENV['CI_COMMIT_SHA'])

      @project_id = project_id || ''
      @branch_name = branch_name || ''
      @token = token || ''
      @api = api || ''
      @commit_sha = commit_sha || ''
      @unmapped = unmapped
      @note_id = -1

      # used to identify the note we would like to update
      @note_prefix = '<!-- nid:1241hx0dfa -->'

      @title = '## :robot: Rule Mapping Check'
      @client = nil
      @mr_iid = -1

      setup_client!
      infer_mriid!

      raise 'Client initialization did not work' if @client.nil?
      raise 'MR iid not identifiable' if @mr_iid.negative?
    end

    def setup_client!
      puts "setup client"
      @client = Gitlab.client(
        endpoint: api,
        private_token: token
      )
    end

    def infer_mriid!
      puts "infer mr iid"
      raise 'Client not properly initialized' if @client.nil?

      merge_requests = @client.merge_requests(@project_id, state: 'opened', source_branch: @branch_name) || []
      raise "No merge request found for source branch: #{branch_name}" if merge_requests.empty?

      mr = merge_requests.first
      @mr_iid = mr.iid
      puts "MR iid: #{@mr_iid} title: #{mr.title}"
    end

    def retrieve_note
      notes = []
      begin
        notes = @client.merge_request_notes(@project_id, @mr_iid)
                       .find { |note| note.body.start_with?(@note_prefix) } || []
      rescue StandardError => e
        puts "Unable to retrieve posts: #{e}"
        return false
      end

      return false if notes.empty?

      @notes_id = notes.id

      true
    end

    def comment_updates(comment_content)
      if retrieve_note
        puts 'Updating comment'
        @client.edit_merge_request_note(
          @project_id,
          @mr_iid,
          @notes_id,
          body: comment_content
        )
      else
        puts 'Creating new comment'
        @client.create_merge_request_discussion(
          @project_id,
          @mr_iid,
          body: comment_content
        )
      end
    end

    def post_update
      if @unmapped.any?
        comment_updates([@note_prefix, @title, ' ',
                         ":warning: #{@unmapped.size} unmapped rules detected", ' ',
                         "Consider adding the rules listed below to their corresponding mapping
                         files so that they are getting included in the next GitLab SAST release.
                         The mapping files are located in in the mappings/ directory. See the [README](https://gitlab.com/gitlab-org/security-products/sast-rules#mappings) for more information.",
                         ' ',
                         @unmapped.map { |item| "1. `#{item.gsub('`', '')}`" }].flatten.join("\n"))
      else
        comment_updates([@note_prefix, @title, ' ',
                         ':white_check_mark: No unmapped rules detected'].flatten.join("\n"))
      end
    end
  end
  # class Commenter end

  def self.in_ci_context?
    ci_ctx = true
    %w[CI_COMMIT_REF_NAME CI_API_V4_URL CI_PROJECT_ID UNMAPPED_RULES_TOKEN].each do |item|
      next if ENV.keys.include?(item)

      puts "Environment variables #{item} not set"
      ci_ctx = false
    end
    ci_ctx
  end

  def self.run
    mapped = {}
    rule_files = Dir.glob('**/*/rule-*.yml')
                    .reject { |f| f.start_with?('qa/fixtures') }
                    .map { |f| [f, true] }.to_h

    Dir.glob('./mappings/*.yml').each do |file|
      mappings = YAML.safe_load(File.read(file))
      base = File.basename(file, '.yml')
      mappings[base]['mappings'].each do |mp|
        mp['rules'].each do |rule|
          mapped["#{rule['path']}.yml"] = true
        end
      end
    end

    unmapped = rule_files.keys - mapped.keys

    puts 'Unmapped rules detected'
    puts unmapped.map{ |f| "* #{f}" }.join("\n")
    puts ''

    return unmapped.empty? unless in_ci_context?

    begin
      commenter = Commenter.new(unmapped)
      commenter.post_update
    rescue StandardError => e
      puts "Unable to comment on MR: #{e}"
      return false
    end

    unmapped.empty?
  end
end

if UnmappedRules.run
  exit(0)
else
  exit(-1)
end
