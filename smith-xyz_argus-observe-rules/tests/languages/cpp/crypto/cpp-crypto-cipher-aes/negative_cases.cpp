#include <openssl/des.h>
#include <cryptopp/des.h>

void des_usage() {
    DES_cblock key;
    DES_key_schedule schedule;
    DES_set_key(&key, &schedule);
}

void cryptopp_des() {
    unsigned char key[8];
    CryptoPP::DES::Encryption des(key, 8);
}
