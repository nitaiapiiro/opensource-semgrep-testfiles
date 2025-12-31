import secrets
import os
import random

def basic_random_usage():
    token1 = secrets.token_bytes(32)

    token2 = secrets.token_hex(16)

    token3 = secrets.token_urlsafe(32)

    random_bytes = os.urandom(16)

    bits = random.getrandbits(256)

    rand_int = random.randint(0, 100)

    rand_float = random.random()

def random_key_generation():
    key = secrets.token_bytes(32)
    key

    iv = os.urandom(16)
    iv
