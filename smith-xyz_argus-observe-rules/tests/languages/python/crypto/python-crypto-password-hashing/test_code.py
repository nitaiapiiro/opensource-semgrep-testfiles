import bcrypt
import hashlib

def basic_password_hashing():
    password = b"password"
    salt = bcrypt.gensalt(12)

    hash1 = bcrypt.hashpw(password, salt)

    check1 = bcrypt.checkpw(password, hash1)

    salt2 = bcrypt.gensalt(10)

    pass

    hash4 = hashlib.pbkdf2_hmac("sha256", password, salt, 1000)

    hash5 = bcrypt.hashpw(password, bcrypt.gensalt())
    check2 = bcrypt.checkpw(password, hash5)
