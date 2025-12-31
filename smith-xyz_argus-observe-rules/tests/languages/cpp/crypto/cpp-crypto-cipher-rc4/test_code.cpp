#include <openssl/rc4.h>
#include <openssl/evp.h>
#include <cryptopp/arc4.h>
#include <botan/stream_cipher.h>

void openssl_rc4_set_key() {
    RC4_KEY key;
    unsigned char data[16];
    RC4_set_key(&key, 16, data);
}

void openssl_rc4() {
    RC4_KEY key;
    unsigned char input[64];
    unsigned char output[64];
    RC4_set_key(&key, 16, input);
    RC4(&key, 64, input, output);
}

void openssl_evp_rc4() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char key[16];
    unsigned char iv[8];
    EVP_CipherInit_ex(ctx, EVP_rc4(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_evp_rc4_40() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    unsigned char key[5];
    unsigned char iv[8];
    EVP_CipherInit_ex(ctx, EVP_rc4_40(), NULL, key, iv, 1);
    EVP_CIPHER_CTX_free(ctx);
}

void openssl_evp_cipher_fetch_rc4() {
    EVP_CIPHER *cipher = EVP_CIPHER_fetch(NULL, "RC4", NULL);
}

void openssl_evp_cipher_fetch_rc4_40() {
    EVP_CIPHER *cipher = EVP_CIPHER_fetch(NULL, "RC4-40", NULL);
}

void cryptopp_arc4_declaration() {
    CryptoPP::ARC4 arc4;
}

void cryptopp_arc4_initialized() {
    unsigned char key[16];
    CryptoPP::ARC4 arc4(key, 16);
}

void cryptopp_arc4_encryption() {
    unsigned char key[16];
    CryptoPP::ARC4::Encryption enc(key, 16);
}

void cryptopp_arc4_decryption() {
    unsigned char key[16];
    CryptoPP::ARC4::Decryption dec(key, 16);
}

void cryptopp_weak_arc4_declaration() {
    CryptoPP::Weak::ARC4 arc4;
}

void cryptopp_weak_arc4_initialized() {
    unsigned char key[16];
    CryptoPP::Weak::ARC4 arc4(key, 16);
}

void cryptopp_weak_arc4_encryption() {
    unsigned char key[16];
    CryptoPP::Weak::ARC4::Encryption enc(key, 16);
}

void cryptopp_weak_arc4_decryption() {
    unsigned char key[16];
    CryptoPP::Weak::ARC4::Decryption dec(key, 16);
}

void cryptopp_arcfour_declaration() {
    CryptoPP::ARCFour arcfour;
}

void cryptopp_arcfour_initialized() {
    unsigned char key[16];
    CryptoPP::ARCFour arcfour(key, 16);
}

void botan_rc4() {
    auto cipher = Botan::StreamCipher::create("RC4");
}
