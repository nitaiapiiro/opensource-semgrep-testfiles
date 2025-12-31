from cryptography.hazmat.primitives.asymmetric import dh
from cryptography.hazmat.primitives import hashes
from cryptography.hazmat.backends import default_backend

def basic_dh_usage():
    generator = 2
    key_size = 2048

    parameters1 = dh.generate_parameters(generator, key_size, default_backend())

    parameters2 = dh.generate_parameters(generator, key_size, default_backend())
    private_key1 = dh.generate_private_key(parameters2, default_backend())

    private_key2 = dh.generate_private_key(parameters1, default_backend())

    private_key3 = dh.generate_private_key(parameters1, default_backend())
    public_key1 = private_key3.public_key()

    private_key4 = dh.generate_private_key(parameters1, default_backend())
    public_key2 = private_key1.public_key()
    shared_key1 = private_key4.exchange(public_key2)

    private_key5 = dh.generate_private_key(parameters1, default_backend())
    shared_key2 = dh.derive_shared_key(private_key5, public_key1)

    p = 1234567890123456789012345678901234567890123456789012345678901234
    g = 2
    param_nums1 = dh.DHParameterNumbers(p, g)

    private_value = 1234567890123456789012345678901234567890123456789012345678901234
    y = 1234567890123456789012345678901234567890123456789012345678901234
    public_nums1 = dh.DHPublicNumbers(y, param_nums1)
    priv_nums1 = dh.DHPrivateNumbers(private_value, public_nums1)

    from cryptography.hazmat.primitives.asymmetric import dh
    parameters3 = dh.generate_parameters(generator, key_size, default_backend())

    from cryptography.hazmat.primitives.asymmetric import dh
    private_key6 = dh.generate_private_key(parameters3, default_backend())

    from cryptography.hazmat.primitives.asymmetric import dh
    private_key7 = dh.generate_private_key(parameters3, default_backend())
    public_key3 = private_key7.public_key()
    shared_key3 = private_key7.exchange(public_key3)

    from cryptography.hazmat.primitives.asymmetric import dh
    param_nums2 = dh.DHParameterNumbers(p, g)
    parameters4 = param_nums2.parameters(default_backend())

    from cryptography.hazmat.primitives.asymmetric import dh
    priv_nums2 = dh.DHPrivateNumbers(private_value, public_nums1)
    private_key8 = priv_nums2.private_key(default_backend())
