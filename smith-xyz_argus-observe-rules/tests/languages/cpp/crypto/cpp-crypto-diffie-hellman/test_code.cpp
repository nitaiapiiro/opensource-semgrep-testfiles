#include <openssl/dh.h>
#include <openssl/evp.h>
#include <cryptopp/dh.h>
#include <botan/dh.h>

void openssl_dh_new() {
    DH *dh = DH_new();
}

void openssl_dh_generate_parameters_ex() {
    DH *dh = DH_new();
    DH_generate_parameters_ex(dh, 2048, 2, NULL);
}

void openssl_dh_generate_key() {
    DH *dh = DH_new();
    DH_generate_key(dh);
}

void openssl_dh_compute_key() {
    DH *dh = DH_new();
    unsigned char key[256];
    const BIGNUM *pub_key = NULL;
    DH_get0_key(dh, &pub_key, NULL);
    DH_compute_key(key, pub_key, dh);
}

void openssl_evp_pkey_new_dh() {
    DH *dh = DH_new();
    EVP_PKEY *pkey = EVP_PKEY_new_DH(dh);
}

void openssl_evp_pkey_assign_dh() {
    DH *dh = DH_new();
    EVP_PKEY *pkey = EVP_PKEY_new();
    EVP_PKEY_assign_DH(pkey, dh);
}

void openssl_evp_pkey_ctx_dh() {
    EVP_PKEY_CTX *ctx = EVP_PKEY_CTX_new_id(EVP_PKEY_DH, NULL);
}

void openssl_evp_pkey_derive_init() {
    EVP_PKEY_CTX *ctx = EVP_PKEY_CTX_new_id(EVP_PKEY_DH, NULL);
    EVP_PKEY_derive_init(ctx);
}

void openssl_evp_pkey_derive_set_peer() {
    EVP_PKEY_CTX *ctx = EVP_PKEY_CTX_new_id(EVP_PKEY_DH, NULL);
    EVP_PKEY *peer = NULL;
    EVP_PKEY_derive_set_peer(ctx, peer);
}

void openssl_evp_pkey_derive() {
    EVP_PKEY_CTX *ctx = EVP_PKEY_CTX_new_id(EVP_PKEY_DH, NULL);
    unsigned char key[256];
    size_t keylen = 256;
    EVP_PKEY_derive(ctx, key, &keylen);
}

void openssl_dh_set0_pqg() {
    DH *dh = DH_new();
    BIGNUM *p = NULL, *q = NULL, *g = NULL;
    DH_set0_pqg(dh, p, q, g);
}

void openssl_dh_get0_pqg() {
    DH *dh = DH_new();
    const BIGNUM *p = NULL, *q = NULL, *g = NULL;
    DH_get0_pqg(dh, &p, &q, &g);
}

void cryptopp_dh() {
    CryptoPP::DH dh;
}

void cryptopp_dh_domain() {
    CryptoPP::Integer prime, generator;
    CryptoPP::DH_Domain domain(prime, generator);
}

void botan_dh_private_key() {
    Botan::AutoSeeded_RNG rng;
    Botan::DL_Group group("modp/ietf/2048");
    Botan::DH_PrivateKey key(rng, group);
}
