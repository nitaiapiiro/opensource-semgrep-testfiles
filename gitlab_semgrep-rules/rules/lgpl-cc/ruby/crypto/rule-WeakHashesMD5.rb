# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

require 'digest'
class Bad_md5
    def bad_md5_code()
        # ruleid: ruby_crypto_rule-WeakHashesMD5
        md5 = Digest::MD5.hexdigest 'abc'
        # ruleid: ruby_crypto_rule-WeakHashesMD5
        md5 = Digest::MD5.new
        # ruleid: ruby_crypto_rule-WeakHashesMD5
        md5 = Digest::MD5.base64digest 'abc'
        # ruleid: ruby_crypto_rule-WeakHashesMD5
        md5 = Digest::MD5.digest 'abc'

        # ruleid: ruby_crypto_rule-WeakHashesMD5
        digest = OpenSSL::Digest::MD5.new
        # ruleid: ruby_crypto_rule-WeakHashesMD5
        digest = OpenSSL::Digest::MD5.hexdigest 'abc'
        # ruleid: ruby_crypto_rule-WeakHashesMD5
        digest = OpenSSL::Digest::MD5.new
        # ruleid: ruby_crypto_rule-WeakHashesMD5
        digest = OpenSSL::Digest::MD5.base64digest 'abc'
        # ruleid: ruby_crypto_rule-WeakHashesMD5
        digest = OpenSSL::Digest::MD5.digest 'abc'
    end
end