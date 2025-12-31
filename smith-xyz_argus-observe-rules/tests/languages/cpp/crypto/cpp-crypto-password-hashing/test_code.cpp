#include <openssl/evp.h>
#include <cryptopp/pwdbased.h>
#include <botan/pbkdf.h>
#include <botan/scrypt.h>
#include <botan/argon2.h>

void openssl_pbkdf2_hmac() {
    const char *pass = "password";
    const unsigned char *salt = (unsigned char*)"salt";
    unsigned char out[32];
    PKCS5_PBKDF2_HMAC(pass, 8, salt, 4, 100000, EVP_sha256(), 32, out);
}

void openssl_pbkdf2_hmac_sha1() {
    const char *pass = "password";
    const unsigned char *salt = (unsigned char*)"salt";
    unsigned char out[32];
    PKCS5_PBKDF2_HMAC_SHA1(pass, 8, salt, 4, 100000, 32, out);
}

void openssl_evp_pbe_scrypt() {
    const char *pass = "password";
    const unsigned char *salt = (unsigned char*)"salt";
    unsigned char key[32];
    EVP_PBE_scrypt(pass, 8, salt, 4, 16384, 8, 1, 0, key, 32);
}

void cryptopp_pbkdf2_declaration() {
    CryptoPP::PKCS5_PBKDF2_HMAC<CryptoPP::SHA256> pbkdf;
}

void cryptopp_pbkdf2_derive() {
    CryptoPP::PKCS5_PBKDF2_HMAC<CryptoPP::SHA256> pbkdf;
    unsigned char key[32];
    const char *pass = "password";
    const unsigned char *salt = (unsigned char*)"salt";
    pbkdf.DeriveKey(key, 32, pass, 8, salt, 4, 100000);
}

void cryptopp_scrypt() {
    unsigned char key[32];
    const char *pass = "password";
    const unsigned char *salt = (unsigned char*)"salt";
    CryptoPP::Scrypt().DeriveKey(key, 32, pass, 8, salt, 4, 16384, 8, 1);
}

void botan_pbkdf2() {
    Botan::PBKDF2 *pbkdf = nullptr;
}

void botan_scrypt() {
    const char *pass = "password";
    const unsigned char *salt = (unsigned char*)"salt";
    unsigned char key[32];
    Botan::Scrypt().derive_key(32, pass, salt, 4, 16384, 8, 1);
}

void botan_argon2() {
    Botan::Argon2 argon2(65536, 2, 3);
}
