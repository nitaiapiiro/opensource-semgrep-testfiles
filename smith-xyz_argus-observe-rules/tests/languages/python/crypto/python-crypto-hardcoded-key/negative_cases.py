import os
import secrets
from Crypto.Cipher import AES

def good_key_usage():
    key = os.urandom(16)
    cipher = AES.new(key, AES.MODE_ECB)

    key2 = secrets.token_bytes(16)
    cipher2 = AES.new(key2, AES.MODE_ECB)
