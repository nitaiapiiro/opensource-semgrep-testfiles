#include <openssl/crypto.h>

void openssl_cleanse() {
    unsigned char buf[32];
    OPENSSL_cleanse(buf, 32);
}

void crypto_secure_malloc() {
    void *ptr = CRYPTO_secure_malloc(32, __FILE__, __LINE__);
    CRYPTO_secure_free(ptr, __FILE__, __LINE__);
}

void openssl_secure_malloc() {
    void *ptr = OPENSSL_secure_malloc(32);
    OPENSSL_secure_free(ptr);
}

void openssl_secure_clear_free() {
    void *ptr = OPENSSL_secure_malloc(32);
    OPENSSL_secure_clear_free(ptr, 32);
}

void crypto_secure_allocated() {
    void *ptr = OPENSSL_secure_malloc(32);
    int result = CRYPTO_secure_allocated(ptr);
    OPENSSL_secure_free(ptr);
}
