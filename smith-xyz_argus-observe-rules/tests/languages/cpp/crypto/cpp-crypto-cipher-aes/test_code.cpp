#include <openssl/aes.h>
#include <openssl/evp.h>
#include <cryptopp/aes.h>
#include <botan/cipher_mode.h>

void openssl_aes_set_key() {
    AES_KEY schedule;
    unsigned char key[32];
    AES_set_encrypt_key(key, 256, &schedule);
}

void openssl_aes_encrypt() {
    AES_KEY schedule;
    unsigned char input[16];
    unsigned char output[16];
    unsigned char key[32];
    AES_set_encrypt_key(key, 256, &schedule);
    AES_encrypt(input, output, &schedule);
}

void openssl_evp_aes() {
    EVP_CIPHER_CTX *ctx;
    unsigned char key[32];
    unsigned char iv[16];
    ctx = EVP_CIPHER_CTX_new();
    EVP_CipherInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void cryptopp_aes_encryption() {
    unsigned char key[32];
    CryptoPP::AES::Encryption aes(key, 32);
}

void cryptopp_aes_decryption() {
    unsigned char key[32];
    CryptoPP::AES::Decryption aes(key, 32);
}

void botan_aes_gcm() {
    auto cipher = Botan::Cipher_Mode::create("AES-256/GCM", Botan::ENCRYPTION);
}
