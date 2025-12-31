#include <openssl/md2.h>
#include <openssl/evp.h>

void md2_init_update_final() {
    MD2_CTX ctx;
    unsigned char data[] = "test";
    unsigned char hash[MD2_DIGEST_LENGTH];
    MD2_Init(&ctx);
    MD2_Update(&ctx, data, 4);
    MD2_Final(hash, &ctx);
}

void md2_evp_digest() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    unsigned char data[] = "test";
    unsigned char hash[MD2_DIGEST_LENGTH];
    EVP_DigestInit_ex(ctx, EVP_md2(), NULL);
    EVP_Digest(data, 4, hash, NULL, EVP_md2(), NULL);
    EVP_MD_CTX_free(ctx);
}
