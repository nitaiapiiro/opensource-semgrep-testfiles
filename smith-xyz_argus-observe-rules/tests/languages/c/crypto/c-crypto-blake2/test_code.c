#include <openssl/evp.h>

void evp_blake2b512() {
    EVP_MD_CTX *ctx;
    unsigned char data[] = "test data";
    unsigned char hash[EVP_MAX_MD_SIZE];
    unsigned int hash_len;
    int len = sizeof(data) - 1;

    ctx = EVP_MD_CTX_new();
    EVP_DigestInit_ex(ctx, EVP_blake2b512(), NULL);
    EVP_Digest(data, len, hash, &hash_len, EVP_blake2b512(), NULL);
    EVP_MD_CTX_free(ctx);
}

void evp_blake2s256() {
    EVP_MD_CTX *ctx;
    unsigned char data[] = "test data";
    unsigned char hash[EVP_MAX_MD_SIZE];
    unsigned int hash_len;
    int len = sizeof(data) - 1;

    ctx = EVP_MD_CTX_new();
    EVP_DigestInit_ex(ctx, EVP_blake2s256(), NULL);
    EVP_Digest(data, len, hash, &hash_len, EVP_blake2s256(), NULL);
    EVP_MD_CTX_free(ctx);
}

void blake2b_functions() {
    blake2b_state state;
    unsigned char data[] = "test";
    unsigned char out[64];
    int outlen = 64;

    blake2b_init(&state, outlen);
    blake2b_update(&state, data, sizeof(data) - 1);
    blake2b_final(&state, out, outlen);
}

void blake2s_functions() {
    blake2s_state state;
    unsigned char data[] = "test";
    unsigned char out[32];
    int outlen = 32;

    blake2s_init(&state, outlen);
    blake2s_update(&state, data, sizeof(data) - 1);
    blake2s_final(&state, out, outlen);
}
