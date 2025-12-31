from Crypto.Cipher import Camellia
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.backends import default_backend

def basic_camellia_usage():
    key = b"1234567890123456"
    mode = Camellia.MODE_ECB

    cipher1 = Camellia.new(key, mode)

    cipher2 = Camellia.new(key, Camellia.MODE_ECB)

    iv = b"1234567890123456"
    cipher3 = Camellia.new(key, Camellia.MODE_CBC, iv)

    cipher4 = Camellia.new(key, Camellia.MODE_CFB, iv)

    cipher5 = Camellia.new(key, Camellia.MODE_OFB, iv)

    counter = b"1234567890123456"
    cipher6 = Camellia.new(key, Camellia.MODE_CTR, counter)

    nonce = b"123456789012"
    cipher7 = Camellia.new(key, Camellia.MODE_GCM, nonce)

    cipher8 = Camellia.new(key, mode)
    ciphertext = cipher8.encrypt(b"plaintext")

    cipher9 = Camellia.new(key, mode)
    plaintext = cipher9.decrypt(b"ciphertext")

    from Crypto.Cipher import Camellia
    cipher10 = Camellia.new(key, mode)
    ciphertext2 = cipher10.encrypt(b"plaintext")

    from Crypto.Cipher import Camellia
    cipher11 = Camellia.new(key, mode)
    plaintext2 = cipher11.decrypt(b"ciphertext")

    algo = algorithms.Camellia(key)

    cipher_obj = Cipher(algorithms.Camellia(key), mode, default_backend())

    from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
    cipher12 = Cipher(algorithms.Camellia(key), modes.ECB(), default_backend())
