#include <openssl/evp.h>
#include <openssl/rsa.h>
#include <openssl/ec.h>

void evp_sign_init_ex() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    EVP_PKEY *pkey = NULL;
    EVP_SignInit_ex(ctx, EVP_sha256(), NULL);
    EVP_MD_CTX_free(ctx);
}

void evp_sign_update() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    unsigned char data[] = "test data";
    EVP_SignUpdate(ctx, data, sizeof(data) - 1);
    EVP_MD_CTX_free(ctx);
}

void evp_sign_final() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    unsigned char sig[256];
    unsigned int sig_len;
    EVP_PKEY *pkey = NULL;
    EVP_SignFinal(ctx, sig, &sig_len, pkey);
    EVP_MD_CTX_free(ctx);
}

void evp_verify_init_ex() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    EVP_VerifyInit_ex(ctx, EVP_sha256(), NULL);
    EVP_MD_CTX_free(ctx);
}

void evp_verify_update() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    unsigned char data[] = "test data";
    EVP_VerifyUpdate(ctx, data, sizeof(data) - 1);
    EVP_MD_CTX_free(ctx);
}

void evp_verify_final() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    unsigned char sig[256];
    unsigned int sig_len = 256;
    EVP_PKEY *pkey = NULL;
    EVP_VerifyFinal(ctx, sig, sig_len, pkey);
    EVP_MD_CTX_free(ctx);
}

void evp_digest_sign_init() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    EVP_PKEY_CTX *pctx;
    EVP_PKEY *pkey = NULL;
    EVP_DigestSignInit(ctx, &pctx, EVP_sha256(), NULL, pkey);
    EVP_MD_CTX_free(ctx);
}

void evp_digest_sign_update() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    unsigned char data[] = "test data";
    EVP_DigestSignUpdate(ctx, data, sizeof(data) - 1);
    EVP_MD_CTX_free(ctx);
}

void evp_digest_sign_final() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    unsigned char sig[256];
    size_t sig_len = 256;
    EVP_DigestSignFinal(ctx, sig, &sig_len);
    EVP_MD_CTX_free(ctx);
}

void evp_digest_verify_init() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    EVP_PKEY_CTX *pctx;
    EVP_PKEY *pkey = NULL;
    EVP_DigestVerifyInit(ctx, &pctx, EVP_sha256(), NULL, pkey);
    EVP_MD_CTX_free(ctx);
}

void evp_digest_verify_update() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    unsigned char data[] = "test data";
    EVP_DigestVerifyUpdate(ctx, data, sizeof(data) - 1);
    EVP_MD_CTX_free(ctx);
}

void evp_digest_verify_final() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    unsigned char sig[256];
    size_t sig_len = 256;
    EVP_DigestVerifyFinal(ctx, sig, sig_len);
    EVP_MD_CTX_free(ctx);
}

void rsa_sign() {
    RSA *rsa = RSA_new();
    unsigned char m[256];
    unsigned char sigret[256];
    unsigned int siglen;
    RSA_sign(NID_sha256, m, 256, sigret, &siglen, rsa);
    RSA_free(rsa);
}

void rsa_verify() {
    RSA *rsa = RSA_new();
    unsigned char m[256];
    unsigned char sigbuf[256];
    unsigned int siglen = 256;
    RSA_verify(NID_sha256, m, 256, sigbuf, siglen, rsa);
    RSA_free(rsa);
}

void ecdsa_sign() {
    EC_KEY *key = EC_KEY_new();
    unsigned char dgst[32];
    unsigned char sig[256];
    unsigned int siglen;
    ECDSA_sign(NID_sha256, dgst, 32, sig, &siglen, key);
    EC_KEY_free(key);
}

void ecdsa_verify() {
    EC_KEY *key = EC_KEY_new();
    unsigned char dgst[32];
    unsigned char sig[256];
    unsigned int siglen = 256;
    ECDSA_verify(NID_sha256, dgst, 32, sig, siglen, key);
    EC_KEY_free(key);
}
