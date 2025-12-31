# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

require 'digest'
class Bad_md5
    def bad_md5_code()
        # ruleid: ruby_crypto_rule-WeakHashesSHA1
        sha = Digest::SHA1.hexdigest 'abc'
        # ruleid: ruby_crypto_rule-WeakHashesSHA1
        sha = Digest::SHA1.new
        # ruleid: ruby_crypto_rule-WeakHashesSHA1
        sha = Digest::SHA1.base64digest 'abc'
        # ruleid: ruby_crypto_rule-WeakHashesSHA1
        sha = Digest::SHA1.digest 'abc'

        # ruleid: ruby_crypto_rule-WeakHashesSHA1
        digest = OpenSSL::Digest::SHA1.new
        # ruleid: ruby_crypto_rule-WeakHashesSHA1
        digest = OpenSSL::Digest::SHA1.hexdigest 'abc'
        # ruleid: ruby_crypto_rule-WeakHashesSHA1
        digest = OpenSSL::Digest::SHA1.new
        # ruleid: ruby_crypto_rule-WeakHashesSHA1
        digest = OpenSSL::Digest::SHA1.base64digest 'abc'
        # ruleid: ruby_crypto_rule-WeakHashesSHA1
        digest = OpenSSL::Digest::SHA1.digest 'abc'
        # ruleid: ruby_crypto_rule-WeakHashesSHA1
        OpenSSL::HMAC.hexdigest("sha1", key, data)
        # ok: ruby_crypto_rule-WeakHashesSHA1
        OpenSSL::HMAC.hexdigest("SHA256", key, data)
        # ok: ruby_crypto_rule-WeakHashesSHA1
        digest = OpenSSL::Digest::SHA256.new
        # ok: ruby_crypto_rule-WeakHashesSHA1
        digest = OpenSSL::Digest::SHA256.hexdigest 'abc'
    end
end