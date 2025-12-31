from cryptography.hazmat.primitives.asymmetric import rsa, padding
from cryptography.hazmat.primitives import serialization, hashes
from cryptography.hazmat.backends import default_backend
from Crypto.PublicKey import RSA
from Crypto.Cipher import PKCS1_v1_5
from Crypto.Signature import pkcs1_15
import hashlib

def basic_rsa_usage():
    private_key = rsa.generate_private_key(65537, 2048, default_backend())

    private_key2 = rsa.generate_private_key(65537, 2048)

    private_key3 = rsa.generate_private_key(65537, 2048, default_backend())
    public_key = private_key3.public_key()

    private_key4 = rsa.RSAPrivateKey.generate(65537, 2048, default_backend())

    public_key2 = rsa.RSAPublicKey.from_encoded(b"data", default_backend())

    private_key5 = rsa.generate_private_key(65537, 2048, default_backend())
    ciphertext = private_key5.encrypt(b"plaintext", padding.PKCS1v15())

    private_key6 = rsa.generate_private_key(65537, 2048, default_backend())
    plaintext = private_key6.decrypt(b"ciphertext", padding.PKCS1v15())

    public_key3 = private_key.public_key()
    ciphertext2 = public_key3.encrypt(b"plaintext", padding.PKCS1v15())

    private_key7 = rsa.generate_private_key(65537, 2048, default_backend())
    signature = private_key7.sign(b"data", padding.PKCS1v15(), hashlib.sha256)

    public_key4 = private_key.public_key()
    public_key4.verify(b"signature", b"data", padding.PKCS1v15(), hashlib.sha256)

    key_data = serialization.load_pem_private_key(b"data", None, default_backend())

    public_key_data = serialization.load_pem_public_key(b"data", default_backend())

    der_key = serialization.load_der_private_key(b"data", None, default_backend())

    der_public = serialization.load_der_public_key(b"data", default_backend())

    pad1 = padding.PKCS1v15()

    pad2 = padding.OAEP(mgf=padding.MGF1(algorithm=hashlib.sha256), algorithm=hashlib.sha256, label=None)

    pad3 = padding.PSS(mgf=padding.MGF1(algorithm=hashlib.sha256), salt_length=padding.PSS.MAX_LENGTH)

def pycryptodome_rsa():
    from Crypto.PublicKey import RSA
    key = RSA.generate(2048)

    key2 = RSA.importKey(b"key data")

    from Crypto.Cipher import PKCS1_v1_5
    cipher = PKCS1_v1_5.new(public_key)
    ciphertext = cipher.encrypt(b"plaintext")

    cipher2 = PKCS1_v1_5.new(private_key)
    plaintext = cipher2.decrypt(b"ciphertext", None)

    from Crypto.Signature import pkcs1_15
    hash_obj = hashlib.sha256(b"data")
    signer = pkcs1_15.new(private_key)
    signature = signer.sign(hash_obj)

    verifier = pkcs1_15.new(public_key)
    verifier.verify(hash_obj, signature)
