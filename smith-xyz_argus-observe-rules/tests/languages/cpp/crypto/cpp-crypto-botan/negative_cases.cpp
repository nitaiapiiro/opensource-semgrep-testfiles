// Negative test cases - should NOT match

void not_botan() {
    // Crypto++, not Botan
    CryptoPP::SHA256 hash;
}

void not_botan_namespace() {
    // Different namespace
    namespace other {
        class HashFunction {};
    }
}
