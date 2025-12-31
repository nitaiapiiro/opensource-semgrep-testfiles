from cryptography.hazmat.primitives.asymmetric import rsa

def non_dh_usage():
    private_key = rsa.generate_private_key(65537, 2048, default_backend())
