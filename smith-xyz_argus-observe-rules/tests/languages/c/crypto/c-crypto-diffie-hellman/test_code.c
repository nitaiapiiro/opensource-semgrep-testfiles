#include <openssl/dh.h>
#include <openssl/evp.h>

void dh_new() {
    DH *dh = DH_new();
    DH_free(dh);
}

void dh_generate_parameters() {
    DH *dh = DH_new();
    BIGNUM *g = BN_new();
    BN_set_word(g, 2);
    DH_generate_parameters_ex(dh, 2048, g, NULL);
    DH_free(dh);
    BN_free(g);
}

void dh_generate_key() {
    DH *dh = DH_new();
    DH_generate_key(dh);
    DH_free(dh);
}

void dh_compute_key() {
    DH *dh = DH_new();
    unsigned char key[256];
    BIGNUM *pubkey = BN_new();

    DH_compute_key(key, pubkey, dh);
    BN_free(pubkey);
    DH_free(dh);
}

void evp_pkey_new_dh() {
    DH *dh = DH_new();
    EVP_PKEY *pkey = EVP_PKEY_new_DH(dh);
    EVP_PKEY_free(pkey);
}

void evp_pkey_assign_dh() {
    DH *dh = DH_new();
    EVP_PKEY *pkey = EVP_PKEY_new();
    EVP_PKEY_assign_DH(pkey, dh);
    EVP_PKEY_free(pkey);
}

void evp_pkey_ctx_dh() {
    EVP_PKEY_CTX *ctx = EVP_PKEY_CTX_new_id(EVP_PKEY_DH, NULL);
    EVP_PKEY_CTX_free(ctx);
}

void evp_pkey_derive_init() {
    EVP_PKEY_CTX *ctx = EVP_PKEY_CTX_new_id(EVP_PKEY_DH, NULL);
    EVP_PKEY_derive_init(ctx);
    EVP_PKEY_CTX_free(ctx);
}

void evp_pkey_derive_set_peer() {
    EVP_PKEY_CTX *ctx = EVP_PKEY_CTX_new_id(EVP_PKEY_DH, NULL);
    EVP_PKEY *peer = NULL;
    EVP_PKEY_derive_set_peer(ctx, peer);
    EVP_PKEY_CTX_free(ctx);
}

void evp_pkey_derive() {
    EVP_PKEY_CTX *ctx = EVP_PKEY_CTX_new_id(EVP_PKEY_DH, NULL);
    unsigned char key[256];
    size_t keylen = 256;
    EVP_PKEY_derive(ctx, key, &keylen);
    EVP_PKEY_CTX_free(ctx);
}

void dh_set0_pqg() {
    DH *dh = DH_new();
    BIGNUM *p = BN_new();
    BIGNUM *q = NULL;
    BIGNUM *g = BN_new();
    DH_set0_pqg(dh, p, q, g);
    DH_free(dh);
}

void dh_get0_pqg() {
    DH *dh = DH_new();
    const BIGNUM *p, *q, *g;
    DH_get0_pqg(dh, &p, &q, &g);
    DH_free(dh);
}
