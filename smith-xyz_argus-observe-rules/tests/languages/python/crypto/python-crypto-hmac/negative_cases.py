import hashlib

def non_hmac_usage():
    data = b"test data"

    hash1 = hashlib.sha256(data)

    hash2 = hashlib.md5(data)

    hash3 = hashlib.sha512(data)

def non_crypto_function():
    message = "This function doesn't use HMAC"
    print(message)
