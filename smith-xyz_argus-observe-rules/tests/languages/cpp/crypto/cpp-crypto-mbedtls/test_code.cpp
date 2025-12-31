#include <mbedtls/md.h>
#include <mbedtls/aes.h>
#include <mbedtls/rsa.h>
#include <mbedtls/ecdsa.h>
#include <mbedtls/entropy.h>
#include <mbedtls/ctr_drbg.h>

void mbedtls_md5_init() {
    mbedtls_md5_context ctx;
    mbedtls_md5_init(&ctx);
}

void mbedtls_md5_update() {
    mbedtls_md5_context ctx;
    unsigned char data[] = "test";
    mbedtls_md5_init(&ctx);
    mbedtls_md5_update(&ctx, data, sizeof(data) - 1);
}

void mbedtls_md5_finish() {
    mbedtls_md5_context ctx;
    unsigned char hash[16];
    mbedtls_md5_init(&ctx);
    mbedtls_md5_finish(&ctx, hash);
}

void mbedtls_sha1_init() {
    mbedtls_sha1_context ctx;
    mbedtls_sha1_init(&ctx);
}

void mbedtls_sha1_update() {
    mbedtls_sha1_context ctx;
    unsigned char data[] = "test";
    mbedtls_sha1_init(&ctx);
    mbedtls_sha1_update(&ctx, data, sizeof(data) - 1);
}

void mbedtls_sha1_finish() {
    mbedtls_sha1_context ctx;
    unsigned char hash[20];
    mbedtls_sha1_init(&ctx);
    mbedtls_sha1_finish(&ctx, hash);
}

void mbedtls_sha256_init() {
    mbedtls_sha256_context ctx;
    mbedtls_sha256_init(&ctx);
}

void mbedtls_sha256_update() {
    mbedtls_sha256_context ctx;
    unsigned char data[] = "test";
    mbedtls_sha256_init(&ctx);
    mbedtls_sha256_update(&ctx, data, sizeof(data) - 1);
}

void mbedtls_sha256_finish() {
    mbedtls_sha256_context ctx;
    unsigned char hash[32];
    mbedtls_sha256_init(&ctx);
    mbedtls_sha256_finish(&ctx, hash);
}

void mbedtls_sha512_init() {
    mbedtls_sha512_context ctx;
    mbedtls_sha512_init(&ctx);
}

void mbedtls_sha512_update() {
    mbedtls_sha512_context ctx;
    unsigned char data[] = "test";
    mbedtls_sha512_init(&ctx);
    mbedtls_sha512_update(&ctx, data, sizeof(data) - 1);
}

void mbedtls_sha512_finish() {
    mbedtls_sha512_context ctx;
    unsigned char hash[64];
    mbedtls_sha512_init(&ctx);
    mbedtls_sha512_finish(&ctx, hash);
}

void mbedtls_aes_setkey_enc() {
    mbedtls_aes_context ctx;
    unsigned char key[32];
    mbedtls_aes_setkey_enc(&ctx, key, 256);
}

void mbedtls_aes_setkey_dec() {
    mbedtls_aes_context ctx;
    unsigned char key[32];
    mbedtls_aes_setkey_dec(&ctx, key, 256);
}

void mbedtls_aes_crypt_ecb() {
    mbedtls_aes_context ctx;
    unsigned char input[16];
    unsigned char output[16];
    unsigned char key[32];
    mbedtls_aes_setkey_enc(&ctx, key, 256);
    mbedtls_aes_crypt_ecb(&ctx, MBEDTLS_AES_ENCRYPT, input, output);
}

void mbedtls_aes_crypt_cbc() {
    mbedtls_aes_context ctx;
    unsigned char input[64];
    unsigned char output[64];
    unsigned char iv[16];
    unsigned char key[32];
    mbedtls_aes_setkey_enc(&ctx, key, 256);
    mbedtls_aes_crypt_cbc(&ctx, MBEDTLS_AES_ENCRYPT, 64, iv, input, output);
}

void mbedtls_rsa_gen_key() {
    mbedtls_rsa_context rsa;
    mbedtls_entropy_context entropy;
    mbedtls_ctr_drbg_context ctr_drbg;
    mbedtls_rsa_gen_key(&rsa, NULL, NULL, 2048, 65537);
}

void mbedtls_rsa_public() {
    mbedtls_rsa_context rsa;
    unsigned char input[256];
    unsigned char output[256];
    mbedtls_rsa_public(&rsa, input, output);
}

void mbedtls_rsa_private() {
    mbedtls_rsa_context rsa;
    unsigned char input[256];
    unsigned char output[256];
    mbedtls_entropy_context entropy;
    mbedtls_ctr_drbg_context ctr_drbg;
    mbedtls_rsa_private(&rsa, NULL, NULL, input, output);
}

void mbedtls_rsa_pkcs1_sign() {
    mbedtls_rsa_context rsa;
    unsigned char hash[32];
    unsigned char sig[256];
    mbedtls_entropy_context entropy;
    mbedtls_ctr_drbg_context ctr_drbg;
    mbedtls_rsa_pkcs1_sign(&rsa, NULL, NULL, MBEDTLS_RSA_PRIVATE, MBEDTLS_MD_SHA256, 32, hash, sig);
}

void mbedtls_rsa_pkcs1_verify() {
    mbedtls_rsa_context rsa;
    unsigned char hash[32];
    unsigned char sig[256];
    mbedtls_rsa_pkcs1_verify(&rsa, NULL, NULL, MBEDTLS_RSA_PUBLIC, MBEDTLS_MD_SHA256, 32, hash, sig);
}

void mbedtls_ecdsa_sign() {
    mbedtls_ecdsa_context ecdsa;
    mbedtls_mpi r, s;
    unsigned char buf[32];
    mbedtls_entropy_context entropy;
    mbedtls_ctr_drbg_context ctr_drbg;
    mbedtls_ecdsa_sign(NULL, &r, &s, &ecdsa, buf, 32, NULL, NULL);
}

void mbedtls_ecdsa_verify() {
    mbedtls_ecdsa_context ecdsa;
    mbedtls_mpi r, s;
    unsigned char buf[32];
    mbedtls_ecdsa_verify(NULL, &ecdsa, buf, 32, &r, &s);
}

void mbedtls_entropy_init() {
    mbedtls_entropy_context entropy;
    mbedtls_entropy_init(&entropy);
}

void mbedtls_ctr_drbg_init() {
    mbedtls_ctr_drbg_context ctr_drbg;
    mbedtls_ctr_drbg_init(&ctr_drbg);
}

void mbedtls_ctr_drbg_seed() {
    mbedtls_ctr_drbg_context ctr_drbg;
    mbedtls_entropy_context entropy;
    unsigned char custom[16];
    mbedtls_ctr_drbg_seed(&ctr_drbg, NULL, NULL, custom, 16);
}

void mbedtls_ctr_drbg_random() {
    mbedtls_ctr_drbg_context ctr_drbg;
    unsigned char output[32];
    mbedtls_ctr_drbg_random(&ctr_drbg, output, 32);
}
