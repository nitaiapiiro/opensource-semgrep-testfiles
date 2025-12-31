from cryptography.hazmat.primitives.asymmetric import rsa
from cryptography.hazmat.backends import default_backend
from Crypto.PublicKey import RSA
from Crypto.Cipher import AES
from cryptography.hazmat.primitives.ciphers.algorithms import AES as CryptoAES
from cryptography.hazmat.primitives.ciphers import Cipher, modes
import os

def basic_key_length():
    private_key = rsa.generate_private_key(65537, 2048, default_backend())

    private_key2 = rsa.generate_private_key(65537, 4096, default_backend())

    from Crypto.PublicKey import RSA
    key = RSA.generate(2048)

    key2 = RSA.generate(4096)

    from Crypto.Cipher import AES
    key3 = os.urandom(16)
    cipher = AES.new(key3, AES.MODE_ECB)

    key4 = os.urandom(32)
    cipher2 = AES.new(key4, AES.MODE_ECB)

    from cryptography.hazmat.primitives.ciphers.algorithms import AES
    key5 = os.urandom(16)
    cipher3 = Cipher(AES(key5), modes.ECB(), default_backend())

    key6 = os.urandom(32)
    cipher4 = Cipher(AES(key6), modes.ECB(), default_backend())
