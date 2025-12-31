from Crypto.Cipher import Twofish

def basic_twofish_usage():
    key = b"1234567890123456"
    mode = Twofish.MODE_ECB

    cipher1 = Twofish.new(key, mode)

    cipher2 = Twofish.new(key, Twofish.MODE_ECB)

    iv = b"1234567890123456"
    cipher3 = Twofish.new(key, Twofish.MODE_CBC, iv)

    cipher4 = Twofish.new(key, Twofish.MODE_CFB, iv)

    cipher5 = Twofish.new(key, Twofish.MODE_OFB, iv)

    counter = b"1234567890123456"
    cipher6 = Twofish.new(key, Twofish.MODE_CTR, counter)

    cipher7 = Twofish.new(key, mode)
    ciphertext = cipher7.encrypt(b"plaintext")

    cipher8 = Twofish.new(key, mode)
    plaintext = cipher8.decrypt(b"ciphertext")

    from Crypto.Cipher import Twofish
    cipher9 = Twofish.new(key, mode)
    ciphertext2 = cipher9.encrypt(b"plaintext")

    from Crypto.Cipher import Twofish
    cipher10 = Twofish.new(key, mode)
    plaintext2 = cipher10.decrypt(b"ciphertext")
