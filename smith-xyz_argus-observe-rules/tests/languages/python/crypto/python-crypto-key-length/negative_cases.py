import hashlib

def non_key_length():
    data = b"test data"
    hash1 = hashlib.md5(data)
