from Crypto.Cipher import AES

def non_blowfish_usage():
    key = b"1234567890123456"
    cipher = AES.new(key, AES.MODE_ECB)
