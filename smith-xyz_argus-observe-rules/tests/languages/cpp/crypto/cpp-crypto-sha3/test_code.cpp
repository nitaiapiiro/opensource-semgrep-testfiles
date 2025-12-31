#include <openssl/evp.h>
#include <cryptopp/sha3.h>
#include <botan/hash.h>

void openssl_evp_sha3_224() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    EVP_DigestInit_ex(ctx, EVP_sha3_224(), NULL);
    EVP_MD_CTX_free(ctx);
}

void openssl_evp_sha3_256() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    EVP_DigestInit_ex(ctx, EVP_sha3_256(), NULL);
    EVP_MD_CTX_free(ctx);
}

void openssl_evp_sha3_384() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    EVP_DigestInit_ex(ctx, EVP_sha3_384(), NULL);
    EVP_MD_CTX_free(ctx);
}

void openssl_evp_sha3_512() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    EVP_DigestInit_ex(ctx, EVP_sha3_512(), NULL);
    EVP_MD_CTX_free(ctx);
}

void openssl_evp_digest_sha3_224() {
    unsigned char data[] = "test";
    unsigned char hash[EVP_MAX_MD_SIZE];
    unsigned int hash_len;
    EVP_Digest(data, sizeof(data) - 1, hash, &hash_len, EVP_sha3_224(), NULL);
}

void openssl_evp_digest_sha3_256() {
    unsigned char data[] = "test";
    unsigned char hash[EVP_MAX_MD_SIZE];
    unsigned int hash_len;
    EVP_Digest(data, sizeof(data) - 1, hash, &hash_len, EVP_sha3_256(), NULL);
}

void openssl_evp_digest_sha3_384() {
    unsigned char data[] = "test";
    unsigned char hash[EVP_MAX_MD_SIZE];
    unsigned int hash_len;
    EVP_Digest(data, sizeof(data) - 1, hash, &hash_len, EVP_sha3_384(), NULL);
}

void openssl_evp_digest_sha3_512() {
    unsigned char data[] = "test";
    unsigned char hash[EVP_MAX_MD_SIZE];
    unsigned int hash_len;
    EVP_Digest(data, sizeof(data) - 1, hash, &hash_len, EVP_sha3_512(), NULL);
}

void openssl_evp_digest_update() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    unsigned char data[] = "test";
    EVP_DigestInit_ex(ctx, EVP_sha3_256(), NULL);
    EVP_DigestUpdate(ctx, data, sizeof(data) - 1);
    EVP_MD_CTX_free(ctx);
}

void openssl_evp_digest_final() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    unsigned char hash[EVP_MAX_MD_SIZE];
    unsigned int hash_len;
    EVP_DigestInit_ex(ctx, EVP_sha3_256(), NULL);
    EVP_DigestFinal_ex(ctx, hash, &hash_len);
    EVP_MD_CTX_free(ctx);
}

void openssl_evp_md_fetch_sha3_224() {
    EVP_MD *md = EVP_MD_fetch(NULL, "SHA3-224", NULL);
}

void openssl_evp_md_fetch_sha3_256() {
    EVP_MD *md = EVP_MD_fetch(NULL, "SHA3-256", NULL);
}

void openssl_evp_md_fetch_sha3_384() {
    EVP_MD *md = EVP_MD_fetch(NULL, "SHA3-384", NULL);
}

void openssl_evp_md_fetch_sha3_512() {
    EVP_MD *md = EVP_MD_fetch(NULL, "SHA3-512", NULL);
}

void cryptopp_sha3_224() {
    CryptoPP::SHA3_224 hash;
}

void cryptopp_sha3_256() {
    CryptoPP::SHA3_256 hash;
}

void cryptopp_sha3_384() {
    CryptoPP::SHA3_384 hash;
}

void cryptopp_sha3_512() {
    CryptoPP::SHA3_512 hash;
}

void botan_sha3_224() {
    auto hash = Botan::HashFunction::create("SHA-3(224)");
}

void botan_sha3_256() {
    auto hash = Botan::HashFunction::create("SHA-3(256)");
}

void botan_sha3_384() {
    auto hash = Botan::HashFunction::create("SHA-3(384)");
}

void botan_sha3_512() {
    auto hash = Botan::HashFunction::create("SHA-3(512)");
}
