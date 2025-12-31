#include <openssl/evp.h>
#include <openssl/aes.h>
#include <cryptopp/modes.h>
#include <botan/cipher_mode.h>

void openssl_evp_aes_128_ecb() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char key[16];
    unsigned char iv[16];
    EVP_CipherInit_ex(ctx, EVP_aes_128_ecb(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_evp_aes_256_ecb() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char key[32];
    unsigned char iv[16];
    EVP_CipherInit_ex(ctx, EVP_aes_256_ecb(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_evp_aes_128_cbc() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char key[16];
    unsigned char iv[16];
    EVP_CipherInit_ex(ctx, EVP_aes_128_cbc(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_evp_aes_256_cbc() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char key[32];
    unsigned char iv[16];
    EVP_CipherInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_evp_aes_128_cfb() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char key[16];
    unsigned char iv[16];
    EVP_CipherInit_ex(ctx, EVP_aes_128_cfb(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_evp_aes_256_cfb() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char key[32];
    unsigned char iv[16];
    EVP_CipherInit_ex(ctx, EVP_aes_256_cfb(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_evp_aes_128_ofb() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char key[16];
    unsigned char iv[16];
    EVP_CipherInit_ex(ctx, EVP_aes_128_ofb(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_evp_aes_256_ofb() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char key[32];
    unsigned char iv[16];
    EVP_CipherInit_ex(ctx, EVP_aes_256_ofb(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_evp_aes_128_ctr() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char key[16];
    unsigned char iv[16];
    EVP_CipherInit_ex(ctx, EVP_aes_128_ctr(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_evp_aes_256_ctr() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char key[32];
    unsigned char iv[16];
    EVP_CipherInit_ex(ctx, EVP_aes_256_ctr(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_evp_aes_128_gcm() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char key[16];
    unsigned char iv[16];
    EVP_CipherInit_ex(ctx, EVP_aes_128_gcm(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_evp_aes_256_gcm() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char key[32];
    unsigned char iv[16];
    EVP_CipherInit_ex(ctx, EVP_aes_256_gcm(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_evp_aes_128_ccm() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char key[16];
    unsigned char iv[16];
    EVP_CipherInit_ex(ctx, EVP_aes_128_ccm(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_evp_aes_256_ccm() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char key[32];
    unsigned char iv[16];
    EVP_CipherInit_ex(ctx, EVP_aes_256_ccm(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_evp_aes_128_xts() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char key[32];
    unsigned char iv[16];
    EVP_CipherInit_ex(ctx, EVP_aes_128_xts(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_evp_aes_256_xts() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char key[64];
    unsigned char iv[16];
    EVP_CipherInit_ex(ctx, EVP_aes_256_xts(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_aes_cbc_encrypt() {
    AES_KEY schedule;
    unsigned char input[64];
    unsigned char output[64];
    unsigned char iv[16];
    AES_set_encrypt_key((unsigned char*)"key", 128, &schedule);
    AES_cbc_encrypt(input, output, 64, &schedule, iv, AES_ENCRYPT);
}

void openssl_aes_cfb128_encrypt() {
    AES_KEY schedule;
    unsigned char input[64];
    unsigned char output[64];
    unsigned char iv[16];
    int num = 0;
    AES_set_encrypt_key((unsigned char*)"key", 128, &schedule);
    AES_cfb128_encrypt(input, output, 64, &schedule, iv, &num, AES_ENCRYPT);
}

void openssl_aes_ofb128_encrypt() {
    AES_KEY schedule;
    unsigned char input[64];
    unsigned char output[64];
    unsigned char iv[16];
    int num = 0;
    AES_set_encrypt_key((unsigned char*)"key", 128, &schedule);
    AES_ofb128_encrypt(input, output, 64, &schedule, iv, &num);
}

void openssl_aes_ctr128_encrypt() {
    AES_KEY schedule;
    unsigned char input[64];
    unsigned char output[64];
    unsigned char iv[16];
    unsigned char ecount_buf[16];
    unsigned int num = 0;
    AES_set_encrypt_key((unsigned char*)"key", 128, &schedule);
    AES_ctr128_encrypt(input, output, 64, &schedule, iv, ecount_buf, &num);
}

void openssl_aes_ecb_encrypt() {
    AES_KEY schedule;
    unsigned char input[16];
    unsigned char output[16];
    AES_set_encrypt_key((unsigned char*)"key", 128, &schedule);
    AES_ecb_encrypt(input, output, &schedule, AES_ENCRYPT);
}

void openssl_evp_cipher_fetch_ecb() {
    EVP_CIPHER *cipher = EVP_CIPHER_fetch(NULL, "AES-128-ECB", NULL);
    EVP_CIPHER *cipher2 = EVP_CIPHER_fetch(NULL, "AES-256-ECB", NULL);
}

void openssl_evp_cipher_fetch_cbc() {
    EVP_CIPHER *cipher = EVP_CIPHER_fetch(NULL, "AES-128-CBC", NULL);
    EVP_CIPHER *cipher2 = EVP_CIPHER_fetch(NULL, "AES-256-CBC", NULL);
}

void openssl_evp_cipher_fetch_cfb() {
    EVP_CIPHER *cipher = EVP_CIPHER_fetch(NULL, "AES-128-CFB", NULL);
    EVP_CIPHER *cipher2 = EVP_CIPHER_fetch(NULL, "AES-256-CFB", NULL);
}

void openssl_evp_cipher_fetch_ofb() {
    EVP_CIPHER *cipher = EVP_CIPHER_fetch(NULL, "AES-128-OFB", NULL);
    EVP_CIPHER *cipher2 = EVP_CIPHER_fetch(NULL, "AES-256-OFB", NULL);
}

void openssl_evp_cipher_fetch_ctr() {
    EVP_CIPHER *cipher = EVP_CIPHER_fetch(NULL, "AES-128-CTR", NULL);
    EVP_CIPHER *cipher2 = EVP_CIPHER_fetch(NULL, "AES-256-CTR", NULL);
}

void openssl_evp_cipher_fetch_gcm() {
    EVP_CIPHER *cipher = EVP_CIPHER_fetch(NULL, "AES-128-GCM", NULL);
    EVP_CIPHER *cipher2 = EVP_CIPHER_fetch(NULL, "AES-256-GCM", NULL);
}

void openssl_evp_cipher_fetch_ccm() {
    EVP_CIPHER *cipher = EVP_CIPHER_fetch(NULL, "AES-128-CCM", NULL);
    EVP_CIPHER *cipher2 = EVP_CIPHER_fetch(NULL, "AES-256-CCM", NULL);
}

void openssl_evp_cipher_fetch_xts() {
    EVP_CIPHER *cipher = EVP_CIPHER_fetch(NULL, "AES-128-XTS", NULL);
    EVP_CIPHER *cipher2 = EVP_CIPHER_fetch(NULL, "AES-256-XTS", NULL);
}

void cryptopp_cbc_mode_encryption() {
    unsigned char key[32];
    unsigned char iv[16];
    CryptoPP::CBC_Mode<CryptoPP::AES>::Encryption enc(key, 32, iv);
}

void cryptopp_cbc_mode_decryption() {
    unsigned char key[32];
    unsigned char iv[16];
    CryptoPP::CBC_Mode<CryptoPP::AES>::Decryption dec(key, 32, iv);
}

void cryptopp_ctr_mode_encryption() {
    unsigned char key[32];
    unsigned char iv[16];
    CryptoPP::CTR_Mode<CryptoPP::AES>::Encryption enc(key, 32, iv);
}

void cryptopp_gcm_encryption() {
    unsigned char key[32];
    CryptoPP::GCM<CryptoPP::AES>::Encryption enc(key, 32);
}

void botan_cbc() {
    auto cipher = Botan::Cipher_Mode::create("AES-128/CBC", Botan::ENCRYPTION);
}

void botan_gcm() {
    auto cipher = Botan::Cipher_Mode::create("AES-256/GCM", Botan::ENCRYPTION);
}

void botan_ctr() {
    auto cipher = Botan::Cipher_Mode::create("AES-128/CTR", Botan::ENCRYPTION);
}

void botan_ocb() {
    auto cipher = Botan::Cipher_Mode::create("AES-128/OCB", Botan::ENCRYPTION);
    auto cipher2 = Botan::Cipher_Mode::create("AES-256/OCB", Botan::ENCRYPTION);
}

void botan_eax() {
    auto cipher = Botan::Cipher_Mode::create("AES-128/EAX", Botan::ENCRYPTION);
    auto cipher2 = Botan::Cipher_Mode::create("AES-256/EAX", Botan::ENCRYPTION);
}
