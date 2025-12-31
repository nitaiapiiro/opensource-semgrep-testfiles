#include <openssl/evp.h>

void evp_sha3_224() {
    EVP_MD_CTX *ctx;
    unsigned char data[] = "test data";
    unsigned char hash[EVP_MAX_MD_SIZE];
    unsigned int hash_len;
    int len = sizeof(data) - 1;

    ctx = EVP_MD_CTX_new();
    EVP_DigestInit_ex(ctx, EVP_sha3_224(), NULL);
    EVP_Digest(data, len, hash, &hash_len, EVP_sha3_224(), NULL);
    EVP_MD_CTX_free(ctx);
}

void evp_sha3_256() {
    EVP_MD_CTX *ctx;
    unsigned char data[] = "test data";
    unsigned char hash[EVP_MAX_MD_SIZE];
    unsigned int hash_len;
    int len = sizeof(data) - 1;

    ctx = EVP_MD_CTX_new();
    EVP_DigestInit_ex(ctx, EVP_sha3_256(), NULL);
    EVP_Digest(data, len, hash, &hash_len, EVP_sha3_256(), NULL);
    EVP_MD_CTX_free(ctx);
}

void evp_sha3_384() {
    EVP_MD_CTX *ctx;
    unsigned char data[] = "test data";
    unsigned char hash[EVP_MAX_MD_SIZE];
    unsigned int hash_len;
    int len = sizeof(data) - 1;

    ctx = EVP_MD_CTX_new();
    EVP_DigestInit_ex(ctx, EVP_sha3_384(), NULL);
    EVP_Digest(data, len, hash, &hash_len, EVP_sha3_384(), NULL);
    EVP_MD_CTX_free(ctx);
}

void evp_sha3_512() {
    EVP_MD_CTX *ctx;
    unsigned char data[] = "test data";
    unsigned char hash[EVP_MAX_MD_SIZE];
    unsigned int hash_len;
    int len = sizeof(data) - 1;

    ctx = EVP_MD_CTX_new();
    EVP_DigestInit_ex(ctx, EVP_sha3_512(), NULL);
    EVP_Digest(data, len, hash, &hash_len, EVP_sha3_512(), NULL);
    EVP_MD_CTX_free(ctx);
}
