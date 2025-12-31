import hashlib

def non_random_usage():
    data = b"test data"
    hash1 = hashlib.md5(data)

    key = b"static key"
