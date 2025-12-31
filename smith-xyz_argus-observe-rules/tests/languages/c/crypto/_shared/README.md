# Cross-Library Test Scenarios

This directory contains test files that demonstrate cross-library compatibility - ensuring that rules for different cryptographic libraries don't interfere with each other.

## cross_library_test.c

This file tests mixing APIs from different cryptographic libraries:

- OpenSSL + libgcrypt
- OpenSSL + Windows CryptoAPI
- OpenSSL + Windows CNG
- All libraries together

**Purpose:** Verify that each rule only matches its own library's APIs and doesn't produce false positives when multiple libraries are used in the same file.

**Usage:** This file can be tested manually against all C crypto rules to ensure no cross-library interference.
