#include <openssl/md5.h>
#include <cryptopp/md5.h>

void md5_usage() {
    MD5_CTX ctx;
    MD5_Init(&ctx);
}

void cryptopp_md5() {
    CryptoPP::MD5 hash;
}
