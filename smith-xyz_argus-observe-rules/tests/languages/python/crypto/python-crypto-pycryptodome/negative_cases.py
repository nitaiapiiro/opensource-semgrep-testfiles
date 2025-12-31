import hashlib

def non_pycryptodome():
    data = b"test data"
    hash1 = hashlib.md5(data)

    hash2 = hashlib.sha256(data)
