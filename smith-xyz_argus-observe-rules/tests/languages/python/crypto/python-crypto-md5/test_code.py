import hashlib
import hmac

def basic_md5_usage():
    data = b"test data"

    hasher1 = hashlib.md5(data)

    hasher2 = hashlib.md5()

    hasher3 = hashlib.md5()
    hasher3.update(data)
    hash3 = hasher3.digest()

    hasher4 = hashlib.md5()
    hasher4.update(data)
    hash4 = hasher4.hexdigest()

    hash5 = hashlib.md5(data).hexdigest()

    hash6 = hashlib.md5(data).digest()

    hmac1 = hmac.new(b"key", b"message", hashlib.md5)

    hmac2 = hmac.new(b"key", b"message", digestmod=hashlib.md5)

    key1 = hashlib.pbkdf2_hmac("md5", b"password", b"salt", 1000)

    key2 = hashlib.pbkdf2_hmac("md5", b"password", b"salt", 1000, dklen=32)
