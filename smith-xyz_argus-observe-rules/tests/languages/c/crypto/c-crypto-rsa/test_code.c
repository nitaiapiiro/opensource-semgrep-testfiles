#include <openssl/rsa.h>
#include <openssl/evp.h>

void rsa_new() {
    RSA *rsa = RSA_new();
    RSA_free(rsa);
}

void rsa_generate_key() {
    RSA *rsa = RSA_new();
    BIGNUM *e = BN_new();
    BN_set_word(e, 65537);
    RSA_generate_key_ex(rsa, 2048, e, NULL);
    RSA_free(rsa);
    BN_free(e);
}

void rsa_encrypt_decrypt() {
    RSA *rsa = RSA_new();
    unsigned char input[256];
    unsigned char output[256];
    int len;

    len = RSA_public_encrypt(256, input, output, rsa, RSA_PKCS1_PADDING);
    len = RSA_private_decrypt(256, input, output, rsa, RSA_PKCS1_PADDING);
    RSA_free(rsa);
}

void rsa_sign_verify() {
    RSA *rsa = RSA_new();
    unsigned char data[256];
    unsigned char sig[256];
    unsigned int sig_len;

    RSA_sign(NID_sha256, data, 256, sig, &sig_len, rsa);
    RSA_verify(NID_sha256, data, 256, sig, sig_len, rsa);
    RSA_free(rsa);
}

void evp_rsa() {
    RSA *rsa = RSA_new();
    EVP_PKEY *pkey = EVP_PKEY_new_RSA(rsa);
    EVP_PKEY_free(pkey);
}

void evp_pkey_assign_rsa() {
    RSA *rsa = RSA_new();
    EVP_PKEY *pkey = EVP_PKEY_new();
    EVP_PKEY_assign_RSA(pkey, rsa);
    EVP_PKEY_free(pkey);
}

void evp_sign_verify() {
    EVP_MD_CTX *ctx;
    EVP_PKEY *pkey = NULL;

    ctx = EVP_MD_CTX_new();
    EVP_SignInit_ex(ctx, EVP_sha256(), NULL);
    EVP_VerifyInit_ex(ctx, EVP_sha256(), NULL);
    EVP_MD_CTX_free(ctx);
}

void evp_pkey_ctx_rsa() {
    EVP_PKEY_CTX *ctx = EVP_PKEY_CTX_new_id(EVP_PKEY_RSA, NULL);
    EVP_PKEY_keygen_init(ctx);
    EVP_PKEY_CTX_free(ctx);
}
