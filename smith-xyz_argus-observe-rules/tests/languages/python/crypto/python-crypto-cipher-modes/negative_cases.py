import hashlib

def non_cipher_modes():
    data = b"test data"
    hash1 = hashlib.md5(data)
