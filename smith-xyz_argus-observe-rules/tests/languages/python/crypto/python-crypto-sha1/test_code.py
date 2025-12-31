import hashlib
import hmac

def basic_sha1_usage():
    data = b"test data"

    hasher1 = hashlib.sha1(data)

    hasher2 = hashlib.sha1()

    hasher3 = hashlib.sha1()
    hasher3.update(data)
    hash3 = hasher3.digest()

    hasher4 = hashlib.sha1()
    hasher4.update(data)
    hash4 = hasher4.hexdigest()

    hash5 = hashlib.sha1(data).hexdigest()

    hash6 = hashlib.sha1(data).digest()

    hmac1 = hmac.new(b"key", b"message", hashlib.sha1)

    hmac2 = hmac.new(b"key", b"message", digestmod=hashlib.sha1)

    key1 = hashlib.pbkdf2_hmac("sha1", b"password", b"salt", 1000)
