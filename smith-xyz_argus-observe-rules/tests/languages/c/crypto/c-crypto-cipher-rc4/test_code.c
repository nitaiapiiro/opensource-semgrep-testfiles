#include <openssl/rc4.h>
#include <openssl/evp.h>

void rc4_set_key() {
    RC4_KEY key;
    unsigned char data[16];
    RC4_set_key(&key, 16, data);
}

void rc4_encrypt() {
    RC4_KEY key;
    unsigned char input[64];
    unsigned char output[64];
    int len = 64;

    RC4_set_key(&key, 16, input);
    RC4(&key, len, input, output);
}

void evp_rc4() {
    EVP_CIPHER_CTX *ctx;
    unsigned char key[16];
    unsigned char iv[16];

    ctx = EVP_CIPHER_CTX_new();
    EVP_CipherInit_ex(ctx, EVP_rc4(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void evp_rc4_40() {
    EVP_CIPHER_CTX *ctx;
    unsigned char key[5];
    unsigned char iv[16];

    ctx = EVP_CIPHER_CTX_new();
    EVP_CipherInit_ex(ctx, EVP_rc4_40(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}
