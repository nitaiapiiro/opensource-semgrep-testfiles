#include <openssl/sha.h>
#include <openssl/evp.h>
#include <cryptopp/sha.h>
#include <botan/hash.h>

void openssl_sha1_basic() {
    SHA_CTX ctx;
    unsigned char data[] = "test data";
    unsigned char hash[SHA_DIGEST_LENGTH];
    int len = sizeof(data) - 1;

    SHA1_Init(&ctx);
    SHA1_Update(&ctx, data, len);
    SHA1_Final(hash, &ctx);
}

void openssl_sha1_init() {
    SHA_CTX ctx;
    SHA1_Init(&ctx);
}

void openssl_sha1_update() {
    SHA_CTX ctx;
    unsigned char data[] = "test";
    SHA1_Init(&ctx);
    SHA1_Update(&ctx, data, sizeof(data) - 1);
}

void openssl_sha1_final() {
    SHA_CTX ctx;
    unsigned char hash[SHA_DIGEST_LENGTH];
    SHA1_Init(&ctx);
    SHA1_Final(hash, &ctx);
}

void openssl_evp_sha1() {
    EVP_MD_CTX *ctx;
    unsigned char data[] = "test data";
    unsigned char hash[EVP_MAX_MD_SIZE];
    unsigned int hash_len;
    int len = sizeof(data) - 1;

    ctx = EVP_MD_CTX_new();
    EVP_DigestInit_ex(ctx, EVP_sha1(), NULL);
    EVP_DigestUpdate(ctx, data, len);
    EVP_DigestFinal_ex(ctx, hash, &hash_len);
    EVP_MD_CTX_free(ctx);
}

void openssl_evp_sha1_fetch() {
    EVP_MD *md = EVP_MD_fetch(NULL, "SHA1", NULL);
}

void cryptopp_sha1() {
    CryptoPP::SHA1 hash;
}

void cryptopp_weak_sha1() {
    CryptoPP::Weak::SHA1 hash;
}

void botan_sha1() {
    auto hash = Botan::HashFunction::create("SHA-1");
}
