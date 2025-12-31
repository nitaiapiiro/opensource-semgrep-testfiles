#include <openssl/evp.h>
#include <cryptopp/chacha.h>
#include <botan/stream_cipher.h>

void openssl_evp_chacha20() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char key[32];
    unsigned char iv[12];
    EVP_CipherInit_ex(ctx, EVP_chacha20(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_evp_chacha20_poly1305() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char key[32];
    unsigned char iv[12];
    EVP_CipherInit_ex(ctx, EVP_chacha20_poly1305(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_evp_cipher_fetch_chacha20() {
    EVP_CIPHER *cipher = EVP_CIPHER_fetch(NULL, "ChaCha20", NULL);
}

void openssl_evp_cipher_fetch_chacha20_poly1305() {
    EVP_CIPHER *cipher = EVP_CIPHER_fetch(NULL, "ChaCha20-Poly1305", NULL);
}

void cryptopp_chacha20_encryption() {
    unsigned char key[32];
    unsigned char iv[12];
    unsigned int counter = 0;
    CryptoPP::ChaCha20::Encryption enc(key, 32, iv, counter);
}

void cryptopp_chacha20_decryption() {
    unsigned char key[32];
    unsigned char iv[12];
    unsigned int counter = 0;
    CryptoPP::ChaCha20::Decryption dec(key, 32, iv, counter);
}

void cryptopp_chacha20_poly1305_encryption() {
    unsigned char key[32];
    CryptoPP::ChaCha20Poly1305::Encryption enc(key, 32);
}

void cryptopp_chacha20_poly1305_decryption() {
    unsigned char key[32];
    CryptoPP::ChaCha20Poly1305::Decryption dec(key, 32);
}

void botan_chacha20() {
    auto cipher = Botan::Cipher_Mode::create("ChaCha20", Botan::ENCRYPTION);
}

void botan_chacha20_poly1305() {
    auto cipher = Botan::Cipher_Mode::create("ChaCha20Poly1305", Botan::ENCRYPTION);
}

void botan_stream_chacha20() {
    auto cipher = Botan::StreamCipher::create("ChaCha(20)");
}
