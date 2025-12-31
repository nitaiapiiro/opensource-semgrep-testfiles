import ssl
import requests

def good_certificate_validation():
    context = ssl.create_default_context()
    context.check_hostname = True
    context.verify_mode = ssl.CERT_REQUIRED

    requests.get("https://example.com", verify=True)
