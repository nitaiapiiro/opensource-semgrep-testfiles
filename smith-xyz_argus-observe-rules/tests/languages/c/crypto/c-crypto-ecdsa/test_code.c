#include <openssl/ec.h>
#include <openssl/evp.h>

void ec_key_new() {
    EC_KEY *key = EC_KEY_new();
    EC_KEY_free(key);
}

void ec_key_new_by_curve() {
    EC_KEY *key = EC_KEY_new_by_curve_name(NID_secp256r1);
    EC_KEY_free(key);
}

void ec_key_generate() {
    EC_KEY *key = EC_KEY_new();
    EC_KEY_generate_key(key);
    EC_KEY_free(key);
}

void ecdsa_sign_verify() {
    EC_KEY *key = EC_KEY_new();
    unsigned char digest[32];
    unsigned char sig[256];
    unsigned int sig_len;

    ECDSA_sign(NID_sha256, digest, 32, sig, &sig_len, key);
    ECDSA_verify(NID_sha256, digest, 32, sig, sig_len, key);
    EC_KEY_free(key);
}

void ecdsa_do_sign_verify() {
    EC_KEY *key = EC_KEY_new();
    unsigned char digest[32];
    ECDSA_SIG *sig;

    sig = ECDSA_do_sign(digest, 32, key);
    ECDSA_do_verify(digest, 32, sig, key);
    ECDSA_SIG_free(sig);
    EC_KEY_free(key);
}

void evp_pkey_new_ec_key() {
    EC_KEY *eckey = EC_KEY_new();
    EVP_PKEY *pkey = EVP_PKEY_new_EC_KEY(eckey);
    EVP_PKEY_free(pkey);
}

void evp_pkey_assign_ec_key() {
    EC_KEY *eckey = EC_KEY_new();
    EVP_PKEY *pkey = EVP_PKEY_new();
    EVP_PKEY_assign_EC_KEY(pkey, eckey);
    EVP_PKEY_free(pkey);
}

void evp_pkey_ctx_ec() {
    EVP_PKEY_CTX *ctx = EVP_PKEY_CTX_new_id(EVP_PKEY_EC, NULL);
    EVP_PKEY_CTX_free(ctx);
}

void evp_pkey_ctx_ecdsa() {
    EVP_PKEY_CTX *ctx = EVP_PKEY_CTX_new_id(EVP_PKEY_ECDSA, NULL);
    EVP_PKEY_CTX_free(ctx);
}

void evp_digest_sign_verify_init() {
    EVP_MD_CTX *ctx;
    EVP_PKEY_CTX *pctx;
    EVP_PKEY *pkey = NULL;

    ctx = EVP_MD_CTX_new();
    EVP_DigestSignInit(ctx, &pctx, EVP_sha256(), NULL, pkey);
    EVP_DigestVerifyInit(ctx, &pctx, EVP_sha256(), NULL, pkey);
    EVP_MD_CTX_free(ctx);
}

void ec_group_new_by_curve() {
    EC_GROUP *group = EC_GROUP_new_by_curve_name(NID_secp256r1);
    EC_GROUP_free(group);
}

void ec_group_get_curve_name() {
    EC_GROUP *group = EC_GROUP_new_by_curve_name(NID_secp256r1);
    int nid = EC_GROUP_get_curve_name(group);
    EC_GROUP_free(group);
}
