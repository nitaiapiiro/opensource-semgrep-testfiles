require 'yaml'
require 'json'
require 'fileutils'

class Deploy
  SEMVER_PATTERN = /[0-9][0-9]*\.[0-9][0-9]*\.[0-9][0-9]*/

  attr_reader :mappings_path, :rule_base_path, :dest, :version

  def initialize(mappings_path, rule_base_path, dest, version)
    @mappings_path = mappings_path
    @rule_base_path = rule_base_path
    @dest = dest
    # remove leading v from version value.
    # Example: v1.2.3 becomes 1.2.3
    @version = version[SEMVER_PATTERN]
  end

  def run
    if version.nil?
      puts "please provide version tag"
      exit 1
    end

    unless version.match?(/[0-9.]{1,15}/)
      puts 'only semver version strings allowed'
      exit 1
    end

    FileUtils.mkdir_p(["#{dest}/lgpl", "#{dest}/lgpl-cc", "#{dest}/gitlab"])
    %w[lgpl lgpl-cc gitlab].each { |license_dir| FileUtils.cp("#{rule_base_path}/rules/#{license_dir}/LICENSE", "#{dest}/#{license_dir}/") }

    Dir.glob("#{mappings_path}/*.yml").each do |mapping_file|
      prefix = File.basename(mapping_file, '.yml')

      dict = YAML.safe_load(File.read(mapping_file))

      native_id = dict[prefix]['native_id']
      analyzer = dict[prefix].fetch('native_analyzer', prefix)

      rulez = {}
      id2rules = {}
      rule2ids = {}
      rule2ruleid = {}
      rule_file_paths = []
      rule_file_path_2_primary_id = {}
      rulepath2ruleid = {}

      dict[prefix]['mappings'].each do |mapping|
        id = mapping['id']
        id2rules[id] = [] unless id2rules.key?(id)
        mapping['rules'].each do |rule|
          rule_path = rule['path']
          rule2ids[rule_path] = [] unless rule2ids.key?(rule_path)
          rule2ids[rule_path] << id

          # rule2ruleid is used to lookup mappings[].rules[].id by mappings[].rules[].path
          # so that it can be used to interpoliate the native ID variable $NID
          rule2ruleid[rule_path] = [] unless rule2ruleid.key?(rule_path)
          rule2ruleid[rule_path] << rule['id']

          rulepath2ruleid[rule_path] = rule['id'] if rule.key? 'id'
          id2rules[id] << rule_path
          rule_file_paths << rule_path
          rule_file_path_2_primary_id[rule['path']] = rule['primary_id'] if rule.key? 'primary_id'
        end
      end

      rule_file_paths.sort!.uniq!

      rule_file_paths.each do |rule_file_path|
        rule_read_path = File.join(File.expand_path(rule_base_path), "#{rule_file_path}.yml")
        rule_file = YAML.safe_load(File.read(rule_read_path))

        # there's only ever one rule in a rule file
        rule = rule_file['rules'].first

        ids = rule2ids[rule_file_path]
        rule_ids = rule2ruleid[rule_file_path]

        # generate a unique rule hash that makes it possible to map results
        # back to the original analyzer -- note that we have n:m mappings multiple
        # native ids can be mapped to a collection of semgrep rules and vice versa
        # every rule gets coordinates: original_rule_id-array index number
        suffix = ids.map { |id| "#{id}-#{id2rules[id].find_index(rule_file_path) + 1}" }.join('.')

        # set the rule ID
        newid = generate_rule_id(
          analyzer, suffix, rule_file_path, rule_file_path_2_primary_id, rulepath2ruleid)

        rule['id'] = newid
        rulez[newid] = rule
        secondary_ids = []
        ids.uniq.each do |id|
          secondary_ids << {
            'name' => native_id['name'].gsub('$ID', id),
            'type' => native_id['type'].gsub('$ID', id),
            'value' => native_id['value'].gsub('$ID', id)
          }

          # Substitute $NID in the native ID with the mapping[].rules[].id
          if rule_ids[0]
            rid = rule_ids[0].split('.').last
            secondary_ids.last['name'].gsub!('$NID', rid)
            secondary_ids.last['type'].gsub!('$NID', rid)
            secondary_ids.last['value'].gsub!('$NID', rid)
          end
        end

        primary_id = ids.one? ? newid.delete_suffix('-1') : newid
        primary_id = newid if ['flawfinder', 'gosec', 'security_code_scan', 'find_sec_bugs'].include? prefix
        primary_id = rule_file_path_2_primary_id[rule_file_path] if rule_file_path_2_primary_id.key? rule_file_path
        rulez[newid]['metadata'].merge!('primary_identifier' => primary_id)
        rulez[newid]['metadata'].merge!('secondary_identifiers' => secondary_ids)
      end

      outdict = { 'rules' => rulez.values }
      formatted = YAML.dump(outdict)

      license_dir = (prefix == 'phpcs_security_audit' || prefix == 'brakeman'? 'lgpl-cc' : '')
      dest_path = File.join(dest, license_dir, "#{prefix}.yml")

      dest_path = "#{dest}/gitlab/#{prefix}.yml" if prefix =~ /gitlab_ee.+/
      dest_path = "#{dest}/lgpl-cc/#{prefix}.yml" if prefix =~ /gitlab_lgpl_cc.+/
      dest_path = "#{dest}/lgpl/#{prefix}.yml" if prefix =~ /nodejs_scan|find_sec_bugs_kotlin|mobsf/

      puts("writing #{dest_path}")

      File.open(dest_path, 'w') do |file|
        file.puts('# yamllint disable')
        file.puts('# License: GNU Lesser General Public License v3.0') if prefix =~ /nodejs_scan|find_sec_bugs_kotlin|mobsf/
        file.puts('# License: The GitLab Enterprise Edition (EE) license (the “EE License”)') if prefix =~ /gitlab_ee.+/
        file.puts('# License: Commons Clause License Condition v1.0[LGPL-2.1-only]') if prefix == 'phpcs_security_audit' || prefix == 'brakeman' || prefix =~ /gitlab_lgpl_cc.+/
        file.puts("# rule-set version: #{version}")
        file.puts('# yamllint enable')
        file.write(formatted)
      end
    end
  end

  def generate_rule_id(analyzer, suffix, rule_file_path, rule_file_path_2_primary_id, rulepath2ruleid)
    if rulepath2ruleid.key? rule_file_path
      return rulepath2ruleid[rule_file_path]
    end

    if rule_file_path_2_primary_id.key? rule_file_path
      return rule_file_path_2_primary_id[rule_file_path]
    end

    "#{analyzer}.#{suffix}"
  end
end
