#include <openssl/evp.h>

void whirlpool_evp_digest() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    unsigned char data[] = "test";
    unsigned char hash[64];
    EVP_DigestInit_ex(ctx, EVP_whirlpool(), NULL);
    EVP_Digest(data, 4, hash, NULL, EVP_whirlpool(), NULL);
    EVP_MD_CTX_free(ctx);
}
