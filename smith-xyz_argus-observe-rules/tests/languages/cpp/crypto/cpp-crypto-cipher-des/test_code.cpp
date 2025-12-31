#include <openssl/des.h>
#include <openssl/evp.h>
#include <cryptopp/des.h>
#include <botan/cipher_mode.h>

void openssl_des_set_key() {
    DES_cblock key;
    DES_key_schedule schedule;
    DES_set_key(&key, &schedule);
}

void openssl_des_encrypt() {
    DES_cblock key;
    DES_key_schedule schedule;
    DES_cblock data;
    DES_set_key(&key, &schedule);
    DES_encrypt(&data, &schedule, 1);
}

void openssl_des_decrypt() {
    DES_cblock key;
    DES_key_schedule schedule;
    DES_cblock data;
    DES_set_key(&key, &schedule);
    DES_decrypt(&data, &schedule, 0);
}

void openssl_des_ecb_encrypt() {
    DES_cblock key;
    DES_key_schedule schedule;
    unsigned char input[8];
    unsigned char output[8];
    DES_set_key(&key, &schedule);
    DES_ecb_encrypt(&input, &output, &schedule, 1);
}

void openssl_des_cbc_encrypt() {
    DES_cblock key;
    DES_key_schedule schedule;
    unsigned char input[64];
    unsigned char output[64];
    DES_cblock iv;
    DES_set_key(&key, &schedule);
    DES_cbc_encrypt(input, output, 64, &schedule, &iv, 1);
}

void openssl_evp_des_ecb() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char key[8];
    unsigned char iv[8];
    EVP_CipherInit_ex(ctx, EVP_des_ecb(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_evp_des_cbc() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char key[8];
    unsigned char iv[8];
    EVP_CipherInit_ex(ctx, EVP_des_cbc(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_evp_cipher_fetch() {
    EVP_CIPHER *cipher = EVP_CIPHER_fetch(NULL, "DES-ECB", NULL);
}

void openssl_des_cfb64() {
    DES_cblock key;
    DES_key_schedule schedule;
    unsigned char input[64];
    unsigned char output[64];
    DES_cblock iv;
    int num = 0;
    DES_set_key(&key, &schedule);
    DES_cfb64_encrypt(input, output, 64, &schedule, &iv, &num, 1);
}

void openssl_des_set_odd_parity() {
    DES_cblock key;
    DES_set_odd_parity(&key);
}

void cryptopp_des_encryption() {
    unsigned char key[8];
    CryptoPP::DES::Encryption des(key, 8);
}

void cryptopp_des_decryption() {
    unsigned char key[8];
    CryptoPP::DES::Decryption des(key, 8);
}

void botan_des_ecb() {
    auto cipher = Botan::Cipher_Mode::create("DES/ECB", Botan::ENCRYPTION);
}

void botan_des_cbc() {
    auto cipher = Botan::Cipher_Mode::create("DES/CBC", Botan::ENCRYPTION);
}
