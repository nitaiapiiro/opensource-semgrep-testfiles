import hmac
import hashlib
import base64

def basic_hmac_usage():
    key = b"secret key"
    message = b"test message"

    hmac1 = hmac.new(key, message, hashlib.md5)

    hmac2 = hmac.new(key, message, digestmod=hashlib.sha1)

    hmac3 = hmac.new(key, message, hashlib.sha256)

    hmac4 = hmac.new(key, message, hashlib.sha512)

    hmac5 = hmac.new(key, message, hashlib.sha3_256)

    hmac6 = hmac.new(key, message, hashlib.sha3_512)

    hmac7 = hmac.new(key, message, hashlib.blake2b)

    hmac8 = hmac.new(key, message, digestmod=hashlib.md5)

    hmac9 = hmac.new(key, message, digestmod=hashlib.sha1)

    hmac10 = hmac.new(key, message, digestmod=hashlib.sha256)

    hmac11 = hmac.new(key, message, digestmod=hashlib.sha512)

    hmac12 = hmac.new(key, message, digestmod=hashlib.sha3_256)

    hmac13 = hmac.new(key, message, digestmod=hashlib.sha3_512)

    hmac14 = hmac.new(key, message, digestmod=hashlib.blake2b)

def hmac_with_update():
    key = b"secret key"
    message = b"test message"

    hmac_obj = hmac.new(key, message, hashlib.sha256)
    hmac_obj.update(b"more data")
    result = hmac_obj.digest()

    hmac_obj2 = hmac.new(key, message, digestmod=hashlib.sha512)
    hmac_obj2.update(b"more data")
    result2 = hmac_obj2.hexdigest()

def hmac_compare():
    key = b"secret key"
    message = b"test message"

    hmac_obj = hmac.new(key, message, hashlib.sha256)
    expected = b"expected hash"
    hmac.compare_digest(hmac_obj.digest(), expected)

    hmac_obj2 = hmac.new(key, message, digestmod=hashlib.sha512)
    encoded = base64.b64encode(hmac_obj2.digest())

    hmac_obj3 = hmac.new(key, message, digestmod=hashlib.sha256)
    hex_result = hmac_obj3.hexdigest()
