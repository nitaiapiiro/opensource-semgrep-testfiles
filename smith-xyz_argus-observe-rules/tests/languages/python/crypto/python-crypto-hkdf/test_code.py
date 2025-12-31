from Crypto.Protocol.KDF import HKDF
from cryptography.hazmat.primitives.kdf.hkdf import HKDF as CryptoHKDF, HKDFExpand, HKDFExtract
from cryptography.hazmat.primitives import hashes
from cryptography.hazmat.backends import default_backend
import hashlib

def basic_hkdf_usage():
    length = 32
    salt = b"salt"
    info = b"info"
    hash_algo = hashlib.sha256

    hkdf1 = HKDF(length, salt, info, hash_algo, default_backend())

    hkdf2 = HKDF(length, salt, info, hash_algo, default_backend())
    key1 = hkdf2.derive(b"key_material")

    hkdf3 = HKDF(length, salt, info, hash_algo, default_backend())
    key2 = hkdf3.derive(b"key_material")

    prk1 = HKDF.extract(salt, b"input_material")

    key3 = HKDF.expand(b"prk", info, length)

    from Crypto.Protocol.KDF import HKDF
    key4 = HKDF(length, salt, info, hash_algo)

    from Crypto.Protocol.KDF import HKDF
    key5 = HKDF(length, salt, info, hash_algo, default_backend())

    from cryptography.hazmat.primitives.kdf.hkdf import HKDF
    algorithm = hashes.SHA256()
    hkdf4 = HKDF(algorithm, length, salt, info, default_backend())
    key6 = hkdf4.derive(b"key_material")

    from cryptography.hazmat.primitives.kdf.hkdf import HKDF
    hkdf5 = HKDF(algorithm, length, salt, info, default_backend())
    key7 = hkdf5.derive(b"key_material")

    from cryptography.hazmat.primitives.kdf.hkdf import HKDFExpand
    hkdf6 = HKDFExpand(algorithm, length, info, default_backend())
    key8 = hkdf6.derive(b"prk")

    from cryptography.hazmat.primitives.kdf.hkdf import HKDFExtract
    hkdf7 = HKDFExtract(algorithm, salt, default_backend())
    prk2 = hkdf7.extract(b"input_material")
