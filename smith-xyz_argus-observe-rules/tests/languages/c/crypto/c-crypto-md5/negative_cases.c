#include <openssl/sha.h>
#include <openssl/evp.h>

void sha256_usage() {
    SHA256_CTX ctx;
    unsigned char data[] = "test data";
    unsigned char hash[SHA256_DIGEST_LENGTH];
    int len = sizeof(data) - 1;

    SHA256_Init(&ctx);
    SHA256_Update(&ctx, data, len);
    SHA256_Final(hash, &ctx);
}

void evp_sha256_usage() {
    EVP_MD_CTX *ctx;
    unsigned char data[] = "test data";
    unsigned char hash[EVP_MAX_MD_SIZE];
    unsigned int hash_len;
    int len = sizeof(data) - 1;

    ctx = EVP_MD_CTX_new();
    EVP_DigestInit_ex(ctx, EVP_sha256(), NULL);
    EVP_Digest(data, len, hash, &hash_len, EVP_sha256(), NULL);
    EVP_MD_CTX_free(ctx);
}
