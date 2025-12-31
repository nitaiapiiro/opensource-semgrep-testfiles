#include <openssl/rand.h>
#include <fcntl.h>
#include <unistd.h>
#include <linux/random.h>
#include <bsd/stdlib.h>
#include <sodium.h>
#include <windows.h>
#include <bcrypt.h>

void openssl_random_bytes() {
    unsigned char buf[32];
    RAND_bytes(buf, 32);
}

void openssl_random_pseudo_bytes() {
    unsigned char buf[32];
    RAND_pseudo_bytes(buf, 32);
}

void openssl_random_seed() {
    unsigned char buf[32];
    RAND_seed(buf, 32);
}

void openssl_random_add() {
    unsigned char buf[32];
    RAND_add(buf, 32, 0.0);
}

void openssl_random_buffer_pattern() {
    unsigned char buf[16];
    RAND_bytes(buf, 16);
}

void dev_random() {
    int fd = open("/dev/random", O_RDONLY);
    close(fd);
}

void dev_urandom() {
    int fd = open("/dev/urandom", O_RDONLY);
    close(fd);
}

void getrandom_syscall() {
    unsigned char buf[32];
    getrandom(buf, 32, 0);
}

void arc4random_family() {
    unsigned int value = arc4random();
    unsigned char buf[32];
    arc4random_buf(buf, 32);
    unsigned int uniform = arc4random_uniform(100);
}

void bcrypt_gen_random() {
    BCRYPT_ALG_HANDLE hProv = NULL;
    unsigned char buf[32];
    BCryptOpenAlgorithmProvider(&hProv, BCRYPT_RNG_ALGORITHM, NULL, 0);
    BCryptGenRandom(hProv, buf, 32, 0);
    BCryptCloseAlgorithmProvider(hProv, 0);
}

void libsodium_randombytes() {
    unsigned char buf[32];
    randombytes_buf(buf, 32);
    uint32_t value = randombytes_random();
    uint32_t uniform = randombytes_uniform(100);
}
