#include <openssl/hmac.h>
#include <openssl/evp.h>

void hmac_simple() {
    unsigned char key[] = "secret key";
    unsigned char data[] = "test data";
    unsigned char hash[EVP_MAX_MD_SIZE];
    unsigned int hash_len;

    HMAC(EVP_sha256(), key, sizeof(key) - 1, data, sizeof(data) - 1, hash, &hash_len);
}

void hmac_init_ex() {
    HMAC_CTX *ctx;
    unsigned char key[] = "secret key";
    unsigned char data[] = "test data";
    unsigned char hash[EVP_MAX_MD_SIZE];
    unsigned int hash_len;

    ctx = HMAC_CTX_new();
    HMAC_Init_ex(ctx, key, sizeof(key) - 1, EVP_sha256(), NULL);
    HMAC_Update(ctx, data, sizeof(data) - 1);
    HMAC_Final(ctx, hash, &hash_len);
    HMAC_CTX_free(ctx);
}

void hmac_pattern() {
    HMAC_CTX *ctx;
    unsigned char key[] = "secret key";
    unsigned char data[] = "test data";
    unsigned char hash[EVP_MAX_MD_SIZE];
    unsigned int hash_len;

    ctx = HMAC_CTX_new();
    HMAC_Init_ex(ctx, key, sizeof(key) - 1, EVP_sha256(), NULL);
    HMAC_Update(ctx, data, sizeof(data) - 1);
    HMAC_Final(ctx, hash, &hash_len);
    HMAC_CTX_free(ctx);
}

void evp_digest_sign_init() {
    EVP_MD_CTX *ctx;
    EVP_PKEY_CTX *pctx;
    EVP_PKEY *pkey = NULL;

    ctx = EVP_MD_CTX_new();
    EVP_DigestSignInit(ctx, &pctx, EVP_sha256(), NULL, pkey);
    EVP_MD_CTX_free(ctx);
}

void evp_digest_verify_init() {
    EVP_MD_CTX *ctx;
    EVP_PKEY_CTX *pctx;
    EVP_PKEY *pkey = NULL;

    ctx = EVP_MD_CTX_new();
    EVP_DigestVerifyInit(ctx, &pctx, EVP_sha256(), NULL, pkey);
    EVP_MD_CTX_free(ctx);
}
