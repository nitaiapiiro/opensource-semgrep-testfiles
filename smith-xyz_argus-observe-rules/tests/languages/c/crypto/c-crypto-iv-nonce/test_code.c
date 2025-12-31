#include <openssl/evp.h>
#include <openssl/aes.h>
#include <openssl/des.h>
#include <openssl/rand.h>

void evp_cipher_ctrl_gcm_set_ivlen() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_IVLEN, 12, NULL);
    EVP_CIPHER_CTX_free(ctx);
}

void evp_cipher_ctrl_ccm_set_ivlen() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_CCM_SET_IVLEN, 12, NULL);
    EVP_CIPHER_CTX_free(ctx);
}

void evp_cipher_ctrl_gcm_get_tag() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char tag[16];
    EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_GET_TAG, 16, tag);
    EVP_CIPHER_CTX_free(ctx);
}

void evp_cipher_ctrl_ccm_set_tag() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char tag[16];
    EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_CCM_SET_TAG, 16, tag);
    EVP_CIPHER_CTX_free(ctx);
}

void evp_encrypt_init_ex() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char key[32];
    unsigned char iv[16];
    EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv);
    EVP_CIPHER_CTX_free(ctx);
}

void evp_decrypt_init_ex() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char key[32];
    unsigned char iv[16];
    EVP_DecryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv);
    EVP_CIPHER_CTX_free(ctx);
}

void evp_cipher_init_ex() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char key[32];
    unsigned char iv[16];
    EVP_CipherInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv, 1);
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

void des_cbc_encrypt() {
    DES_key_schedule schedule;
    unsigned char input[64];
    unsigned char output[64];
    DES_cblock iv;
    int encrypt = 1;
    DES_cbc_encrypt(input, output, 64, &schedule, &iv, encrypt);
}

void rand_bytes() {
    unsigned char iv[16];
    RAND_bytes(iv, 16);
}

void rand_pseudo_bytes() {
    unsigned char iv[16];
    RAND_pseudo_bytes(iv, 16);
}
