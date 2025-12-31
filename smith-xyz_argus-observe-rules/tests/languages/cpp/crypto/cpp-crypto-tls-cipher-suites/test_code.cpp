#include <openssl/ssl.h>

void openssl_ssl_ctx_set_cipher_list() {
    SSL_CTX *ctx = SSL_CTX_new(TLS_method());
    SSL_CTX_set_cipher_list(ctx, "HIGH:!aNULL:!MD5");
}

void openssl_ssl_set_cipher_list() {
    SSL *ssl = SSL_new(NULL);
    SSL_set_cipher_list(ssl, "HIGH:!aNULL:!MD5");
}

void openssl_ssl_ctx_set_ciphersuites() {
    SSL_CTX *ctx = SSL_CTX_new(TLS_method());
    SSL_CTX_set_ciphersuites(ctx, "TLS_AES_256_GCM_SHA384");
}

void openssl_ssl_set_ciphersuites() {
    SSL *ssl = SSL_new(NULL);
    SSL_set_ciphersuites(ssl, "TLS_AES_256_GCM_SHA384");
}

void openssl_ssl_ctx_get_ciphers() {
    SSL_CTX *ctx = SSL_CTX_new(TLS_method());
    STACK_OF(SSL_CIPHER) *ciphers = SSL_CTX_get_ciphers(ctx);
}

void openssl_ssl_get_cipher_list() {
    SSL *ssl = SSL_new(NULL);
    const char *cipher = SSL_get_cipher_list(ssl, 0);
}

void openssl_ssl_get_current_cipher() {
    SSL *ssl = SSL_new(NULL);
    const SSL_CIPHER *cipher = SSL_get_current_cipher(ssl);
}

void openssl_ssl_cipher_get_name() {
    SSL *ssl = SSL_new(NULL);
    const SSL_CIPHER *cipher = SSL_get_current_cipher(ssl);
    const char *name = SSL_CIPHER_get_name(cipher);
}

void openssl_ssl_cipher_get_bits() {
    SSL *ssl = SSL_new(NULL);
    const SSL_CIPHER *cipher = SSL_get_current_cipher(ssl);
    int alg_bits;
    int bits = SSL_CIPHER_get_bits(cipher, &alg_bits);
}
