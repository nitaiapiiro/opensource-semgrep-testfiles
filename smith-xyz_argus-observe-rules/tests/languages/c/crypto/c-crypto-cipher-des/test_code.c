#include <openssl/des.h>
#include <openssl/evp.h>

void des_key_setup() {
    DES_key_schedule schedule;
    DES_cblock key;
    DES_set_key(&key, &schedule);
}

void des_encrypt_decrypt() {
    DES_key_schedule schedule;
    DES_cblock key;
    DES_LONG data[2];
    int encrypt = 1;

    DES_set_key(&key, &schedule);
    DES_encrypt(data, &schedule, encrypt);
    DES_decrypt(data, &schedule, !encrypt);
}

void des_ecb_mode() {
    DES_key_schedule schedule;
    DES_cblock key;
    DES_cblock input;
    DES_cblock output;
    int encrypt = 1;

    DES_set_key(&key, &schedule);
    DES_ecb_encrypt(&input, &output, &schedule, encrypt);
}

void des_cbc_mode() {
    DES_key_schedule schedule;
    DES_cblock key;
    unsigned char input[64];
    unsigned char output[64];
    DES_cblock iv;
    int encrypt = 1;

    DES_set_key(&key, &schedule);
    DES_cbc_encrypt(input, output, 64, &schedule, &iv, encrypt);
}

void evp_des_ecb() {
    EVP_CIPHER_CTX *ctx;
    unsigned char key[8];
    unsigned char iv[8];

    ctx = EVP_CIPHER_CTX_new();
    EVP_CipherInit_ex(ctx, EVP_des_ecb(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void evp_des_cbc() {
    EVP_CIPHER_CTX *ctx;
    unsigned char key[8];
    unsigned char iv[8];

    ctx = EVP_CIPHER_CTX_new();
    EVP_CipherInit_ex(ctx, EVP_des_cbc(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}
