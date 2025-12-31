#include <openssl/evp.h>
#include <openssl/pkcs12.h>

void pkcs5_pbkdf2_hmac() {
    unsigned char pass[] = "password";
    unsigned char salt[] = "salt";
    unsigned char out[32];

    PKCS5_PBKDF2_HMAC(pass, sizeof(pass) - 1, salt, sizeof(salt) - 1, 10000, EVP_sha256(), 32, out);
}

void pkcs5_pbkdf2_hmac_sha1() {
    unsigned char pass[] = "password";
    unsigned char salt[] = "salt";
    unsigned char out[32];

    PKCS5_PBKDF2_HMAC_SHA1(pass, sizeof(pass) - 1, salt, sizeof(salt) - 1, 10000, 32, out);
}

void evp_bytes_to_key() {
    unsigned char data[] = "data";
    unsigned char key[32];
    unsigned char iv[16];

    EVP_BytesToKey(EVP_aes_256_cbc(), EVP_sha256(), NULL, data, sizeof(data) - 1, 1, key, iv);
}

void evp_pbe_scrypt() {
    unsigned char pass[] = "password";
    unsigned char salt[] = "salt";
    unsigned char key[32];

    EVP_PBE_scrypt(pass, sizeof(pass) - 1, salt, sizeof(salt) - 1, 16384, 8, 1, 0, key, 32);
}

void evp_pkey_derive_init() {
    EVP_PKEY_CTX *ctx = EVP_PKEY_CTX_new_id(EVP_PKEY_DH, NULL);
    EVP_PKEY_derive_init(ctx);
    EVP_PKEY_CTX_free(ctx);
}

void evp_pkey_derive() {
    EVP_PKEY_CTX *ctx = EVP_PKEY_CTX_new_id(EVP_PKEY_DH, NULL);
    unsigned char key[32];
    size_t keylen = 32;
    EVP_PKEY_derive(ctx, key, &keylen);
    EVP_PKEY_CTX_free(ctx);
}
