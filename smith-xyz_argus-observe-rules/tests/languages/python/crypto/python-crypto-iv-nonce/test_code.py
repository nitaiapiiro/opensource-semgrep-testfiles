import os
import secrets
from Crypto.Cipher import AES
from cryptography.hazmat.primitives.ciphers import modes
from cryptography.hazmat.backends import default_backend

def basic_iv_nonce_usage():
    iv1 = os.urandom(16)

    nonce1 = os.urandom(12)

    iv2 = secrets.token_bytes(16)

    nonce2 = secrets.token_bytes(12)

    key = b"1234567890123456"
    iv3 = os.urandom(16)
    cipher = AES.new(key, AES.MODE_CBC, iv3)

    nonce3 = os.urandom(12)
    cipher2 = AES.new(key, AES.MODE_GCM, nonce3)

    iv4 = secrets.token_bytes(16)
    cipher3 = AES.new(key, AES.MODE_CBC, iv4)

    nonce4 = secrets.token_bytes(12)
    cipher4 = AES.new(key, AES.MODE_GCM, nonce4)

    from cryptography.hazmat.primitives.ciphers import modes
    iv5 = os.urandom(16)
    mode = modes.CBC(iv5)

    nonce5 = os.urandom(12)
    mode2 = modes.GCM(nonce5)

    from Crypto.Cipher import AES
    iv6 = os.urandom(16)
    cipher5 = AES.new(key, AES.MODE_CBC, iv6)

    nonce6 = os.urandom(12)
    cipher6 = AES.new(key, AES.MODE_GCM, nonce6)

    iv7 = b"static_iv_123456"
    cipher7 = AES.new(key, AES.MODE_CBC, iv7)

    nonce7 = b"static_nonce_12"
    cipher8 = AES.new(key, AES.MODE_GCM, nonce7)

    iv8 = "static_iv_string"
    cipher9 = AES.new(key, AES.MODE_CBC, iv8.encode())

    iv9 = [0x00] * 16
    cipher10 = AES.new(key, AES.MODE_CBC, bytes(iv9))

    iv10 = b"\x00" * 16
    cipher11 = AES.new(key, AES.MODE_CBC, iv10)
