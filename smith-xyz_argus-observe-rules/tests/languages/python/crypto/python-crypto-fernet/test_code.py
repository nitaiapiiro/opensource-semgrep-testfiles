from cryptography.fernet import Fernet, MultiFernet

def basic_fernet_usage():
    key = b"1234567890123456789012345678901234567890123456789012345678901234="
    fernet = Fernet(key)

    fernet2 = Fernet(key)
    token = fernet2.encrypt(b"data")

    fernet3 = Fernet(key)
    data = fernet3.decrypt(b"token")

    from cryptography.fernet import Fernet
    fernet4 = Fernet(key)
    token2 = fernet4.encrypt(b"data")

    from cryptography.fernet import Fernet
    fernet5 = Fernet(key)
    data2 = fernet5.decrypt(b"token")

    from cryptography.fernet import Fernet
    key2 = Fernet.generate_key()
    fernet6 = Fernet(key2)

    from cryptography.fernet import Fernet
    fernet7 = Fernet(key)
    result = fernet7.encrypt(b"data")

    from cryptography.fernet import Fernet
    fernet8 = Fernet(key)
    result2 = fernet8.decrypt(b"token")

    from cryptography.fernet import MultiFernet
    fernet9 = MultiFernet([fernet, fernet2])
    token3 = fernet9.encrypt(b"data")

    from cryptography.fernet import MultiFernet
    fernet10 = MultiFernet([fernet, fernet2])
    data3 = fernet10.decrypt(b"token")
