import hashlib

def non_sha512_usage():
    data = b"test data"

    hash1 = hashlib.md5(data)

    hash2 = hashlib.sha1(data)
