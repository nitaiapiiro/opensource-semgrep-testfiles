from cryptography.hazmat.primitives.asymmetric import rsa, ec, padding, ed25519, ed448
from cryptography.hazmat.primitives import hashes
from cryptography.hazmat.backends import default_backend
from Crypto.Signature import pkcs1_15, DSS
from ecdsa import SigningKey, VerifyingKey, curves
import hashlib

def basic_digital_signatures():
    private_key = rsa.generate_private_key(65537, 2048, default_backend())
    signature = private_key.sign(b"data", padding.PKCS1v15(), hashlib.sha256())

    public_key = private_key.public_key()
    public_key.verify(signature, b"data", padding.PKCS1v15(), hashlib.sha256())

    private_key2 = ec.generate_private_key(ec.SECP256R1(), default_backend())
    signature2 = private_key2.sign(b"data", ec.ECDSA(hashlib.sha256()))

    public_key2 = private_key2.public_key()
    public_key2.verify(signature2, b"data", ec.ECDSA(hashlib.sha256()))

    from Crypto.Signature import pkcs1_15
    hash_obj = hashlib.sha256(b"data")
    signer = pkcs1_15.new(private_key)
    signature3 = signer.sign(hash_obj)

    verifier = pkcs1_15.new(public_key)
    verifier.verify(hash_obj, signature3)

    from Crypto.Signature import DSS
    signer2 = DSS.new(private_key, "fips-186-3")
    signature4 = signer2.sign(hash_obj)

    verifier2 = DSS.new(public_key, "fips-186-3")
    verifier2.verify(hash_obj, signature4)

    from ecdsa import SigningKey
    key = SigningKey.generate(curve=curves.SECP256k1)
    signature5 = key.sign(b"data")

    from ecdsa import VerifyingKey
    key2 = VerifyingKey.from_string(b"key data", curve=curves.SECP256k1)
    key2.verify(signature5, b"data")

    from cryptography.hazmat.primitives.asymmetric import padding
    signature6 = private_key.sign(b"data", padding.PKCS1v15(), hashlib.sha256())

    public_key3 = private_key.public_key()
    public_key3.verify(signature6, b"data", padding.PKCS1v15(), hashlib.sha256())

    signature7 = private_key.sign(b"data", padding.PSS(mgf=padding.MGF1(algorithm=hashlib.sha256), salt_length=padding.PSS.MAX_LENGTH), hashlib.sha256())

    public_key4 = private_key.public_key()
    public_key4.verify(signature7, b"data", padding.PSS(mgf=padding.MGF1(algorithm=hashlib.sha256), salt_length=padding.PSS.MAX_LENGTH), hashlib.sha256())

    from cryptography.hazmat.primitives.asymmetric import ed25519
    private_key3 = ed25519.Ed25519PrivateKey.generate()
    signature8 = private_key3.sign(b"data")

    public_key5 = private_key3.public_key()
    public_key5.verify(signature8, b"data")

    from cryptography.hazmat.primitives.asymmetric import ed448
    private_key4 = ed448.Ed448PrivateKey.generate()
    signature9 = private_key4.sign(b"data")

    public_key6 = private_key4.public_key()
    public_key6.verify(signature9, b"data")
