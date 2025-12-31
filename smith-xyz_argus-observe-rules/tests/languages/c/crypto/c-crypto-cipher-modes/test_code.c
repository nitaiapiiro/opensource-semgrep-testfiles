#include <openssl/evp.h>
#include <openssl/aes.h>

void evp_aes_ecb() {
    EVP_CIPHER_CTX *ctx;
    unsigned char key[16];
    unsigned char iv[16];

    ctx = EVP_CIPHER_CTX_new();
    EVP_CipherInit_ex(ctx, EVP_aes_128_ecb(), NULL, key, iv, 1);
    EVP_CipherInit_ex(ctx, EVP_aes_256_ecb(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void evp_aes_cbc() {
    EVP_CIPHER_CTX *ctx;
    unsigned char key[16];
    unsigned char iv[16];

    ctx = EVP_CIPHER_CTX_new();
    EVP_CipherInit_ex(ctx, EVP_aes_128_cbc(), NULL, key, iv, 1);
    EVP_CipherInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void evp_aes_cfb() {
    EVP_CIPHER_CTX *ctx;
    unsigned char key[16];
    unsigned char iv[16];

    ctx = EVP_CIPHER_CTX_new();
    EVP_CipherInit_ex(ctx, EVP_aes_128_cfb(), NULL, key, iv, 1);
    EVP_CipherInit_ex(ctx, EVP_aes_256_cfb(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void evp_aes_ofb() {
    EVP_CIPHER_CTX *ctx;
    unsigned char key[16];
    unsigned char iv[16];

    ctx = EVP_CIPHER_CTX_new();
    EVP_CipherInit_ex(ctx, EVP_aes_128_ofb(), NULL, key, iv, 1);
    EVP_CipherInit_ex(ctx, EVP_aes_256_ofb(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void evp_aes_ctr() {
    EVP_CIPHER_CTX *ctx;
    unsigned char key[16];
    unsigned char iv[16];

    ctx = EVP_CIPHER_CTX_new();
    EVP_CipherInit_ex(ctx, EVP_aes_128_ctr(), NULL, key, iv, 1);
    EVP_CipherInit_ex(ctx, EVP_aes_256_ctr(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void evp_aes_gcm() {
    EVP_CIPHER_CTX *ctx;
    unsigned char key[16];
    unsigned char iv[12];

    ctx = EVP_CIPHER_CTX_new();
    EVP_CipherInit_ex(ctx, EVP_aes_128_gcm(), NULL, key, iv, 1);
    EVP_CipherInit_ex(ctx, EVP_aes_256_gcm(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void evp_aes_ccm() {
    EVP_CIPHER_CTX *ctx;
    unsigned char key[16];
    unsigned char iv[12];

    ctx = EVP_CIPHER_CTX_new();
    EVP_CipherInit_ex(ctx, EVP_aes_128_ccm(), NULL, key, iv, 1);
    EVP_CipherInit_ex(ctx, EVP_aes_256_ccm(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void evp_aes_xts() {
    EVP_CIPHER_CTX *ctx;
    unsigned char key[32];
    unsigned char iv[16];

    ctx = EVP_CIPHER_CTX_new();
    EVP_CipherInit_ex(ctx, EVP_aes_128_xts(), NULL, key, iv, 1);
    EVP_CipherInit_ex(ctx, EVP_aes_256_xts(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void aes_cbc_encrypt() {
    AES_KEY schedule;
    unsigned char input[128];
    unsigned char output[128];
    unsigned char iv[16];
    int encrypt = 1;

    AES_cbc_encrypt(input, output, 128, &schedule, iv, encrypt);
}

void aes_cfb128_encrypt() {
    AES_KEY schedule;
    unsigned char input[128];
    unsigned char output[128];
    unsigned char iv[16];
    int num = 0;
    int encrypt = 1;

    AES_cfb128_encrypt(input, output, 128, &schedule, iv, &num, encrypt);
}

void aes_ofb128_encrypt() {
    AES_KEY schedule;
    unsigned char input[128];
    unsigned char output[128];
    unsigned char iv[16];
    int num = 0;

    AES_ofb128_encrypt(input, output, 128, &schedule, iv, &num);
}

void aes_ctr128_encrypt() {
    AES_KEY schedule;
    unsigned char input[128];
    unsigned char output[128];
    unsigned char iv[16];
    unsigned char ecount_buf[16];
    unsigned int num = 0;

    AES_ctr128_encrypt(input, output, 128, &schedule, iv, ecount_buf, &num);
}
