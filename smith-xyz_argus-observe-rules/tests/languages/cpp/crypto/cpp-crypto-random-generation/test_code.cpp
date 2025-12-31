#include <openssl/rand.h>
#include <cryptopp/osrng.h>
#include <botan/rng.h>
#include <random>

void openssl_rand_bytes() {
    unsigned char buf[32];
    RAND_bytes(buf, 32);
}

void openssl_rand_buffer_pattern() {
    unsigned char buf[16];
    RAND_bytes(buf, 16);
}

void cryptopp_auto_seeded_rng() {
    CryptoPP::AutoSeededRandomPool rng;
}

void cryptopp_random_number_generator() {
    CryptoPP::RandomNumberGenerator *rng = new CryptoPP::AutoSeededRandomPool();
}

void botan_system_rng() {
    auto rng = Botan::RandomNumberGenerator::create("system");
}

void std_random_device() {
    std::random_device rd;
}

void std_mt19937() {
    std::mt19937 gen;
}
