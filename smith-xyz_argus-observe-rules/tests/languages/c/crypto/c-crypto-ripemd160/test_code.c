#include <openssl/ripemd.h>
#include <openssl/evp.h>

void ripemd160_init_update_final() {
    RIPEMD160_CTX ctx;
    unsigned char data[] = "test";
    unsigned char hash[RIPEMD160_DIGEST_LENGTH];
    RIPEMD160_Init(&ctx);
    RIPEMD160_Update(&ctx, data, 4);
    RIPEMD160_Final(hash, &ctx);
}

void ripemd160_evp_digest() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    unsigned char data[] = "test";
    unsigned char hash[RIPEMD160_DIGEST_LENGTH];
    EVP_DigestInit_ex(ctx, EVP_ripemd160(), NULL);
    EVP_Digest(data, 4, hash, NULL, EVP_ripemd160(), NULL);
    EVP_MD_CTX_free(ctx);
}
