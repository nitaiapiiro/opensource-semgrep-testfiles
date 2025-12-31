import hashlib
import hmac

def basic_sha256_usage():
    data = b"test data"

    hasher1 = hashlib.sha256(data)

    hasher2 = hashlib.sha256()

    hasher3 = hashlib.sha256()
    hasher3.update(data)
    hash3 = hasher3.digest()

    hasher4 = hashlib.sha256()
    hasher4.update(data)
    hash4 = hasher4.hexdigest()

    hash5 = hashlib.sha256(data).hexdigest()

    hash6 = hashlib.sha256(data).digest()

    hmac1 = hmac.new(b"key", b"message", hashlib.sha256)

    hmac2 = hmac.new(b"key", b"message", digestmod=hashlib.sha256)

    key1 = hashlib.pbkdf2_hmac("sha256", b"password", b"salt", 1000)
