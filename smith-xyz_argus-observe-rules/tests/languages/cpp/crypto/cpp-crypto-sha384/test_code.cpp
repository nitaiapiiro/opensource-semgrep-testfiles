#include <openssl/sha.h>
#include <openssl/evp.h>
#include <cryptopp/sha.h>
#include <botan/hash.h>

void openssl_sha384_basic() {
    SHA512_CTX ctx;
    unsigned char data[] = "test data";
    unsigned char hash[SHA384_DIGEST_LENGTH];
    int len = sizeof(data) - 1;

    SHA384_Init(&ctx);
    SHA384_Update(&ctx, data, len);
    SHA384_Final(hash, &ctx);
}

void openssl_sha384_init() {
    SHA512_CTX ctx;
    SHA384_Init(&ctx);
}

void openssl_sha384_update() {
    SHA512_CTX ctx;
    unsigned char data[] = "test";
    SHA384_Init(&ctx);
    SHA384_Update(&ctx, data, sizeof(data) - 1);
}

void openssl_sha384_final() {
    SHA512_CTX ctx;
    unsigned char hash[SHA384_DIGEST_LENGTH];
    SHA384_Init(&ctx);
    SHA384_Final(hash, &ctx);
}

void openssl_evp_sha384() {
    EVP_MD_CTX *ctx;
    unsigned char data[] = "test data";
    unsigned char hash[EVP_MAX_MD_SIZE];
    unsigned int hash_len;
    int len = sizeof(data) - 1;

    ctx = EVP_MD_CTX_new();
    EVP_DigestInit_ex(ctx, EVP_sha384(), NULL);
    EVP_DigestUpdate(ctx, data, len);
    EVP_DigestFinal_ex(ctx, hash, &hash_len);
    EVP_MD_CTX_free(ctx);
}

void openssl_evp_sha384_one_shot() {
    unsigned char data[] = "test data";
    unsigned char hash[EVP_MAX_MD_SIZE];
    unsigned int hash_len;
    int len = sizeof(data) - 1;

    EVP_Digest(data, len, hash, &hash_len, EVP_sha384(), NULL);
}

void openssl_evp_md_fetch_sha384() {
    EVP_MD *md = EVP_MD_fetch(NULL, "SHA-384", NULL);
    EVP_MD_free(md);
}

void openssl_evp_md_fetch_sha384_alt() {
    EVP_MD *md = EVP_MD_fetch(NULL, "SHA384", NULL);
    EVP_MD_free(md);
}

void cryptopp_sha384() {
    CryptoPP::SHA384 hash;
}

void botan_sha384_create() {
    auto hash = Botan::HashFunction::create("SHA-384");
}

void botan_sha384_create_alt() {
    auto hash = Botan::HashFunction::create("SHA384");
}

void botan_sha384_direct() {
    Botan::SHA_384 hash;
}
