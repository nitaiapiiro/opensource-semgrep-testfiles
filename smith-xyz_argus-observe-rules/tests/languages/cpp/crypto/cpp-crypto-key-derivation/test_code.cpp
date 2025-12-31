#include <openssl/evp.h>
#include <cryptopp/pwdbased.h>
#include <cryptopp/hkdf.h>
#include <botan/pbkdf.h>
#include <botan/scrypt.h>
#include <botan/argon2.h>

void openssl_pbkdf2_hmac() {
    const char *pass = "password";
    const unsigned char *salt = (unsigned char*)"salt";
    unsigned char out[32];
    PKCS5_PBKDF2_HMAC(pass, 8, salt, 4, 10000, EVP_sha256(), 32, out);
}

void openssl_pbkdf2_hmac_sha1() {
    const char *pass = "password";
    const unsigned char *salt = (unsigned char*)"salt";
    unsigned char out[32];
    PKCS5_PBKDF2_HMAC_SHA1(pass, 8, salt, 4, 10000, 32, out);
}

void openssl_evp_bytes_to_key() {
    const unsigned char *data = (unsigned char*)"data";
    unsigned char key[32];
    unsigned char iv[16];
    EVP_BytesToKey(EVP_aes_256_cbc(), EVP_sha256(), (unsigned char*)"salt", data, 4, 1, key, iv);
}

void openssl_evp_pbe_scrypt() {
    const char *pass = "password";
    const unsigned char *salt = (unsigned char*)"salt";
    unsigned char key[32];
    EVP_PBE_scrypt(pass, 8, salt, 4, 16384, 8, 1, 0, key, 32);
}

void openssl_evp_pkey_derive_init() {
    EVP_PKEY_CTX *ctx = EVP_PKEY_CTX_new_id(EVP_PKEY_HKDF, NULL);
    EVP_PKEY_derive_init(ctx);
}

void openssl_evp_kdf_fetch_pbkdf2() {
    EVP_KDF *kdf = EVP_KDF_fetch(NULL, "PBKDF2", NULL);
}

void openssl_evp_kdf_fetch_hkdf() {
    EVP_KDF *kdf = EVP_KDF_fetch(NULL, "HKDF", NULL);
}

void openssl_evp_kdf_fetch_scrypt() {
    EVP_KDF *kdf = EVP_KDF_fetch(NULL, "scrypt", NULL);
}

void openssl_evp_kdf_fetch_argon2() {
    EVP_KDF *kdf = EVP_KDF_fetch(NULL, "Argon2", NULL);
}

void cryptopp_pbkdf2() {
    CryptoPP::PKCS5_PBKDF2_HMAC<CryptoPP::SHA256> pbkdf;
}

void cryptopp_hkdf() {
    unsigned char salt[32];
    unsigned char info[16];
    CryptoPP::HKDF<CryptoPP::SHA256> hkdf(salt, 32, info, 16);
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
