from Crypto.Cipher import Salsa20

def basic_salsa20_usage():
    key = b"12345678901234567890123456789012"
    nonce = b"12345678"

    cipher1 = Salsa20.new(key, nonce)

    cipher2 = Salsa20.new(key, nonce)
    ciphertext = cipher2.encrypt(b"plaintext")

    cipher3 = Salsa20.new(key, nonce)
    plaintext = cipher3.decrypt(b"ciphertext")

    from Crypto.Cipher import Salsa20
    cipher4 = Salsa20.new(key, nonce)
    ciphertext2 = cipher4.encrypt(b"plaintext")

    from Crypto.Cipher import Salsa20
    cipher5 = Salsa20.new(key, nonce)
    plaintext2 = cipher5.decrypt(b"ciphertext")
