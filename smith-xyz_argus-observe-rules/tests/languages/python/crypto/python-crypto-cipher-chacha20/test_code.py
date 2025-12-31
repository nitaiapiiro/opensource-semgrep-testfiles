from Crypto.Cipher import ChaCha20, ChaCha20_Poly1305
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.primitives.ciphers.aead import ChaCha20Poly1305
from cryptography.hazmat.backends import default_backend

def basic_chacha20_usage():
    key = b"12345678901234567890123456789012"
    nonce = b"123456789012"

    cipher1 = ChaCha20.new(key, nonce)

    cipher2 = ChaCha20.new(key, nonce)
    ciphertext = cipher2.encrypt(b"plaintext")

    cipher3 = ChaCha20.new(key, nonce)
    plaintext = cipher3.decrypt(b"ciphertext")

    from Crypto.Cipher import ChaCha20
    cipher4 = ChaCha20.new(key, nonce)
    ciphertext2 = cipher4.encrypt(b"plaintext")

    from Crypto.Cipher import ChaCha20
    cipher5 = ChaCha20.new(key, nonce)
    plaintext2 = cipher5.decrypt(b"ciphertext")

    from Crypto.Cipher import ChaCha20_Poly1305
    cipher6 = ChaCha20_Poly1305.new(key, nonce)
    ciphertext3, tag = cipher6.encrypt_and_digest(b"plaintext")

    from Crypto.Cipher import ChaCha20_Poly1305
    cipher7 = ChaCha20_Poly1305.new(key, nonce)
    plaintext3 = cipher7.decrypt_and_verify(b"ciphertext", b"tag")

    algo = algorithms.ChaCha20(key)

    cipher_obj = Cipher(algorithms.ChaCha20(key), modes.ChaCha20Poly1305(nonce), default_backend())

    from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
    cipher8 = Cipher(algorithms.ChaCha20(key), modes.ChaCha20Poly1305(nonce), default_backend())

    from cryptography.hazmat.primitives.ciphers.aead import ChaCha20Poly1305
    cipher9 = ChaCha20Poly1305(key)
    ciphertext4 = cipher9.encrypt(nonce, b"plaintext", b"associated_data")

    from cryptography.hazmat.primitives.ciphers.aead import ChaCha20Poly1305
    cipher10 = ChaCha20Poly1305(key)
    plaintext4 = cipher10.decrypt(nonce, b"ciphertext", b"associated_data")
