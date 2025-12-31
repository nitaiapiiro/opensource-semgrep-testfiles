#include <openssl/evp.h>
#include <blake2.h>
#include <cryptopp/blake2.h>
#include <botan/hash.h>

void openssl_evp_blake2b512() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    EVP_DigestInit_ex(ctx, EVP_blake2b512(), NULL);
    EVP_MD_CTX_free(ctx);
}

void openssl_evp_blake2s256() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    EVP_DigestInit_ex(ctx, EVP_blake2s256(), NULL);
    EVP_MD_CTX_free(ctx);
}

void openssl_evp_digest_blake2b512() {
    unsigned char data[] = "test";
    unsigned char hash[EVP_MAX_MD_SIZE];
    unsigned int hash_len;
    EVP_Digest(data, sizeof(data) - 1, hash, &hash_len, EVP_blake2b512(), NULL);
}

void openssl_evp_digest_blake2s256() {
    unsigned char data[] = "test";
    unsigned char hash[EVP_MAX_MD_SIZE];
    unsigned int hash_len;
    EVP_Digest(data, sizeof(data) - 1, hash, &hash_len, EVP_blake2s256(), NULL);
}

void openssl_evp_md_fetch_blake2b512() {
    EVP_MD *md = EVP_MD_fetch(NULL, "BLAKE2b512", NULL);
}

void openssl_evp_md_fetch_blake2s256() {
    EVP_MD *md = EVP_MD_fetch(NULL, "BLAKE2s256", NULL);
}

void blake2b_init() {
    blake2b_state state;
    blake2b_init(&state, 64);
}

void blake2b_update() {
    blake2b_state state;
    unsigned char data[] = "test";
    blake2b_init(&state, 64);
    blake2b_update(&state, data, sizeof(data) - 1);
}

void blake2b_final() {
    blake2b_state state;
    unsigned char out[64];
    blake2b_init(&state, 64);
    blake2b_final(&state, out, 64);
}

void blake2s_init() {
    blake2s_state state;
    blake2s_init(&state, 32);
}

void blake2s_update() {
    blake2s_state state;
    unsigned char data[] = "test";
    blake2s_init(&state, 32);
    blake2s_update(&state, data, sizeof(data) - 1);
}

void blake2s_final() {
    blake2s_state state;
    unsigned char out[32];
    blake2s_init(&state, 32);
    blake2s_final(&state, out, 32);
}

void cryptopp_blake2b() {
    CryptoPP::BLAKE2b hash;
}

void cryptopp_blake2s() {
    CryptoPP::BLAKE2s hash;
}

void botan_blake2b512() {
    auto hash = Botan::HashFunction::create("BLAKE2b(512)");
}

void botan_blake2b256() {
    auto hash = Botan::HashFunction::create("BLAKE2b(256)");
}

void botan_blake2b384() {
    auto hash = Botan::HashFunction::create("BLAKE2b(384)");
}

void botan_blake2s256() {
    auto hash = Botan::HashFunction::create("BLAKE2s(256)");
}

void botan_blake2s128() {
    auto hash = Botan::HashFunction::create("BLAKE2s(128)");
}

void botan_blake2s224() {
    auto hash = Botan::HashFunction::create("BLAKE2s(224)");
}
