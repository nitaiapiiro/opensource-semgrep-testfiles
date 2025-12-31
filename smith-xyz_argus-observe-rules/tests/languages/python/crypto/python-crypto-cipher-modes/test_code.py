from Crypto.Cipher import AES
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.backends import default_backend

def basic_cipher_modes():
    key = b"1234567890123456"

    mode1 = AES.MODE_ECB

    mode2 = AES.MODE_CBC

    mode3 = AES.MODE_CFB

    mode4 = AES.MODE_OFB

    mode5 = AES.MODE_CTR

    mode6 = AES.MODE_GCM

    mode7 = AES.MODE_CCM

    mode8 = AES.MODE_XTS

    cipher1 = AES.new(key, AES.MODE_ECB)

    iv = b"1234567890123456"
    cipher2 = AES.new(key, AES.MODE_CBC, iv)

    cipher3 = AES.new(key, AES.MODE_CFB, iv)

    cipher4 = AES.new(key, AES.MODE_OFB, iv)

    counter = b"1234567890123456"
    cipher5 = AES.new(key, AES.MODE_CTR, counter)

    nonce = b"123456789012"
    cipher6 = AES.new(key, AES.MODE_GCM, nonce)

    cipher7 = AES.new(key, AES.MODE_CCM, nonce)

    tweak = b"1234567890123456"
    cipher8 = AES.new(key, AES.MODE_XTS, tweak)

def cryptography_library_modes():
    from cryptography.hazmat.primitives.ciphers import modes
    mode1 = modes.ECB()

    iv = b"1234567890123456"
    mode2 = modes.CBC(iv)

    mode3 = modes.CFB(iv)

    mode4 = modes.OFB(iv)

    counter = b"1234567890123456"
    mode5 = modes.CTR(counter)

    nonce = b"123456789012"
    mode6 = modes.GCM(nonce)

    mode7 = modes.CCM(nonce, tag_length=16)

    tweak = b"1234567890123456"
    mode8 = modes.XTS(tweak)

    from cryptography.hazmat.primitives.ciphers import Cipher, algorithms
    key = b"1234567890123456"
    cipher1 = Cipher(algorithms.AES(key), modes.ECB(), default_backend())

    cipher2 = Cipher(algorithms.AES(key), modes.CBC(iv), default_backend())

    cipher3 = Cipher(algorithms.AES(key), modes.GCM(nonce), default_backend())
