#include <openssl/rsa.h>

void rsa_usage() {
    RSA *rsa = RSA_new();
    RSA_generate_key_ex(rsa, 2048, NULL, NULL);
}
