import ssl
import requests
from urllib3.util.ssl_ import create_urllib3_context

def basic_tls_cipher_suites():
    context = ssl.create_default_context()
    context.set_ciphers("HIGH:!aNULL:!eNULL")

    context2 = ssl.SSLContext(ssl.PROTOCOL_TLSv1_2)
    context2.set_ciphers("HIGH:!aNULL:!eNULL")

    context3 = ssl.create_default_context()
    ciphers = context3.ciphers()

    context4 = ssl.SSLContext(ssl.PROTOCOL_TLSv1_2)
    ciphers2 = context4.ciphers()

    import ssl
    context5 = ssl.create_default_context()
    context5.set_ciphers("ECDHE-RSA-AES256-GCM-SHA384")

    import ssl
    context6 = ssl.SSLContext(ssl.PROTOCOL_TLSv1_2)
    context6.set_ciphers("ECDHE-RSA-AES256-GCM-SHA384")

    from urllib3.util.ssl_ import create_urllib3_context
    context7 = create_urllib3_context()
    context7.set_ciphers("HIGH:!aNULL:!eNULL")

    import requests
    session = requests.Session()
    adapter = requests.adapters.HTTPAdapter()
    session.mount("https://", adapter)
    adapter.init_poolmanager(ciphers="HIGH:!aNULL:!eNULL")
