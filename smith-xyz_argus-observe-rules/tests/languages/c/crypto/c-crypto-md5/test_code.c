#include <openssl/md5.h>
#include <openssl/evp.h>

void basic_md5_usage() {
    MD5_CTX ctx;
    unsigned char data[] = "test data";
    unsigned char hash[MD5_DIGEST_LENGTH];
    int len = sizeof(data) - 1;

    MD5_Init(&ctx);
    MD5_Update(&ctx, data, len);
    MD5_Final(hash, &ctx);
}

void evp_md5_usage() {
    EVP_MD_CTX *ctx;
    unsigned char data[] = "test data";
    unsigned char hash[EVP_MAX_MD_SIZE];
    unsigned int hash_len;
    int len = sizeof(data) - 1;

    ctx = EVP_MD_CTX_new();
    EVP_DigestInit_ex(ctx, EVP_md5(), NULL);
    EVP_Digest(data, len, hash, &hash_len, EVP_md5(), NULL);
    EVP_MD_CTX_free(ctx);
}

void md5_init_pattern() {
    MD5_CTX ctx;
    MD5_Init(&ctx);
}

void md5_update_pattern() {
    MD5_CTX ctx;
    unsigned char data[] = "test";
    MD5_Init(&ctx);
    MD5_Update(&ctx, data, sizeof(data) - 1);
}

void md5_final_pattern() {
    MD5_CTX ctx;
    unsigned char hash[MD5_DIGEST_LENGTH];
    MD5_Init(&ctx);
    MD5_Final(hash, &ctx);
}
