#include <openssl/evp.h>
#include <cryptopp/ed25519.h>
#include <cryptopp/ed448.h>
#include <botan/ed25519.h>
#include <botan/ed448.h>

void openssl_evp_pkey_raw_private_ed25519() {
    unsigned char key[32];
    EVP_PKEY *pkey = EVP_PKEY_new_raw_private_key(EVP_PKEY_ED25519, NULL, key, 32);
}

void openssl_evp_pkey_raw_public_ed25519() {
    unsigned char key[32];
    EVP_PKEY *pkey = EVP_PKEY_new_raw_public_key(EVP_PKEY_ED25519, NULL, key, 32);
}

void openssl_evp_pkey_raw_private_ed448() {
    unsigned char key[57];
    EVP_PKEY *pkey = EVP_PKEY_new_raw_private_key(EVP_PKEY_ED448, NULL, key, 57);
}

void openssl_evp_pkey_raw_public_ed448() {
    unsigned char key[57];
    EVP_PKEY *pkey = EVP_PKEY_new_raw_public_key(EVP_PKEY_ED448, NULL, key, 57);
}

void openssl_evp_pkey_ctx_ed25519() {
    EVP_PKEY_CTX *ctx = EVP_PKEY_CTX_new_id(EVP_PKEY_ED25519, NULL);
}

void openssl_evp_pkey_ctx_ed448() {
    EVP_PKEY_CTX *ctx = EVP_PKEY_CTX_new_id(EVP_PKEY_ED448, NULL);
}

void openssl_evp_digest_sign_init_ed25519() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    EVP_PKEY_CTX *pctx = NULL;
    EVP_PKEY *pkey = NULL;
    EVP_DigestSignInit(ctx, &pctx, NULL, NULL, pkey);
}

void openssl_evp_digest_verify_init_ed25519() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    EVP_PKEY_CTX *pctx = NULL;
    EVP_PKEY *pkey = NULL;
    EVP_DigestVerifyInit(ctx, &pctx, NULL, NULL, pkey);
}

void cryptopp_ed25519_signer() {
    CryptoPP::ed25519::Signer signer;
}

void cryptopp_ed25519_verifier() {
    CryptoPP::ed25519::Verifier verifier;
}

void cryptopp_ed448_signer() {
    CryptoPP::ed448::Signer signer;
}

void cryptopp_ed448_verifier() {
    CryptoPP::ed448::Verifier verifier;
}

void botan_ed25519_private_key() {
    Botan::AutoSeeded_RNG rng;
    Botan::Ed25519_PrivateKey key(rng);
}

void botan_ed25519_public_key() {
    Botan::Ed25519_PublicKey key(nullptr);
}

void botan_ed448_private_key() {
    Botan::AutoSeeded_RNG rng;
    Botan::Ed448_PrivateKey key(rng);
}

void botan_ed448_public_key() {
    Botan::Ed448_PublicKey key(nullptr);
}
