import hashlib

def non_blake2_usage():
    data = b"test data"

    hash1 = hashlib.md5(data)

    hash2 = hashlib.sha1(data)

    hash3 = hashlib.sha256(data)
