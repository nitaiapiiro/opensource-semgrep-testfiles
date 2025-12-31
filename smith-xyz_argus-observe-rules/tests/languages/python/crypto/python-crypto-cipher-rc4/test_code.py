from Crypto.Cipher import ARC4

def basic_rc4_usage():
    key = b"secret key"

    cipher1 = ARC4.new(key)

    cipher2 = ARC4.new(key)
    ciphertext = cipher2.encrypt(b"plaintext")

    cipher3 = ARC4.new(key)
    plaintext = cipher3.decrypt(b"ciphertext")

    cipher4 = ARC4.new(key)
    result = cipher4.encrypt(b"plaintext")

    cipher5 = ARC4.new(key)
    result2 = cipher5.decrypt(b"ciphertext")
