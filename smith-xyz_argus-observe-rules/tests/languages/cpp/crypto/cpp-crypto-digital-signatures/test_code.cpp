#include <openssl/evp.h>
#include <openssl/rsa.h>
#include <openssl/ecdsa.h>
#include <cryptopp/rsa.h>
#include <cryptopp/ecdsa.h>
#include <botan/pk_keys.h>

void openssl_evp_sign_init_ex() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    EVP_SignInit_ex(ctx, EVP_sha256(), NULL);
    EVP_MD_CTX_free(ctx);
}

void openssl_evp_sign_update() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    unsigned char data[] = "message";
    EVP_SignInit_ex(ctx, EVP_sha256(), NULL);
    EVP_SignUpdate(ctx, data, sizeof(data) - 1);
    EVP_MD_CTX_free(ctx);
}

void openssl_evp_sign_final() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    unsigned char sig[256];
    unsigned int siglen;
    EVP_PKEY *pkey = NULL;
    EVP_SignInit_ex(ctx, EVP_sha256(), NULL);
    EVP_SignFinal(ctx, sig, &siglen, pkey);
    EVP_MD_CTX_free(ctx);
}

void openssl_evp_verify_init_ex() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    EVP_VerifyInit_ex(ctx, EVP_sha256(), NULL);
    EVP_MD_CTX_free(ctx);
}

void openssl_evp_verify_update() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    unsigned char data[] = "message";
    EVP_VerifyInit_ex(ctx, EVP_sha256(), NULL);
    EVP_VerifyUpdate(ctx, data, sizeof(data) - 1);
    EVP_MD_CTX_free(ctx);
}

void openssl_evp_verify_final() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    unsigned char sig[256];
    EVP_PKEY *pkey = NULL;
    EVP_VerifyInit_ex(ctx, EVP_sha256(), NULL);
    EVP_VerifyFinal(ctx, sig, 256, pkey);
    EVP_MD_CTX_free(ctx);
}

void openssl_evp_digest_sign_init() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    EVP_PKEY_CTX *pctx = NULL;
    EVP_PKEY *pkey = NULL;
    EVP_DigestSignInit(ctx, &pctx, EVP_sha256(), NULL, pkey);
    EVP_MD_CTX_free(ctx);
}

void openssl_evp_digest_sign_update() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    unsigned char data[] = "message";
    EVP_PKEY_CTX *pctx = NULL;
    EVP_PKEY *pkey = NULL;
    EVP_DigestSignInit(ctx, &pctx, EVP_sha256(), NULL, pkey);
    EVP_DigestSignUpdate(ctx, data, sizeof(data) - 1);
    EVP_MD_CTX_free(ctx);
}

void openssl_evp_digest_sign_final() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    unsigned char sig[256];
    size_t siglen = 256;
    EVP_PKEY_CTX *pctx = NULL;
    EVP_PKEY *pkey = NULL;
    EVP_DigestSignInit(ctx, &pctx, EVP_sha256(), NULL, pkey);
    EVP_DigestSignFinal(ctx, sig, &siglen);
    EVP_MD_CTX_free(ctx);
}

void openssl_evp_digest_verify_init() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    EVP_PKEY_CTX *pctx = NULL;
    EVP_PKEY *pkey = NULL;
    EVP_DigestVerifyInit(ctx, &pctx, EVP_sha256(), NULL, pkey);
    EVP_MD_CTX_free(ctx);
}

void openssl_evp_digest_verify_update() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    unsigned char data[] = "message";
    EVP_PKEY_CTX *pctx = NULL;
    EVP_PKEY *pkey = NULL;
    EVP_DigestVerifyInit(ctx, &pctx, EVP_sha256(), NULL, pkey);
    EVP_DigestVerifyUpdate(ctx, data, sizeof(data) - 1);
    EVP_MD_CTX_free(ctx);
}

void openssl_evp_digest_verify_final() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    unsigned char sig[256];
    EVP_PKEY_CTX *pctx = NULL;
    EVP_PKEY *pkey = NULL;
    EVP_DigestVerifyInit(ctx, &pctx, EVP_sha256(), NULL, pkey);
    EVP_DigestVerifyFinal(ctx, sig, 256);
    EVP_MD_CTX_free(ctx);
}

void openssl_rsa_sign() {
    RSA *rsa = RSA_new();
    unsigned char m[32];
    unsigned char sigret[256];
    unsigned int siglen;
    RSA_sign(NID_sha256, m, 32, sigret, &siglen, rsa);
}

void openssl_rsa_verify() {
    RSA *rsa = RSA_new();
    unsigned char m[32];
    unsigned char sigbuf[256];
    RSA_verify(NID_sha256, m, 32, sigbuf, 256, rsa);
}

void openssl_ecdsa_sign() {
    EC_KEY *key = EC_KEY_new();
    unsigned char dgst[32];
    unsigned char sig[72];
    unsigned int siglen;
    ECDSA_sign(NID_sha256, dgst, 32, sig, &siglen, key);
}

void openssl_ecdsa_verify() {
    EC_KEY *key = EC_KEY_new();
    unsigned char dgst[32];
    unsigned char sig[72];
    ECDSA_verify(NID_sha256, dgst, 32, sig, 72, key);
}

void cryptopp_rsass_pss_signer() {
    CryptoPP::RSA::PrivateKey privkey;
    CryptoPP::RSASS<CryptoPP::PSS, CryptoPP::SHA256>::Signer signer(privkey);
}

void cryptopp_rsass_pkcs1v15_signer() {
    CryptoPP::RSA::PrivateKey privkey;
    CryptoPP::RSASS<CryptoPP::PKCS1v15, CryptoPP::SHA256>::Signer signer(privkey);
}

void cryptopp_rsass_pss_verifier() {
    CryptoPP::RSA::PublicKey pubkey;
    CryptoPP::RSASS<CryptoPP::PSS, CryptoPP::SHA256>::Verifier verifier(pubkey);
}

void cryptopp_rsass_pkcs1v15_verifier() {
    CryptoPP::RSA::PublicKey pubkey;
    CryptoPP::RSASS<CryptoPP::PKCS1v15, CryptoPP::SHA256>::Verifier verifier(pubkey);
}

void cryptopp_ecdsa_signer() {
    CryptoPP::ECDSA<CryptoPP::ECP, CryptoPP::SHA256>::Signer signer;
}

void cryptopp_ecdsa_verifier() {
    CryptoPP::ECDSA<CryptoPP::ECP, CryptoPP::SHA256>::Verifier verifier;
}

void botan_pk_signer() {
    Botan::AutoSeeded_RNG rng;
    Botan::PK_Signer signer(nullptr, rng);
}

void botan_pk_verifier() {
    Botan::PK_Verifier verifier(nullptr);
}
