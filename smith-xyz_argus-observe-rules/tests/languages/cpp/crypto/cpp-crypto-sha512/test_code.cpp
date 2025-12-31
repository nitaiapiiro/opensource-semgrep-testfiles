#include <openssl/sha.h>
#include <openssl/evp.h>
#include <cryptopp/sha.h>
#include <botan/hash.h>

void openssl_sha512_basic() {
    SHA512_CTX ctx;
    unsigned char data[] = "test data";
    unsigned char hash[SHA512_DIGEST_LENGTH];
    int len = sizeof(data) - 1;

    SHA512_Init(&ctx);
    SHA512_Update(&ctx, data, len);
    SHA512_Final(hash, &ctx);
}

void openssl_sha512_init() {
    SHA512_CTX ctx;
    SHA512_Init(&ctx);
}

void openssl_sha512_update() {
    SHA512_CTX ctx;
    unsigned char data[] = "test";
    SHA512_Init(&ctx);
    SHA512_Update(&ctx, data, sizeof(data) - 1);
}

void openssl_sha512_final() {
    SHA512_CTX ctx;
    unsigned char hash[SHA512_DIGEST_LENGTH];
    SHA512_Init(&ctx);
    SHA512_Final(hash, &ctx);
}

void openssl_evp_sha512() {
    EVP_MD_CTX *ctx;
    unsigned char data[] = "test data";
    unsigned char hash[EVP_MAX_MD_SIZE];
    unsigned int hash_len;
    int len = sizeof(data) - 1;

    ctx = EVP_MD_CTX_new();
    EVP_DigestInit_ex(ctx, EVP_sha512(), NULL);
    EVP_DigestUpdate(ctx, data, len);
    EVP_DigestFinal_ex(ctx, hash, &hash_len);
    EVP_MD_CTX_free(ctx);
}

void openssl_evp_sha512_fetch() {
    EVP_MD *md = EVP_MD_fetch(NULL, "SHA-512", NULL);
    EVP_MD *md2 = EVP_MD_fetch(NULL, "SHA512", NULL);
}

void cryptopp_sha512() {
    CryptoPP::SHA512 hash;
}

void botan_sha512() {
    auto hash = Botan::HashFunction::create("SHA-512");
}
