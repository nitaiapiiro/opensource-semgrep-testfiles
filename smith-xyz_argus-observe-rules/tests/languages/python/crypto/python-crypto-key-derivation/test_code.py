import hashlib

def basic_key_derivation():
    password = b"password"
    salt = b"salt"

    key1 = hashlib.pbkdf2_hmac("sha256", password, salt, 1000)

    key2 = hashlib.pbkdf2_hmac("sha256", password, salt, 1000, dklen=32)

    key3 = hashlib.pbkdf2_hmac("sha256", password, salt, 1000)
    key3

    pass
