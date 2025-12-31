from Crypto.Cipher import AES

def hardcoded_key_usage():
    key = "secretkey123456"
    cipher = AES.new(key, AES.MODE_ECB)

    cipher2 = AES.new("secretkey123456", AES.MODE_ECB)

    key2 = b"secretkey123456"
    cipher3 = AES.new(key2, AES.MODE_ECB)

    cipher4 = AES.new(b"secretkey123456", AES.MODE_ECB)

    KEY = "secretkey123456"
    cipher5 = AES.new(KEY, AES.MODE_ECB)
