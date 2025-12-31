#include <openssl/sha.h>
#include <openssl/evp.h>
#include <cryptopp/sha.h>
#include <botan/hash.h>

void openssl_sha224_basic() {
    SHA256_CTX ctx;
    unsigned char data[] = "test data";
    unsigned char hash[SHA224_DIGEST_LENGTH];
    int len = sizeof(data) - 1;

    SHA224_Init(&ctx);
    SHA224_Update(&ctx, data, len);
    SHA224_Final(hash, &ctx);
}

void openssl_sha224_init() {
    SHA256_CTX ctx;
    SHA224_Init(&ctx);
}

void openssl_sha224_update() {
    SHA256_CTX ctx;
    unsigned char data[] = "test";
    SHA224_Init(&ctx);
    SHA224_Update(&ctx, data, sizeof(data) - 1);
}

void openssl_sha224_final() {
    SHA256_CTX ctx;
    unsigned char hash[SHA224_DIGEST_LENGTH];
    SHA224_Init(&ctx);
    SHA224_Final(hash, &ctx);
}

void openssl_evp_sha224() {
    EVP_MD_CTX *ctx;
    unsigned char data[] = "test data";
    unsigned char hash[EVP_MAX_MD_SIZE];
    unsigned int hash_len;
    int len = sizeof(data) - 1;

    ctx = EVP_MD_CTX_new();
    EVP_DigestInit_ex(ctx, EVP_sha224(), NULL);
    EVP_DigestUpdate(ctx, data, len);
    EVP_DigestFinal_ex(ctx, hash, &hash_len);
    EVP_MD_CTX_free(ctx);
}

void openssl_evp_sha224_one_shot() {
    unsigned char data[] = "test data";
    unsigned char hash[EVP_MAX_MD_SIZE];
    unsigned int hash_len;
    int len = sizeof(data) - 1;

    EVP_Digest(data, len, hash, &hash_len, EVP_sha224(), NULL);
}

void openssl_evp_md_fetch_sha224() {
    EVP_MD *md = EVP_MD_fetch(NULL, "SHA-224", NULL);
    EVP_MD_free(md);
}

void openssl_evp_md_fetch_sha224_alt() {
    EVP_MD *md = EVP_MD_fetch(NULL, "SHA224", NULL);
    EVP_MD_free(md);
}

void cryptopp_sha224() {
    CryptoPP::SHA224 hash;
}

void botan_sha224_create() {
    auto hash = Botan::HashFunction::create("SHA-224");
}

void botan_sha224_create_alt() {
    auto hash = Botan::HashFunction::create("SHA224");
}

void botan_sha224_direct() {
    Botan::SHA_224 hash;
}
