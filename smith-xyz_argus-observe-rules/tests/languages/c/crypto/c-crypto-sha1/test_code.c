#include <openssl/sha.h>
#include <openssl/evp.h>

void basic_sha1_usage() {
    SHA_CTX ctx;
    unsigned char data[] = "test data";
    unsigned char hash[SHA_DIGEST_LENGTH];
    int len = sizeof(data) - 1;

    SHA1_Init(&ctx);
    SHA1_Update(&ctx, data, len);
    SHA1_Final(hash, &ctx);
}

void evp_sha1_usage() {
    EVP_MD_CTX *ctx;
    unsigned char data[] = "test data";
    unsigned char hash[EVP_MAX_MD_SIZE];
    unsigned int hash_len;
    int len = sizeof(data) - 1;

    ctx = EVP_MD_CTX_new();
    EVP_DigestInit_ex(ctx, EVP_sha1(), NULL);
    EVP_Digest(data, len, hash, &hash_len, EVP_sha1(), NULL);
    EVP_MD_CTX_free(ctx);
}

void sha1_init_pattern() {
    SHA_CTX ctx;
    SHA1_Init(&ctx);
}

void sha1_update_pattern() {
    SHA_CTX ctx;
    unsigned char data[] = "test";
    SHA1_Init(&ctx);
    SHA1_Update(&ctx, data, sizeof(data) - 1);
}

void sha1_final_pattern() {
    SHA_CTX ctx;
    unsigned char hash[SHA_DIGEST_LENGTH];
    SHA1_Init(&ctx);
    SHA1_Final(hash, &ctx);
}
