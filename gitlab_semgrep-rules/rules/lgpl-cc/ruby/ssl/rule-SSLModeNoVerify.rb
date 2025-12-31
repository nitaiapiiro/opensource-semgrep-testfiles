# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

require "net/https"
require "uri"

uri = URI.parse("https://ssl-site.com/")
http = Net::HTTP.new(uri.host, uri.port)
http.use_ssl = true
# ruleid:ruby_ssl_rule-SSLModeNoVerify
http.verify_mode = OpenSSL::SSL::VERIFY_NONE

request = Net::HTTP::Get.new(uri.request_uri)

http.verify_mode = OpenSSL::SSL::VERIFY_PEER

response = http.request(request)

# ok:ruby_ssl_rule-SSLModeNoVerify
http.verify_mode = OpenSSL::SSL::VERIFY_PEER
request = Net::HTTP::Get.new(uri.request_uri)
response = http.request(request)