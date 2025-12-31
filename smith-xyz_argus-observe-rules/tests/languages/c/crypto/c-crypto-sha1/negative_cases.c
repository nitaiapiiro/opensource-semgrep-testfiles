#include <openssl/md5.h>
#include <openssl/evp.h>

void md5_usage() {
    MD5_CTX ctx;
    MD5_Init(&ctx);
}

void evp_md5_usage() {
    EVP_MD_CTX *ctx;
    ctx = EVP_MD_CTX_new();
    EVP_DigestInit_ex(ctx, EVP_md5(), NULL);
    EVP_MD_CTX_free(ctx);
}
