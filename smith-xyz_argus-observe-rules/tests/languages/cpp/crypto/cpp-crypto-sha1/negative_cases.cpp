#include <openssl/sha.h>
#include <cryptopp/sha.h>

void sha256_usage() {
    SHA256_CTX ctx;
    SHA256_Init(&ctx);
}

void cryptopp_sha256() {
    CryptoPP::SHA256 hash;
}
