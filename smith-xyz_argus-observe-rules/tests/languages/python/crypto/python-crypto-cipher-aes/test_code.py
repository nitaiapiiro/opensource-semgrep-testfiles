from Crypto.Cipher import AES
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.backends import default_backend
import cryptography.hazmat.primitives.ciphers.algorithms

def basic_aes_usage():
    key = b"1234567890123456"
    mode = AES.MODE_ECB

    cipher1 = AES.new(key, mode)

    cipher2 = AES.new(key, AES.MODE_ECB)

    iv = b"1234567890123456"
    cipher3 = AES.new(key, AES.MODE_CBC, iv)

    nonce = b"123456789012"
    cipher4 = AES.new(key, AES.MODE_GCM, nonce)

    cipher5 = AES.new(key, mode)
    ciphertext = cipher5.encrypt(b"plaintext")

    cipher6 = AES.new(key, mode)
    plaintext = cipher6.decrypt(b"ciphertext")

    algo = algorithms.AES(key)

    cipher_obj = Cipher(algorithms.AES(key), modes.ECB(), default_backend())

def pycryptodome_aes():
    from Crypto.Cipher import AES
    key = b"1234567890123456"
    mode = AES.MODE_CBC
    iv = b"1234567890123456"

    cipher = AES.new(key, mode)
    ciphertext = cipher.encrypt(b"plaintext")

    cipher2 = AES.new(key, mode)
    plaintext = cipher2.decrypt(b"ciphertext")

def cryptography_library_aes():
    from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
    from cryptography.hazmat.backends import default_backend

    key = b"1234567890123456"
    iv = b"1234567890123456"
    mode = modes.CBC(iv)

    cipher = Cipher(algorithms.AES(key), mode, default_backend())
    encryptor = cipher.encryptor()
    ciphertext = encryptor.update(b"plaintext") + encryptor.finalize()

    cipher2 = Cipher(algorithms.AES(key), mode, default_backend())
    decryptor = cipher2.decryptor()
    plaintext = decryptor.update(b"ciphertext") + decryptor.finalize()
