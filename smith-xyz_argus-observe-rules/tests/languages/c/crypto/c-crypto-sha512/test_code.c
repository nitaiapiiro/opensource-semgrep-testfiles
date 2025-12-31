#include <openssl/sha.h>
#include <openssl/evp.h>

void basic_sha512_usage() {
    SHA512_CTX ctx;
    unsigned char data[] = "test data";
    unsigned char hash[SHA512_DIGEST_LENGTH];
    int len = sizeof(data) - 1;

    SHA512_Init(&ctx);
    SHA512_Update(&ctx, data, len);
    SHA512_Final(hash, &ctx);
}

void evp_sha512_usage() {
    EVP_MD_CTX *ctx;
    unsigned char data[] = "test data";
    unsigned char hash[EVP_MAX_MD_SIZE];
    unsigned int hash_len;
    int len = sizeof(data) - 1;

    ctx = EVP_MD_CTX_new();
    EVP_DigestInit_ex(ctx, EVP_sha512(), NULL);
    EVP_Digest(data, len, hash, &hash_len, EVP_sha512(), NULL);
    EVP_MD_CTX_free(ctx);
}
