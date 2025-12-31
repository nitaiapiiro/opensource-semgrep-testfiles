#include <openssl/evp.h>
#include <cryptopp/blowfish.h>
#include <botan/cipher_mode.h>

void openssl_blowfish_cbc() {
    EVP_CIPHER_CTX *ctx;
    unsigned char key[16];
    unsigned char iv[8];
    ctx = EVP_CIPHER_CTX_new();
    EVP_CipherInit_ex(ctx, EVP_bf_cbc(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_blowfish_ecb() {
    EVP_CIPHER_CTX *ctx;
    unsigned char key[16];
    unsigned char iv[8];
    ctx = EVP_CIPHER_CTX_new();
    EVP_CipherInit_ex(ctx, EVP_bf_ecb(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_blowfish_cfb() {
    EVP_CIPHER_CTX *ctx;
    unsigned char key[16];
    unsigned char iv[8];
    ctx = EVP_CIPHER_CTX_new();
    EVP_CipherInit_ex(ctx, EVP_bf_cfb(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_blowfish_ofb() {
    EVP_CIPHER_CTX *ctx;
    unsigned char key[16];
    unsigned char iv[8];
    ctx = EVP_CIPHER_CTX_new();
    EVP_CipherInit_ex(ctx, EVP_bf_ofb(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_evp_cipher_fetch_bf_cbc() {
    EVP_CIPHER *cipher = EVP_CIPHER_fetch(NULL, "BF-CBC", NULL);
    EVP_CIPHER_free(cipher);
}

void openssl_evp_cipher_fetch_bf_ecb() {
    EVP_CIPHER *cipher = EVP_CIPHER_fetch(NULL, "BF-ECB", NULL);
    EVP_CIPHER_free(cipher);
}

void openssl_evp_cipher_fetch_bf_cfb() {
    EVP_CIPHER *cipher = EVP_CIPHER_fetch(NULL, "BF-CFB", NULL);
    EVP_CIPHER_free(cipher);
}

void openssl_evp_cipher_fetch_bf_ofb() {
    EVP_CIPHER *cipher = EVP_CIPHER_fetch(NULL, "BF-OFB", NULL);
    EVP_CIPHER_free(cipher);
}

void cryptopp_blowfish_encryption() {
    unsigned char key[16];
    CryptoPP::Blowfish::Encryption blowfish(key, 16);
}

void cryptopp_blowfish_decryption() {
    unsigned char key[16];
    CryptoPP::Blowfish::Decryption blowfish(key, 16);
}

void botan_blowfish_cbc() {
    auto cipher = Botan::Cipher_Mode::create("Blowfish/CBC", Botan::ENCRYPTION);
}

void botan_blowfish_ecb() {
    auto cipher = Botan::Cipher_Mode::create("Blowfish/ECB", Botan::ENCRYPTION);
}
