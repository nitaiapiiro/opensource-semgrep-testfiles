#!/usr/bin/env python
# License: GNU Lesser General Public License v2.1
# source: https://github.com/semgrep/semgrep-rules/blob/release/python/jwt/security/jwt-none-alg.py

import jwt


def bad1():
    # ruleid: python_jwt_rule-jwt-none-alg
    encoded = jwt.encode({'some': 'payload'}, None, algorithm='none')
    return encoded


def bad2(encoded):
    # ruleid: python_jwt_rule-jwt-none-alg
    jwt.decode(encoded, None, algorithms=['none'])
    return encoded


def ok(secret_key):
    # ok: python_jwt_rule-jwt-none-alg
    encoded = jwt.encode({'some': 'payload'}, secret_key, algorithm='HS256')
    return encoded
