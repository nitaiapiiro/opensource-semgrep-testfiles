#include <openssl/evp.h>
#include <cryptopp/camellia.h>
#include <botan/cipher_mode.h>

void openssl_camellia_128_cbc() {
    EVP_CIPHER_CTX *ctx;
    unsigned char key[16];
    unsigned char iv[16];
    ctx = EVP_CIPHER_CTX_new();
    EVP_CipherInit_ex(ctx, EVP_camellia_128_cbc(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_camellia_192_cbc() {
    EVP_CIPHER_CTX *ctx;
    unsigned char key[24];
    unsigned char iv[16];
    ctx = EVP_CIPHER_CTX_new();
    EVP_CipherInit_ex(ctx, EVP_camellia_192_cbc(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_camellia_256_cbc() {
    EVP_CIPHER_CTX *ctx;
    unsigned char key[32];
    unsigned char iv[16];
    ctx = EVP_CIPHER_CTX_new();
    EVP_CipherInit_ex(ctx, EVP_camellia_256_cbc(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_camellia_128_gcm() {
    EVP_CIPHER_CTX *ctx;
    unsigned char key[16];
    unsigned char iv[12];
    ctx = EVP_CIPHER_CTX_new();
    EVP_CipherInit_ex(ctx, EVP_camellia_128_gcm(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_camellia_256_gcm() {
    EVP_CIPHER_CTX *ctx;
    unsigned char key[32];
    unsigned char iv[12];
    ctx = EVP_CIPHER_CTX_new();
    EVP_CipherInit_ex(ctx, EVP_camellia_256_gcm(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_evp_cipher_fetch_camellia_128_cbc() {
    EVP_CIPHER *cipher = EVP_CIPHER_fetch(NULL, "CAMELLIA-128-CBC", NULL);
    EVP_CIPHER_free(cipher);
}

void openssl_evp_cipher_fetch_camellia_192_cbc() {
    EVP_CIPHER *cipher = EVP_CIPHER_fetch(NULL, "CAMELLIA-192-CBC", NULL);
    EVP_CIPHER_free(cipher);
}

void openssl_evp_cipher_fetch_camellia_256_cbc() {
    EVP_CIPHER *cipher = EVP_CIPHER_fetch(NULL, "CAMELLIA-256-CBC", NULL);
    EVP_CIPHER_free(cipher);
}

void openssl_evp_cipher_fetch_camellia_128_gcm() {
    EVP_CIPHER *cipher = EVP_CIPHER_fetch(NULL, "CAMELLIA-128-GCM", NULL);
    EVP_CIPHER_free(cipher);
}

void openssl_evp_cipher_fetch_camellia_256_gcm() {
    EVP_CIPHER *cipher = EVP_CIPHER_fetch(NULL, "CAMELLIA-256-GCM", NULL);
    EVP_CIPHER_free(cipher);
}

void cryptopp_camellia_encryption() {
    unsigned char key[32];
    CryptoPP::Camellia::Encryption camellia(key, 32);
}

void cryptopp_camellia_decryption() {
    unsigned char key[32];
    CryptoPP::Camellia::Decryption camellia(key, 32);
}

void botan_camellia_128_cbc() {
    auto cipher = Botan::Cipher_Mode::create("Camellia-128/CBC", Botan::ENCRYPTION);
}

void botan_camellia_256_cbc() {
    auto cipher = Botan::Cipher_Mode::create("Camellia-256/CBC", Botan::ENCRYPTION);
}

void botan_camellia_128_gcm() {
    auto cipher = Botan::Cipher_Mode::create("Camellia-128/GCM", Botan::ENCRYPTION);
}

void botan_camellia_256_gcm() {
    auto cipher = Botan::Cipher_Mode::create("Camellia-256/GCM", Botan::ENCRYPTION);
}
