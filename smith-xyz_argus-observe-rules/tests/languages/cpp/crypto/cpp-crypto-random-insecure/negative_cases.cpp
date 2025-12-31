#include <openssl/rand.h>

void secure_rand() {
    unsigned char buf[32];
    RAND_bytes(buf, 32);
}
