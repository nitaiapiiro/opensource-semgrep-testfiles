#include <openssl/des.h>
#include <openssl/evp.h>
#include <cryptopp/des.h>
#include <botan/cipher_mode.h>

void openssl_des_set_key_checked() {
    DES_cblock key;
    DES_key_schedule schedule;
    DES_set_key_checked(&key, &schedule);
}

void openssl_des_set_key_unchecked() {
    DES_cblock key;
    DES_key_schedule schedule;
    DES_set_key_unchecked(&key, &schedule);
}

void openssl_des_ede3_set_key() {
    DES_cblock key;
    DES_key_schedule schedule;
    DES_ede3_set_key(&key, &schedule);
}

void openssl_des_ede3_cbc_encrypt() {
    DES_key_schedule ks1, ks2, ks3;
    unsigned char input[64];
    unsigned char output[64];
    DES_cblock iv;
    DES_ede3_cbc_encrypt(input, output, 64, &ks1, &ks2, &ks3, &iv, 1);
}

void openssl_evp_des_ede3() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char key[24];
    unsigned char iv[8];
    EVP_CipherInit_ex(ctx, EVP_des_ede3(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_evp_des_ede3_cbc() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char key[24];
    unsigned char iv[8];
    EVP_CipherInit_ex(ctx, EVP_des_ede3_cbc(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_evp_des_ede3_ecb() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char key[24];
    unsigned char iv[8];
    EVP_CipherInit_ex(ctx, EVP_des_ede3_ecb(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_evp_cipher_fetch() {
    EVP_CIPHER *cipher = EVP_CIPHER_fetch(NULL, "DES-EDE3", NULL);
    EVP_CIPHER *cipher2 = EVP_CIPHER_fetch(NULL, "DES-EDE3-CBC", NULL);
    EVP_CIPHER *cipher3 = EVP_CIPHER_fetch(NULL, "DES-EDE3-ECB", NULL);
}

void openssl_des_ede3_cfb64() {
    DES_key_schedule ks1, ks2, ks3;
    unsigned char input[64];
    unsigned char output[64];
    DES_cblock iv;
    int num = 0;
    DES_ede3_cfb64_encrypt(input, output, 64, &ks1, &ks2, &ks3, &iv, &num, 1);
}

void openssl_des_ede3_ofb64() {
    DES_key_schedule ks1, ks2, ks3;
    unsigned char input[64];
    unsigned char output[64];
    DES_cblock iv;
    int num = 0;
    DES_ede3_ofb64_encrypt(input, output, 64, &ks1, &ks2, &ks3, &iv, &num);
}

void cryptopp_des_ede3_encryption() {
    unsigned char key[24];
    CryptoPP::DES_EDE3::Encryption des(key, 24);
}

void cryptopp_des_ede3_decryption() {
    unsigned char key[24];
    CryptoPP::DES_EDE3::Decryption des(key, 24);
}

void botan_3des_ecb() {
    auto cipher = Botan::Cipher_Mode::create("3DES/ECB", Botan::ENCRYPTION);
}

void botan_3des_cbc() {
    auto cipher = Botan::Cipher_Mode::create("3DES/CBC", Botan::ENCRYPTION);
}
