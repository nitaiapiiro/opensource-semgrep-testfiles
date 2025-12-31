#include <sodium.h>
#include <sodium/crypto_box.h>
#include <sodium/crypto_secretbox.h>
#include <sodium/crypto_sign.h>
#include <sodium/crypto_hash.h>
#include <sodium/crypto_pwhash.h>
#include <sodium/crypto_aead_chacha20poly1305.h>
#include <sodium/crypto_aead_aes256gcm.h>

void sodium_init_call() {
    sodium_init();
}

void crypto_box_easy() {
    unsigned char c[64];
    unsigned char m[32];
    unsigned char n[24];
    unsigned char pk[32];
    unsigned char sk[32];
    crypto_box_easy(c, m, 32, n, pk, sk);
}

void crypto_box_open_easy() {
    unsigned char m[32];
    unsigned char c[64];
    unsigned char n[24];
    unsigned char pk[32];
    unsigned char sk[32];
    crypto_box_open_easy(m, c, 64, n, pk, sk);
}

void crypto_secretbox_easy() {
    unsigned char c[48];
    unsigned char m[32];
    unsigned char n[24];
    unsigned char k[32];
    crypto_secretbox_easy(c, m, 32, n, k);
}

void crypto_secretbox_open_easy() {
    unsigned char m[32];
    unsigned char c[48];
    unsigned char n[24];
    unsigned char k[32];
    crypto_secretbox_open_easy(m, c, 48, n, k);
}

void crypto_sign_detached() {
    unsigned char sig[64];
    unsigned long long siglen;
    unsigned char m[32];
    unsigned char sk[64];
    crypto_sign_detached(sig, &siglen, m, 32, sk);
}

void crypto_sign_verify_detached() {
    unsigned char sig[64];
    unsigned char m[32];
    unsigned char pk[32];
    crypto_sign_verify_detached(sig, m, 32, pk);
}

void crypto_hash_sha256() {
    unsigned char out[32];
    unsigned char in[32];
    crypto_hash_sha256(out, in, 32);
}

void crypto_hash_sha512() {
    unsigned char out[64];
    unsigned char in[32];
    crypto_hash_sha512(out, in, 32);
}

void crypto_generichash() {
    unsigned char out[32];
    unsigned char in[32];
    unsigned char key[32];
    crypto_generichash(out, 32, in, 32, key, 32);
}

void crypto_pwhash() {
    unsigned char out[32];
    unsigned char passwd[] = "password";
    unsigned char salt[16];
    crypto_pwhash(out, 32, passwd, 8, salt, 100000, 67108864, crypto_pwhash_ALG_DEFAULT);
}

void crypto_pwhash_str() {
    char out[128];
    unsigned char passwd[] = "password";
    crypto_pwhash_str(out, passwd, 8, 100000, 67108864);
}

void crypto_pwhash_str_verify() {
    char str[128];
    unsigned char passwd[] = "password";
    crypto_pwhash_str_verify(str, passwd, 8);
}

void crypto_pwhash_argon2i() {
    unsigned char out[32];
    unsigned char passwd[] = "password";
    unsigned char salt[16];
    crypto_pwhash_argon2i(out, 32, passwd, 8, salt, 3, 67108864, crypto_pwhash_ALG_ARGON2I13);
}

void crypto_pwhash_argon2id() {
    unsigned char out[32];
    unsigned char passwd[] = "password";
    unsigned char salt[16];
    crypto_pwhash_argon2id(out, 32, passwd, 8, salt, 3, 67108864, crypto_pwhash_ALG_ARGON2ID13);
}

void randombytes() {
    unsigned char buf[32];
    randombytes(buf, 32);
}

void randombytes_buf() {
    unsigned char buf[32];
    randombytes_buf(buf, 32);
}

void randombytes_random() {
    uint32_t val = randombytes_random();
}

void crypto_aead_chacha20poly1305_encrypt() {
    unsigned char c[48];
    unsigned long long clen;
    unsigned char m[32];
    unsigned char ad[16];
    unsigned char npub[12];
    unsigned char k[32];
    crypto_aead_chacha20poly1305_encrypt(c, &clen, m, 32, ad, 16, NULL, npub, k);
}

void crypto_aead_chacha20poly1305_decrypt() {
    unsigned char m[32];
    unsigned long long mlen;
    unsigned char c[48];
    unsigned char ad[16];
    unsigned char npub[12];
    unsigned char k[32];
    crypto_aead_chacha20poly1305_decrypt(m, &mlen, NULL, c, 48, ad, 16, npub, k);
}

void crypto_aead_aes256gcm_encrypt() {
    unsigned char c[48];
    unsigned long long clen;
    unsigned char m[32];
    unsigned char ad[16];
    unsigned char npub[12];
    unsigned char k[32];
    crypto_aead_aes256gcm_encrypt(c, &clen, m, 32, ad, 16, NULL, npub, k);
}

void crypto_aead_aes256gcm_decrypt() {
    unsigned char m[32];
    unsigned long long mlen;
    unsigned char c[48];
    unsigned char ad[16];
    unsigned char npub[12];
    unsigned char k[32];
    crypto_aead_aes256gcm_decrypt(m, &mlen, NULL, c, 48, ad, 16, npub, k);
}
