#include <openssl/md5.h>
#include <openssl/evp.h>
#include <cryptopp/md5.h>
#include <botan/hash.h>

void openssl_md5_basic() {
    MD5_CTX ctx;
    unsigned char data[] = "test data";
    unsigned char hash[MD5_DIGEST_LENGTH];
    int len = sizeof(data) - 1;

    MD5_Init(&ctx);
    MD5_Update(&ctx, data, len);
    MD5_Final(hash, &ctx);
}

void openssl_md5_init() {
    MD5_CTX ctx;
    MD5_Init(&ctx);
}

void openssl_md5_update() {
    MD5_CTX ctx;
    unsigned char data[] = "test";
    MD5_Init(&ctx);
    MD5_Update(&ctx, data, sizeof(data) - 1);
}

void openssl_md5_final() {
    MD5_CTX ctx;
    unsigned char hash[MD5_DIGEST_LENGTH];
    MD5_Init(&ctx);
    MD5_Final(hash, &ctx);
}

void openssl_evp_md5() {
    EVP_MD_CTX *ctx;
    unsigned char data[] = "test data";
    unsigned char hash[EVP_MAX_MD_SIZE];
    unsigned int hash_len;
    int len = sizeof(data) - 1;

    ctx = EVP_MD_CTX_new();
    EVP_DigestInit_ex(ctx, EVP_md5(), NULL);
    EVP_MD_CTX_free(ctx);
}

void cryptopp_md5() {
    CryptoPP::MD5 hash;
}

void cryptopp_weak_md5() {
    CryptoPP::Weak::MD5 hash;
}
