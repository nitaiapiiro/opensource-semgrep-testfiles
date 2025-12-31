#include <openssl/des.h>
#include <openssl/evp.h>

void des_set_key_checked() {
    DES_key_schedule schedule;
    DES_cblock key;
    DES_set_key_checked(&key, &schedule);
}

void des_set_key_unchecked() {
    DES_key_schedule schedule;
    DES_cblock key;
    DES_set_key_unchecked(&key, &schedule);
}

void des_ede3_set_key() {
    DES_key_schedule ks1, ks2, ks3;
    DES_cblock key;
    DES_ede3_set_key(&key, &ks1);
}

void des_ede3_cbc_encrypt() {
    DES_key_schedule ks1, ks2, ks3;
    unsigned char input[64];
    unsigned char output[64];
    DES_cblock iv;
    int encrypt = 1;

    DES_ede3_cbc_encrypt(input, output, 64, &ks1, &ks2, &ks3, &iv, encrypt);
}

void evp_des_ede3() {
    EVP_CIPHER_CTX *ctx;
    unsigned char key[24];
    unsigned char iv[8];

    ctx = EVP_CIPHER_CTX_new();
    EVP_CipherInit_ex(ctx, EVP_des_ede3(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void evp_des_ede3_cbc() {
    EVP_CIPHER_CTX *ctx;
    unsigned char key[24];
    unsigned char iv[8];

    ctx = EVP_CIPHER_CTX_new();
    EVP_CipherInit_ex(ctx, EVP_des_ede3_cbc(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void evp_des_ede3_ecb() {
    EVP_CIPHER_CTX *ctx;
    unsigned char key[24];
    unsigned char iv[8];

    ctx = EVP_CIPHER_CTX_new();
    EVP_CipherInit_ex(ctx, EVP_des_ede3_ecb(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}
