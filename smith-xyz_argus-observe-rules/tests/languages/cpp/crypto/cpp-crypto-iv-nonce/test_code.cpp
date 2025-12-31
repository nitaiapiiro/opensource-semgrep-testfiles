#include <openssl/evp.h>
#include <openssl/aes.h>
#include <openssl/des.h>
#include <openssl/rand.h>
#include <cryptopp/modes.h>
#include <botan/cipher_mode.h>

void openssl_evp_cipher_ctrl_gcm_set_ivlen() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_SET_IVLEN, 12, NULL);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_evp_cipher_ctrl_ccm_set_ivlen() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_CCM_SET_IVLEN, 12, NULL);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_evp_cipher_ctrl_gcm_get_tag() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char tag[16];
    EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_GCM_GET_TAG, 16, tag);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_evp_cipher_ctrl_ccm_set_tag() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char tag[16];
    EVP_CIPHER_CTX_ctrl(ctx, EVP_CTRL_CCM_SET_TAG, 16, tag);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_evp_encrypt_init_ex() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char key[32];
    unsigned char iv[16];
    EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_evp_decrypt_init_ex() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char key[32];
    unsigned char iv[16];
    EVP_DecryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_evp_cipher_init_ex() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char key[32];
    unsigned char iv[16];
    EVP_CipherInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_aes_cbc_encrypt() {
    AES_KEY schedule;
    unsigned char input[64];
    unsigned char output[64];
    unsigned char iv[16];
    AES_set_encrypt_key((unsigned char*)"key", 256, &schedule);
    AES_cbc_encrypt(input, output, 64, &schedule, iv, AES_ENCRYPT);
}

void openssl_des_cbc_encrypt() {
    DES_key_schedule schedule;
    unsigned char input[64];
    unsigned char output[64];
    DES_cblock iv;
    DES_set_key((DES_cblock*)"key", &schedule);
    DES_cbc_encrypt(input, output, 64, &schedule, &iv, DES_ENCRYPT);
}

void openssl_rand_bytes_iv() {
    unsigned char iv[16];
    RAND_bytes(iv, 16);
}

void openssl_rand_pseudo_bytes_iv() {
    unsigned char iv[16];
    RAND_pseudo_bytes(iv, 16);
}

void cryptopp_cbc_mode_encryption() {
    unsigned char key[32];
    unsigned char iv[16];
    CryptoPP::CBC_Mode<CryptoPP::AES>::Encryption enc(key, 32, iv);
}

void cryptopp_gcm_encryption() {
    unsigned char key[32];
    CryptoPP::GCM<CryptoPP::AES>::Encryption enc(key, 32);
}

void botan_gcm() {
    auto cipher = Botan::Cipher_Mode::create("AES-256/GCM", Botan::ENCRYPTION);
}
