// Negative test cases - should NOT match

void not_sha384() {
    // SHA512, not SHA384
    SHA512_CTX ctx;
    SHA512_Init(&ctx);
}

void not_sha384_evp() {
    // SHA512, not SHA384
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    EVP_DigestInit_ex(ctx, EVP_sha512(), NULL);
    EVP_MD_CTX_free(ctx);
}

void not_sha384_botan() {
    // SHA512, not SHA384
    auto hash = Botan::HashFunction::create("SHA-512");
}
