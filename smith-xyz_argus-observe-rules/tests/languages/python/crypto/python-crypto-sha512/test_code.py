import hashlib
import hmac

def basic_sha512_usage():
    data = b"test data"

    hasher1 = hashlib.sha512(data)

    hasher2 = hashlib.sha512()

    hasher3 = hashlib.sha512()
    hasher3.update(data)
    hash3 = hasher3.digest()

    hasher4 = hashlib.sha512()
    hasher4.update(data)
    hash4 = hasher4.hexdigest()

    hash5 = hashlib.sha512(data).hexdigest()

    hash6 = hashlib.sha512(data).digest()

    hmac1 = hmac.new(b"key", b"message", hashlib.sha512)

    hmac2 = hmac.new(b"key", b"message", digestmod=hashlib.sha512)

    key1 = hashlib.pbkdf2_hmac("sha512", b"password", b"salt", 1000)
