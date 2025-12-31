from Crypto.Cipher import DES, DES3

def basic_des_usage():
    key = b"12345678"
    mode = DES.MODE_ECB

    cipher1 = DES.new(key, mode)

    cipher2 = DES3.new(key, mode)

    cipher3 = DES.new(key, mode)
    ciphertext = cipher3.encrypt(b"plaintext")

    cipher4 = DES3.new(key, mode)
    plaintext = cipher4.decrypt(b"ciphertext")

def cryptography_library_des():
    from cryptography.hazmat.primitives.ciphers.algorithms import TripleDES
    key = b"1234567890123456"
    algo = TripleDES(key)
