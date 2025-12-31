import hashlib

def good_hashing_practices():
    data = b"test data"

    hasher1 = hashlib.sha256(data)

    hasher2 = hashlib.sha512()

    hasher3 = hashlib.sha3_256()
