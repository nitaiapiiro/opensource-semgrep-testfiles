#include <openssl/ec.h>

void ecdsa_usage() {
    EC_KEY *key = EC_KEY_new();
    EC_KEY_free(key);
}
