# License: Commons Clause License Condition v1.0[LGPL-2.1-only]
# https://github.com/semgrep/semgrep-rules/blob/release/python/lang/security/audit/network/http-not-https-connection.py

import urllib3 as ur3
import urllib3 

# ruleid: python_crypto_rule-HTTPConnectionPool
pool = ur3.HTTPConnectionPool("example.com")
print(pool)

# ruleid: python_crypto_rule-HTTPConnectionPool
pool2 = ur3.connectionpool.HTTPConnectionPool("example.com")
print(pool2)

# ruleid: python_crypto_rule-HTTPConnectionPool
pool3 = urllib3.connectionpool.HTTPConnectionPool("example.com")
print(pool3)

# ok: python_crypto_rule-HTTPConnectionPool
spool = ur3.connectionpool.HTTPSConnectionPool("example.com")
print(spool)

# ok: python_crypto_rule-HTTPConnectionPool
spool = urllib3.connectionpool.HTTPSConnectionPool("example.com")
print(spool)