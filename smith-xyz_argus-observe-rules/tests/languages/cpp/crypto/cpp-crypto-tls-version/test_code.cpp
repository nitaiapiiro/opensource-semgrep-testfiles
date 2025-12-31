#include <openssl/ssl.h>

void openssl_ssl_ctx_ssl2() {
    SSL_CTX *ctx = SSL_CTX_new(SSLv2_method());
}

void openssl_ssl_ctx_ssl3() {
    SSL_CTX *ctx = SSL_CTX_new(SSLv3_method());
}

void openssl_ssl_ctx_tls1() {
    SSL_CTX *ctx = SSL_CTX_new(TLSv1_method());
}

void openssl_ssl_ctx_tls1_1() {
    SSL_CTX *ctx = SSL_CTX_new(TLSv1_1_method());
}

void openssl_ssl_ctx_tls1_2() {
    SSL_CTX *ctx = SSL_CTX_new(TLSv1_2_method());
}

void openssl_ssl_ctx_tls1_3() {
    SSL_CTX *ctx = SSL_CTX_new(TLSv1_3_method());
}

void openssl_ssl_ctx_ssl23() {
    SSL_CTX *ctx = SSL_CTX_new(SSLv23_method());
}

void openssl_ssl_ctx_dtls1() {
    SSL_CTX *ctx = SSL_CTX_new(DTLSv1_method());
}

void openssl_ssl_ctx_dtls1_2() {
    SSL_CTX *ctx = SSL_CTX_new(DTLSv1_2_method());
}

void openssl_ssl_ctx_set_min_proto() {
    SSL_CTX *ctx = SSL_CTX_new(TLS_method());
    SSL_CTX_set_min_proto_version(ctx, TLS1_2_VERSION);
}

void openssl_ssl_ctx_set_max_proto() {
    SSL_CTX *ctx = SSL_CTX_new(TLS_method());
    SSL_CTX_set_max_proto_version(ctx, TLS1_3_VERSION);
}

void openssl_ssl_set_min_proto() {
    SSL *ssl = SSL_new(NULL);
    SSL_set_min_proto_version(ssl, TLS1_2_VERSION);
}

void openssl_ssl_set_max_proto() {
    SSL *ssl = SSL_new(NULL);
    SSL_set_max_proto_version(ssl, TLS1_3_VERSION);
}

void openssl_ssl_ctx_set_options_no_ssl2() {
    SSL_CTX *ctx = SSL_CTX_new(TLS_method());
    SSL_CTX_set_options(ctx, SSL_OP_NO_SSLv2);
}

void openssl_ssl_ctx_set_options_no_ssl3() {
    SSL_CTX *ctx = SSL_CTX_new(TLS_method());
    SSL_CTX_set_options(ctx, SSL_OP_NO_SSLv3);
}

void openssl_ssl_ctx_set_options_no_tls1() {
    SSL_CTX *ctx = SSL_CTX_new(TLS_method());
    SSL_CTX_set_options(ctx, SSL_OP_NO_TLSv1);
}

void openssl_ssl_ctx_set_options_no_tls1_1() {
    SSL_CTX *ctx = SSL_CTX_new(TLS_method());
    SSL_CTX_set_options(ctx, SSL_OP_NO_TLSv1_1);
}

void openssl_ssl_ctx_set_options_no_tls1_2() {
    SSL_CTX *ctx = SSL_CTX_new(TLS_method());
    SSL_CTX_set_options(ctx, SSL_OP_NO_TLSv1_2);
}

void openssl_ssl_ctx_set_options_no_tls1_3() {
    SSL_CTX *ctx = SSL_CTX_new(TLS_method());
    SSL_CTX_set_options(ctx, SSL_OP_NO_TLSv1_3);
}
