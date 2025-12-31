from Crypto.Cipher import DES3
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.backends import default_backend
import cryptography.hazmat.primitives.ciphers.algorithms

def basic_3des_usage():
    key = b"123456789012345678901234"
    mode = DES3.MODE_ECB

    cipher1 = DES3.new(key, mode)

    cipher2 = DES3.new(key, mode)

    cipher3 = DES3.new(key, mode)
    ciphertext = cipher3.encrypt(b"plaintext")

    cipher4 = DES3.new(key, mode)
    plaintext = cipher4.decrypt(b"ciphertext")

    cipher5 = DES3.new(key, mode)
    ciphertext2 = cipher5.encrypt(b"plaintext")

    cipher6 = DES3.new(key, mode)
    plaintext2 = cipher6.decrypt(b"ciphertext")

    algo = algorithms.TripleDES(key)

    cipher_obj = Cipher(algorithms.TripleDES(key), modes.ECB(), default_backend())
