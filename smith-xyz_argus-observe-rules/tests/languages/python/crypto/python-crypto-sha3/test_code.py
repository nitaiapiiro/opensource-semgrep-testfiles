import hashlib

def basic_sha3_usage():
    data = b"test data"

    hasher1 = hashlib.sha3_224(data)

    hasher2 = hashlib.sha3_256(data)

    hasher3 = hashlib.sha3_384(data)

    hasher4 = hashlib.sha3_512(data)

    hasher5 = hashlib.sha3_224()

    hasher6 = hashlib.sha3_256()

    hasher7 = hashlib.sha3_384()

    hasher8 = hashlib.sha3_512()

    hasher9 = hashlib.sha3_256()
    hasher9.update(data)
    hash9 = hasher9.digest()

    hash10 = hashlib.sha3_256(data).hexdigest()

    hash11 = hashlib.sha3_512(data).hexdigest()
