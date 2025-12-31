// Negative test cases - should NOT match

void not_cryptopp() {
    // Botan, not Crypto++
    Botan::SHA_256 hash;
}

void not_cryptopp_namespace() {
    // Different namespace
    namespace other {
        class AES {};
    }
}
