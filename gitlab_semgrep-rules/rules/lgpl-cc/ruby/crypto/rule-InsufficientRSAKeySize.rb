# License: Commons Clause License Condition v1.0[LGPL-2.1-only]

class Test
    $key = 512
    $pass1 = 2048

    def initialize(key = nil, iv = nil)
        @key2 = 512
        @pass2 = 2048
        # ruleid: ruby_crypto_rule-InsufficientRSAKeySize
        OpenSSL::PKey::RSA.new(@key2)
        # ruleid: ruby_crypto_rule-InsufficientRSAKeySize
        OpenSSL::PKey::RSA.new 512
        bad
        bad1
        ok
    end

    def bad
        # ruleid: ruby_crypto_rule-InsufficientRSAKeySize
        key = OpenSSL::PKey::RSA.new($key)
    end

    def bad1
        # ruleid: ruby_crypto_rule-InsufficientRSAKeySize
        key = OpenSSL::PKey::RSA.new(@key2)
    end


    def ok
        # ok: ruby_crypto_rule-InsufficientRSAKeySize
        key = OpenSSL::PKey::RSA.new($pass1)
        # ok: ruby_crypto_rule-InsufficientRSAKeySize
        key = OpenSSL::PKey::RSA.new(@pass2)
        # ok: ruby_crypto_rule-InsufficientRSAKeySize
        key = OpenSSL::PKey::RSA.new(2048)
    end
end