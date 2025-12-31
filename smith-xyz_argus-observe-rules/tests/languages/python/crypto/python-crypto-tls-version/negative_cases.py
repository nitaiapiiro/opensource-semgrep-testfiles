import hashlib

def non_tls_usage():
    data = b"test data"
    hash1 = hashlib.md5(data)
