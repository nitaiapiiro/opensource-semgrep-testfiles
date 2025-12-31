#include <openssl/evp.h>

void legacy_evp() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    EVP_CIPHER_CTX_free(ctx);
}
