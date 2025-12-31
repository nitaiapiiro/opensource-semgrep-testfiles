# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

# frozen_string_literal: true

require "abstract_unit"
require "controller/fake_controllers"

class ActionPackAssertionsController < ActionController::Base
  def nothing() head :ok end

  # ok: ruby_xss_rule-AvoidRenderText
  def hello_xml_world() render template: "test/hello_xml_world"; end

  def assign_this
    @howdy = "ho"
    render inline: "Mr. Henke"
  end

  def render_based_on_parameters
    # ok: ruby_xss_rule-AvoidRenderText
    render plain: "Mr. #{params[:name]}"
  end

  def render_url
    # ok: ruby_xss_rule-AvoidRenderText
    render html: "<div>#{url_for(action: 'flash_me', only_path: true)}</div>"
  end

  def render_text_with_custom_content_type
    # ok: ruby_xss_rule-AvoidRenderText
    render body: "Hello!", content_type: Mime[:rss]
  end

  def session_stuffing
    session["xmas"] = "turkey"
    # ruleid: ruby_xss_rule-AvoidRenderText
    render text: "ho ho ho"
  end

  def raise_exception_on_get
    raise "get" if request.get?
    # ruleid: ruby_xss_rule-AvoidRenderText
    render text: "request method: #{request.env['REQUEST_METHOD']}"
  end

  def raise_exception_on_post
    raise "post" if request.post?
    # ok: ruby_xss_rule-AvoidRenderText
    render plain: "request method: #{request.env['REQUEST_METHOD']}"
  end

  def render_file_absolute_path
    # ok: ruby_xss_rule-AvoidRenderText
    render file: File.expand_path("../../README.rdoc", __dir__)
  end

  def render_file_relative_path
    # ok: ruby_xss_rule-AvoidRenderText
    render file: "README.rdoc"
  end
end

# Used to test that assert_response includes the exception message
# in the failure message when an action raises and assert_response
# is expecting something other than an error.
class AssertResponseWithUnexpectedErrorController < ActionController::Base
  def index
    raise "FAIL"
  end

  def show
    # ok: ruby_xss_rule-AvoidRenderText
    render plain: "Boom", status: 500
  end
end