#include <openssl/hmac.h>
#include <openssl/evp.h>
#include <cryptopp/hmac.h>
#include <botan/mac.h>

void openssl_hmac_one_shot() {
    unsigned char key[] = "secret";
    unsigned char data[] = "message";
    unsigned char hash[EVP_MAX_MD_SIZE];
    unsigned int hash_len;

    HMAC(EVP_sha256(), key, 6, data, 7, hash, &hash_len);
}

void openssl_hmac_init() {
    HMAC_CTX *ctx = HMAC_CTX_new();
    unsigned char key[] = "secret";
    HMAC_Init_ex(ctx, key, 6, EVP_sha256(), NULL);
    HMAC_CTX_free(ctx);
}

void openssl_hmac_update() {
    HMAC_CTX *ctx = HMAC_CTX_new();
    unsigned char key[] = "secret";
    unsigned char data[] = "message";
    HMAC_Init_ex(ctx, key, 6, EVP_sha256(), NULL);
    HMAC_Update(ctx, data, 7);
    HMAC_CTX_free(ctx);
}

void openssl_hmac_final() {
    HMAC_CTX *ctx = HMAC_CTX_new();
    unsigned char key[] = "secret";
    unsigned char hash[EVP_MAX_MD_SIZE];
    unsigned int hash_len;
    HMAC_Init_ex(ctx, key, 6, EVP_sha256(), NULL);
    HMAC_Final(ctx, hash, &hash_len);
    HMAC_CTX_free(ctx);
}

void openssl_hmac_multi_step() {
    HMAC_CTX *ctx = HMAC_CTX_new();
    unsigned char key[] = "secret";
    unsigned char data[] = "message";
    unsigned char hash[EVP_MAX_MD_SIZE];
    unsigned int hash_len;

    HMAC_Init_ex(ctx, key, 6, EVP_sha256(), NULL);
    HMAC_Update(ctx, data, 7);
    HMAC_Final(ctx, hash, &hash_len);
    HMAC_CTX_free(ctx);
}

void openssl_evp_digest_sign_init() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    EVP_PKEY_CTX *pctx = NULL;
    EVP_PKEY *pkey = NULL;
    EVP_DigestSignInit(ctx, &pctx, EVP_sha256(), NULL, pkey);
}

void openssl_evp_mac_fetch() {
    EVP_MAC *mac = EVP_MAC_fetch(NULL, "HMAC", NULL);
}

void cryptopp_hmac_declaration() {
    CryptoPP::HMAC<CryptoPP::SHA256> hmac;
}

void cryptopp_hmac_initialized() {
    unsigned char key[32];
    CryptoPP::HMAC<CryptoPP::SHA256> hmac(key, 32);
}

void cryptopp_hmac_function() {
    unsigned char key[32];
    CryptoPP::HMAC<CryptoPP::SHA256> hmac(key, 32);
}

void botan_hmac() {
    auto mac = Botan::MessageAuthenticationCode::create("HMAC(SHA-256)");
}
