#include <openssl/sha.h>

void sha256_usage() {
    unsigned char hash[32];
    SHA256_CTX ctx;
    SHA256_Init(&ctx);
}
