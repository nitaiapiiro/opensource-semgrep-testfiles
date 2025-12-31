import ssl

def basic_tls_usage():
    context1 = ssl.PROTOCOL_SSLv2

    context2 = ssl.PROTOCOL_SSLv3

    context3 = ssl.PROTOCOL_TLSv1

    context4 = ssl.PROTOCOL_TLSv1_1

    context5 = ssl.PROTOCOL_TLSv1_2

    context6 = ssl.PROTOCOL_TLSv1_3

    context7 = ssl.create_default_context(ssl.PROTOCOL_TLSv1)

    context8 = ssl.create_default_context()
    context8.minimum_version = ssl.TLSVersion.TLSv1_2

    context9 = ssl.SSLContext(ssl.PROTOCOL_TLSv1_2)
