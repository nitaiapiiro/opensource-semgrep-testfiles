#include <openssl/ssl.h>
#include <openssl/evp.h>
#include <openssl/engine.h>

void ssl_library_init() {
    SSL_library_init();
}

void openssl_init_crypto() {
    OPENSSL_init_crypto(0, NULL);
}

void openssl_init_ssl() {
    OPENSSL_init_ssl(0, NULL);
}

void openssl_add_all_algorithms() {
    OpenSSL_add_all_algorithms();
}

void openssl_add_all_ciphers() {
    OpenSSL_add_all_ciphers();
}

void openssl_add_all_digests() {
    OpenSSL_add_all_digests();
}

void evp_add_cipher() {
    EVP_add_cipher(EVP_aes_256_cbc());
}

void evp_add_digest() {
    EVP_add_digest(EVP_sha256());
}

void engine_load_builtin_engines() {
    ENGINE_load_builtin_engines();
}

void engine_register_all_complete() {
    ENGINE_register_all_complete();
}
