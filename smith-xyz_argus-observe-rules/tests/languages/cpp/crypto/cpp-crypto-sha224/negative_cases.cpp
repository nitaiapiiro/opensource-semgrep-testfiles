// Negative test cases - should NOT match

void not_sha224() {
    // SHA256, not SHA224
    SHA256_CTX ctx;
    SHA256_Init(&ctx);
}

void not_sha224_evp() {
    // SHA256, not SHA224
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    EVP_DigestInit_ex(ctx, EVP_sha256(), NULL);
    EVP_MD_CTX_free(ctx);
}

void not_sha224_botan() {
    // SHA256, not SHA224
    auto hash = Botan::HashFunction::create("SHA-256");
}
