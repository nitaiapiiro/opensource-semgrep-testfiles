#include <openssl/rand.h>
#include <cryptopp/osrng.h>
#include <botan/rng.h>
#include <sys/random.h>

void openssl_rand_bytes() {
    unsigned char buf[32];
    RAND_bytes(buf, 32);
}

void openssl_rand_bytes_array() {
    unsigned char buf[32];
    RAND_bytes(buf, 32);
    // Use buf
}

void openssl_rand_pseudo_bytes() {
    unsigned char buf[32];
    RAND_pseudo_bytes(buf, 32);
}

void openssl_rand_add() {
    unsigned char buf[32];
    RAND_add(buf, 32, 32.0);
}

void openssl_rand_seed() {
    unsigned char buf[32];
    RAND_seed(buf, 32);
}

void cryptopp_auto_seeded_random_pool() {
    CryptoPP::AutoSeededRandomPool rng;
}

void cryptopp_random_number_generator() {
    CryptoPP::RandomNumberGenerator *rng = nullptr;
}

void botan_rng_system() {
    auto rng = Botan::RandomNumberGenerator::create("system");
}

void botan_rng_user() {
    auto rng = Botan::RandomNumberGenerator::create("user");
}

void getentropy_syscall() {
    unsigned char buf[32];
    getentropy(buf, 32);
}

void getrandom_syscall() {
    unsigned char buf[32];
    getrandom(buf, 32, 0);
}
