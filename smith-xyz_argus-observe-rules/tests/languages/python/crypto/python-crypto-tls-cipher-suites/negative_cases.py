import ssl

def non_cipher_suites():
    context = ssl.create_default_context()
    context.check_hostname = True
