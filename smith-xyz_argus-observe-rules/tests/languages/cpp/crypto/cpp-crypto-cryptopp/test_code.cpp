#include <cryptopp/aes.h>
#include <cryptopp/sha.h>
#include <cryptopp/rsa.h>
#include <cryptopp/osrng.h>

void cryptopp_aes_encryption() {
    unsigned char key[32];
    CryptoPP::AES::Encryption aes(key, 32);
}

void cryptopp_aes_decryption() {
    unsigned char key[32];
    CryptoPP::AES::Decryption aes(key, 32);
}

void cryptopp_sha256() {
    CryptoPP::SHA256();
}

void cryptopp_sha512() {
    CryptoPP::SHA512();
}

void cryptopp_rsa_private_key() {
    CryptoPP::RSA::PrivateKey();
}

void cryptopp_rsa_public_key() {
    CryptoPP::RSA::PublicKey();
}

void cryptopp_auto_seeded_random_pool() {
    CryptoPP::AutoSeededRandomPool();
}
