def non_3des_usage():
    from Crypto.Cipher import AES
    key = b"1234567890123456"
    cipher = AES.new(key, AES.MODE_ECB)
