import hashlib

def basic_blake2_usage():
    data = b"test data"

    hasher1 = hashlib.blake2b(data)

    hasher2 = hashlib.blake2s(data)

    hasher3 = hashlib.blake2b(data, digest_size=32)

    hasher4 = hashlib.blake2s(data, digest_size=16)

    hasher5 = hashlib.blake2b(data)
    hash5 = hasher5.digest()

    hash6 = hashlib.blake2b(data).hexdigest()

    hash7 = hashlib.blake2s(data).hexdigest()
