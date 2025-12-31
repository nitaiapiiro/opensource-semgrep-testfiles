#include <openssl/sha.h>

void openssl_usage() {
    SHA256_CTX ctx;
    SHA256_Init(&ctx);
}
