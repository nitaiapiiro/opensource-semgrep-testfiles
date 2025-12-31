from cryptography.hazmat.primitives.asymmetric import ec
from cryptography.hazmat.primitives import hashes
from cryptography.hazmat.backends import default_backend
from ecdsa import SigningKey, VerifyingKey, curves
import hashlib

def basic_ecdsa_usage():
    private_key = ec.generate_private_key(ec.SECP256R1(), default_backend())

    private_key2 = ec.generate_private_key(ec.SECP256R1(), default_backend())
    public_key = private_key2.public_key()

    curve1 = ec.SECP256R1()

    curve2 = ec.SECP384R1()

    curve3 = ec.SECP521R1()

    curve4 = ec.SECP256K1()

    curve5 = ec.BrainpoolP256R1()

    curve6 = ec.BrainpoolP384R1()

    curve7 = ec.BrainpoolP512R1()

    private_key3 = ec.generate_private_key(ec.SECP256R1(), default_backend())
    signature = private_key3.sign(b"data", ec.ECDSA(hashlib.sha256()))

    public_key2 = private_key.public_key()
    public_key2.verify(signature, b"data", ec.ECDSA(hashlib.sha256()))

def ecdsa_library_usage():
    from ecdsa import SigningKey
    key = SigningKey.generate(curve=curves.SECP256k1)

    key2 = SigningKey.from_string(b"key data", curve=curves.SECP256k1)

    key3 = SigningKey.generate(curve=curves.SECP256k1)
    signature = key3.sign(b"data")

    from ecdsa import VerifyingKey
    key4 = VerifyingKey.from_string(b"key data", curve=curves.SECP256k1)
    key4.verify(signature, b"data")

    curve1 = curves.SECP256k1

    curve2 = curves.NIST256p

    curve3 = curves.NIST384p

    curve4 = curves.NIST521p
