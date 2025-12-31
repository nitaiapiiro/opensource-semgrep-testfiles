#include <openssl/provider.h>
#include <openssl/evp.h>

void ossl_provider_load() {
    OSSL_LIB_CTX *ctx = OSSL_LIB_CTX_new();
    OSSL_PROVIDER *prov = OSSL_PROVIDER_load(ctx, "default");
    OSSL_PROVIDER_unload(prov);
    OSSL_LIB_CTX_free(ctx);
}

void ossl_provider_available() {
    OSSL_LIB_CTX *ctx = OSSL_LIB_CTX_new();
    int result = OSSL_PROVIDER_available(ctx, "default");
    OSSL_LIB_CTX_free(ctx);
}

void evp_cipher_fetch() {
    OSSL_LIB_CTX *ctx = OSSL_LIB_CTX_new();
    EVP_CIPHER *cipher = EVP_CIPHER_fetch(ctx, "AES-256-CBC", NULL);
    EVP_CIPHER_free(cipher);
    OSSL_LIB_CTX_free(ctx);
}

void evp_md_fetch() {
    OSSL_LIB_CTX *ctx = OSSL_LIB_CTX_new();
    EVP_MD *md = EVP_MD_fetch(ctx, "SHA256", NULL);
    EVP_MD_free(md);
    OSSL_LIB_CTX_free(ctx);
}

void evp_pkey_sign_verify() {
    EVP_PKEY_CTX *ctx = EVP_PKEY_CTX_new(NULL, NULL);
    unsigned char sig[256];
    size_t siglen = 256;
    unsigned char tbs[32];
    EVP_PKEY_sign(ctx, sig, &siglen, tbs, 32);
    int result = EVP_PKEY_verify(ctx, sig, siglen, tbs, 32);
    EVP_PKEY_CTX_free(ctx);
}

void evp_pkey_verify_recover() {
    EVP_PKEY_CTX *ctx = EVP_PKEY_CTX_new(NULL, NULL);
    unsigned char rmsg[256];
    size_t rmsglen = 256;
    unsigned char sig[256];
    EVP_PKEY_verify_recover(ctx, rmsg, &rmsglen, sig, 256);
    EVP_PKEY_CTX_free(ctx);
}
