#include <openssl/ec.h>
#include <openssl/evp.h>

void ecdh_compute_key() {
    EC_KEY *key = EC_KEY_new();
    EC_POINT *pubkey = EC_POINT_new(NULL);
    unsigned char out[32];
    size_t outlen = 32;
    ECDH_compute_key(out, outlen, pubkey, key);
    EC_KEY_free(key);
    EC_POINT_free(pubkey);
}

void evp_pkey_derive_ec() {
    EVP_PKEY_CTX *ctx = EVP_PKEY_CTX_new_id(EVP_PKEY_EC, NULL);
    unsigned char key[32];
    size_t keylen = 32;
    EVP_PKEY_derive(ctx, key, &keylen);
    EVP_PKEY_CTX_free(ctx);
}

void evp_pkey_derive_x25519() {
    EVP_PKEY_CTX *ctx = EVP_PKEY_CTX_new_id(EVP_PKEY_X25519, NULL);
    unsigned char key[32];
    size_t keylen = 32;
    EVP_PKEY_derive(ctx, key, &keylen);
    EVP_PKEY_CTX_free(ctx);
}

void evp_pkey_derive_x448() {
    EVP_PKEY_CTX *ctx = EVP_PKEY_CTX_new_id(EVP_PKEY_X448, NULL);
    unsigned char key[56];
    size_t keylen = 56;
    EVP_PKEY_derive(ctx, key, &keylen);
    EVP_PKEY_CTX_free(ctx);
}
