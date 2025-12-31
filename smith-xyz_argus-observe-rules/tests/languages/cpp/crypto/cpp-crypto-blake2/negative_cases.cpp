#include <openssl/sha.h>

void sha256_usage() {
    SHA256_CTX ctx;
    SHA256_Init(&ctx);
}
