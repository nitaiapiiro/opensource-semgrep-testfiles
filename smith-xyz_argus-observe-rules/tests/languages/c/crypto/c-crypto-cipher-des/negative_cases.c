#include <openssl/aes.h>

void aes_usage() {
    AES_KEY schedule;
    unsigned char key[32];
    AES_set_encrypt_key(key, 256, &schedule);
}
