import hashlib

def non_iv_nonce_usage():
    data = b"test data"
    hash1 = hashlib.md5(data)
