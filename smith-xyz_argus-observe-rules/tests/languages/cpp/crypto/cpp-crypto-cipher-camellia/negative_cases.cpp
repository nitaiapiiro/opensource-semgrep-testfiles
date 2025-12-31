// Negative test cases - should NOT match

void not_camellia() {
    // AES, not Camellia
    EVP_CIPHER_CTX *ctx;
    unsigned char key[32];
    unsigned char iv[16];
    ctx = EVP_CIPHER_CTX_new();
    EVP_CipherInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void not_camellia_cryptopp() {
    // AES, not Camellia
    unsigned char key[32];
    CryptoPP::AES::Encryption aes(key, 32);
}

void not_camellia_botan() {
    // AES, not Camellia
    auto cipher = Botan::Cipher_Mode::create("AES-256/CBC", Botan::ENCRYPTION);
}
