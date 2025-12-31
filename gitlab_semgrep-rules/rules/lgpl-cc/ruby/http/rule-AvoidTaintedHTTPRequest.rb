# License: Commons Clause License Condition v1.0[LGPL-2.1-only]
require 'net/http'

def foo

  url = params[:url]
  # ruleid: ruby_http_rule-AvoidTaintedHTTPRequest
  Net::HTTP.get(url, "/index.html")

  # ruleid: ruby_http_rule-AvoidTaintedHTTPRequest
  Net::HTTP.get_response(params[:url])

  uri = URI(params[:url])
  # ruleid: ruby_http_rule-AvoidTaintedHTTPRequest
  Net::HTTP.post(uri)

  # ruleid: ruby_http_rule-AvoidTaintedHTTPRequest
  Net::HTTP.post_form(URI(params[:url]))

  uri = URI(params[:server])
  # ruleid: ruby_http_rule-AvoidTaintedHTTPRequest
  req = Net::HTTP::Get.new uri

  # ruleid: ruby_http_rule-AvoidTaintedHTTPRequest
  Net::HTTP.start(uri.host, uri.port) do |http|
    # ruleid: ruby_http_rule-AvoidTaintedHTTPRequest
    req = Net::HTTP::Get.new uri
    resp = http.request request
  end

  # ruleid: ruby_http_rule-AvoidTaintedHTTPRequest
  Net::HTTP::Get.new(params[:url])

  # ruleid: ruby_http_rule-AvoidTaintedHTTPRequest
  Net::HTTP::Post.new(URI(params[:url]))


  # ok: ruby_http_rule-AvoidTaintedHTTPRequest
  Net::HTTP.get("example.com", "/index.html")

  uri = URI("example.com/index.html")
  # ok: ruby_http_rule-AvoidTaintedHTTPRequest
  Net::HTTP::Get.new(uri)

end