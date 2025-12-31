#include <openssl/md5.h>

void md5_usage() {
    MD5_CTX ctx;
    MD5_Init(&ctx);
}
