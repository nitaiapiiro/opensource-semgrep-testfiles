#include <openssl/des.h>

void des_usage() {
    DES_key_schedule schedule;
    DES_cblock key;
    DES_set_key(&key, &schedule);
}
