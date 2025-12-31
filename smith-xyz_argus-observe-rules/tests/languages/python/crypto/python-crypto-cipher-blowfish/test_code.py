from Crypto.Cipher import Blowfish
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.backends import default_backend

def basic_blowfish_usage():
    key = b"1234567890123456"
    mode = Blowfish.MODE_ECB

    cipher1 = Blowfish.new(key, mode)

    cipher2 = Blowfish.new(key, Blowfish.MODE_ECB)

    iv = b"12345678"
    cipher3 = Blowfish.new(key, Blowfish.MODE_CBC, iv)

    cipher4 = Blowfish.new(key, Blowfish.MODE_CFB, iv)

    cipher5 = Blowfish.new(key, Blowfish.MODE_OFB, iv)

    counter = b"12345678"
    cipher6 = Blowfish.new(key, Blowfish.MODE_CTR, counter)

    cipher7 = Blowfish.new(key, mode)
    ciphertext = cipher7.encrypt(b"plaintext")

    cipher8 = Blowfish.new(key, mode)
    plaintext = cipher8.decrypt(b"ciphertext")

    from Crypto.Cipher import Blowfish
    cipher9 = Blowfish.new(key, mode)
    ciphertext2 = cipher9.encrypt(b"plaintext")

    from Crypto.Cipher import Blowfish
    cipher10 = Blowfish.new(key, mode)
    plaintext2 = cipher10.decrypt(b"ciphertext")

    algo = algorithms.Blowfish(key)

    cipher_obj = Cipher(algorithms.Blowfish(key), mode, default_backend())

    from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
    cipher11 = Cipher(algorithms.Blowfish(key), modes.ECB(), default_backend())
