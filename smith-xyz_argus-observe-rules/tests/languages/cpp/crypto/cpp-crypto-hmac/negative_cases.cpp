#include <openssl/sha.h>

void sha256_direct() {
    SHA256_CTX ctx;
    SHA256_Init(&ctx);
}

void non_hmac_mac() {
    // Some other MAC algorithm
}
