#include <openssl/md4.h>
#include <openssl/evp.h>

void md4_init_update_final() {
    MD4_CTX ctx;
    unsigned char data[] = "test";
    unsigned char hash[MD4_DIGEST_LENGTH];
    MD4_Init(&ctx);
    MD4_Update(&ctx, data, 4);
    MD4_Final(hash, &ctx);
}

void md4_evp_digest() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    unsigned char data[] = "test";
    unsigned char hash[MD4_DIGEST_LENGTH];
    EVP_DigestInit_ex(ctx, EVP_md4(), NULL);
    EVP_Digest(data, 4, hash, NULL, EVP_md4(), NULL);
    EVP_MD_CTX_free(ctx);
}
