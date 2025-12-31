#include <openssl/ecdh.h>
#include <openssl/evp.h>
#include <cryptopp/dh.h>
#include <cryptopp/x25519.h>
#include <cryptopp/x448.h>
#include <botan/ecdh.h>
#include <botan/x25519.h>
#include <botan/x448.h>

void openssl_ecdh_compute_key() {
    EC_KEY *key = EC_KEY_new();
    unsigned char secret[32];
    const EC_POINT *pub_key = NULL;
    EC_KEY_get0_public_key(key, &pub_key);
    ECDH_compute_key(secret, 32, pub_key, key, NULL);
}

void openssl_evp_pkey_raw_private_x25519() {
    unsigned char key[32];
    EVP_PKEY *pkey = EVP_PKEY_new_raw_private_key(EVP_PKEY_X25519, NULL, key, 32);
}

void openssl_evp_pkey_raw_public_x25519() {
    unsigned char key[32];
    EVP_PKEY *pkey = EVP_PKEY_new_raw_public_key(EVP_PKEY_X25519, NULL, key, 32);
}

void openssl_evp_pkey_raw_private_x448() {
    unsigned char key[56];
    EVP_PKEY *pkey = EVP_PKEY_new_raw_private_key(EVP_PKEY_X448, NULL, key, 56);
}

void openssl_evp_pkey_raw_public_x448() {
    unsigned char key[56];
    EVP_PKEY *pkey = EVP_PKEY_new_raw_public_key(EVP_PKEY_X448, NULL, key, 56);
}

void openssl_evp_pkey_ctx_x25519() {
    EVP_PKEY_CTX *ctx = EVP_PKEY_CTX_new_id(EVP_PKEY_X25519, NULL);
}

void openssl_evp_pkey_ctx_x448() {
    EVP_PKEY_CTX *ctx = EVP_PKEY_CTX_new_id(EVP_PKEY_X448, NULL);
}

void openssl_evp_pkey_derive_init() {
    EVP_PKEY_CTX *ctx = EVP_PKEY_CTX_new_id(EVP_PKEY_X25519, NULL);
    EVP_PKEY_derive_init(ctx);
}

void openssl_evp_pkey_derive_set_peer() {
    EVP_PKEY_CTX *ctx = EVP_PKEY_CTX_new_id(EVP_PKEY_X25519, NULL);
    EVP_PKEY *peer = NULL;
    EVP_PKEY_derive_set_peer(ctx, peer);
}

void openssl_evp_pkey_derive() {
    EVP_PKEY_CTX *ctx = EVP_PKEY_CTX_new_id(EVP_PKEY_X25519, NULL);
    unsigned char key[32];
    size_t keylen = 32;
    EVP_PKEY_derive(ctx, key, &keylen);
}

void cryptopp_ecdh_domain() {
    CryptoPP::Integer p, g;
    CryptoPP::ECDH<CryptoPP::ECP>::Domain domain(p, g);
}

void cryptopp_x25519_domain() {
    CryptoPP::X25519::Domain domain;
}

void cryptopp_x448_domain() {
    CryptoPP::X448::Domain domain;
}

void botan_ecdh_private_key() {
    Botan::AutoSeeded_RNG rng;
    Botan::EC_Group group("secp256r1");
    Botan::ECDH_PrivateKey key(rng, group);
}

void botan_ecdh_public_key() {
    Botan::EC_Group group("secp256r1");
    Botan::BigInt x, y;
    Botan::ECDH_PublicKey key(group, x, y);
}

void botan_x25519_private_key() {
    Botan::AutoSeeded_RNG rng;
    Botan::X25519_PrivateKey key(rng);
}

void botan_x25519_public_key() {
    Botan::X25519_PublicKey key(nullptr);
}

void botan_x448_private_key() {
    Botan::AutoSeeded_RNG rng;
    Botan::X448_PrivateKey key(rng);
}

void botan_x448_public_key() {
    Botan::X448_PublicKey key(nullptr);
}
