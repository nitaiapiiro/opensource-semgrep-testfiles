#include <openssl/ssl.h>

void ssl_ctx_new_versions() {
    SSL_CTX *ctx;

    ctx = SSL_CTX_new(SSLv2_method());
    ctx = SSL_CTX_new(SSLv3_method());
    ctx = SSL_CTX_new(TLSv1_method());
    ctx = SSL_CTX_new(TLSv1_1_method());
    ctx = SSL_CTX_new(TLSv1_2_method());
    ctx = SSL_CTX_new(TLSv1_3_method());
    ctx = SSL_CTX_new(SSLv23_method());
    ctx = SSL_CTX_new(DTLSv1_method());
    ctx = SSL_CTX_new(DTLSv1_2_method());
}

void ssl_ctx_set_proto_version() {
    SSL_CTX *ctx = SSL_CTX_new(TLS_method());
    SSL_CTX_set_min_proto_version(ctx, TLS1_2_VERSION);
    SSL_CTX_set_max_proto_version(ctx, TLS1_3_VERSION);
}

void ssl_set_proto_version() {
    SSL *ssl = SSL_new(NULL);
    SSL_set_min_proto_version(ssl, TLS1_2_VERSION);
    SSL_set_max_proto_version(ssl, TLS1_3_VERSION);
    SSL_free(ssl);
}

void ssl_ctx_set_options() {
    SSL_CTX *ctx = SSL_CTX_new(TLS_method());
    SSL_CTX_set_options(ctx, SSL_OP_NO_SSLv2);
    SSL_CTX_set_options(ctx, SSL_OP_NO_SSLv3);
    SSL_CTX_set_options(ctx, SSL_OP_NO_TLSv1);
    SSL_CTX_set_options(ctx, SSL_OP_NO_TLSv1_1);
    SSL_CTX_set_options(ctx, SSL_OP_NO_TLSv1_2);
    SSL_CTX_set_options(ctx, SSL_OP_NO_TLSv1_3);
}
