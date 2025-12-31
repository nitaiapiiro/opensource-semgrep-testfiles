#include <openssl/ssl.h>
#include <openssl/x509.h>
#include <openssl/x509_vfy.h>

void ssl_ctx_set_verify() {
    SSL_CTX *ctx = SSL_CTX_new(TLS_method());
    SSL_CTX_set_verify(ctx, SSL_VERIFY_PEER, NULL);
    SSL_CTX_free(ctx);
}

void ssl_set_verify() {
    SSL *ssl = SSL_new(NULL);
    SSL_set_verify(ssl, SSL_VERIFY_PEER, NULL);
    SSL_free(ssl);
}

void ssl_ctx_set_verify_depth() {
    SSL_CTX *ctx = SSL_CTX_new(TLS_method());
    SSL_CTX_set_verify_depth(ctx, 4);
    SSL_CTX_free(ctx);
}

void ssl_ctx_set_cert_verify_callback() {
    SSL_CTX *ctx = SSL_CTX_new(TLS_method());
    SSL_CTX_set_cert_verify_callback(ctx, NULL, NULL);
    SSL_CTX_free(ctx);
}

void ssl_get_verify_result() {
    SSL *ssl = SSL_new(NULL);
    long result = SSL_get_verify_result(ssl);
    SSL_free(ssl);
}

void ssl_ctx_load_verify_locations() {
    SSL_CTX *ctx = SSL_CTX_new(TLS_method());
    SSL_CTX_load_verify_locations(ctx, "ca.pem", NULL);
    SSL_CTX_free(ctx);
}

void ssl_ctx_set_default_verify_paths() {
    SSL_CTX *ctx = SSL_CTX_new(TLS_method());
    SSL_CTX_set_default_verify_paths(ctx);
    SSL_CTX_free(ctx);
}

void x509_store_ctx_get_error() {
    X509_STORE_CTX *ctx = X509_STORE_CTX_new();
    int error = X509_STORE_CTX_get_error(ctx);
    X509_STORE_CTX_free(ctx);
}

void x509_verify_cert() {
    X509_STORE_CTX *ctx = X509_STORE_CTX_new();
    int result = X509_verify_cert(ctx);
    X509_STORE_CTX_free(ctx);
}
