from Crypto.Cipher import AES, DES, DES3, ARC4, ChaCha20, Salsa20
from Crypto.PublicKey import RSA, DSA, ECC
from Crypto.Hash import MD5, SHA1, SHA256, SHA512, SHA3_256, SHA3_512, BLAKE2b
from Crypto.Signature import pkcs1_15, DSS, eddsa
from Crypto.Random import get_random_bytes
from Crypto.Protocol.KDF import PBKDF2, HKDF, scrypt
from Crypto.Util.Padding import pad, unpad
import Crypto.Cipher
import Crypto.PublicKey
import Crypto.Hash
import Crypto.Signature

def basic_pycryptodome_imports():
    from Crypto.Cipher import AES

    from Crypto.PublicKey import RSA

    from Crypto.Hash import SHA256

    from Crypto.Signature import pkcs1_15

    from Crypto.Random import get_random_bytes

    from Crypto.Protocol import KDF

    from Crypto.Util import Padding

    from Crypto.Cipher import AES, DES, DES3, ARC4, ChaCha20, Salsa20

    from Crypto.PublicKey import RSA, DSA, ECC

    from Crypto.Hash import MD5, SHA1, SHA256, SHA512, SHA3_256, SHA3_512, BLAKE2b

    from Crypto.Signature import pkcs1_15, DSS, eddsa

    from Crypto.Random import get_random_bytes

    from Crypto.Protocol.KDF import PBKDF2, HKDF, scrypt

    from Crypto.Util.Padding import pad, unpad

    import Crypto.Cipher

    import Crypto.PublicKey

    import Crypto.Hash

    import Crypto.Signature
