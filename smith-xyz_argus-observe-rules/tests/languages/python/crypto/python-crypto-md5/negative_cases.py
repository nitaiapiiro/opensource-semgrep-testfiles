import hashlib
import hmac

def good_hashing_practices():
    data = b"test data"

    hasher1 = hashlib.sha256(data)

    hasher2 = hashlib.sha512()

    hasher3 = hashlib.sha3_256()
    hasher3.update(data)
    hash3 = hasher3.digest()

    hash4 = hashlib.blake2b(data).hexdigest()

    hmac1 = hmac.new(b"key", b"message", hashlib.sha256)

    hmac2 = hmac.new(b"key", b"message", digestmod=hashlib.sha512)

def non_crypto_function():
    message = "This function doesn't use any hashing"
    print(message)

def confusing_names():
    md5_looking = "not actually md5"
    md5_var = 12345
