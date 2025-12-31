#include <openssl/aes.h>
#include <openssl/evp.h>

void aes_key_setup() {
    AES_KEY schedule;
    unsigned char key[32];
    AES_set_encrypt_key(key, 256, &schedule);
    AES_set_decrypt_key(key, 256, &schedule);
}

void aes_encrypt_decrypt() {
    AES_KEY schedule;
    unsigned char key[32];
    unsigned char input[16];
    unsigned char output[16];

    AES_set_encrypt_key(key, 256, &schedule);
    AES_encrypt(input, output, &schedule);
    AES_decrypt(input, output, &schedule);
}

void aes_cbc_mode() {
    AES_KEY schedule;
    unsigned char key[32];
    unsigned char input[128];
    unsigned char output[128];
    unsigned char iv[16];
    int encrypt = 1;

    AES_set_encrypt_key(key, 256, &schedule);
    AES_cbc_encrypt(input, output, 128, &schedule, iv, encrypt);
}

void evp_aes_cbc() {
    EVP_CIPHER_CTX *ctx;
    unsigned char key[32];
    unsigned char iv[16];

    ctx = EVP_CIPHER_CTX_new();
    EVP_CipherInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void evp_aes_gcm() {
    EVP_CIPHER_CTX *ctx;
    unsigned char key[32];
    unsigned char iv[12];

    ctx = EVP_CIPHER_CTX_new();
    EVP_CipherInit_ex(ctx, EVP_aes_256_gcm(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}
