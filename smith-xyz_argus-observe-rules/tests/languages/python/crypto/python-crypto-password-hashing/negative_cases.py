import hashlib

def non_password_hashing():
    data = b"test data"
    hash1 = hashlib.md5(data)

    hash2 = hashlib.sha256(data)
