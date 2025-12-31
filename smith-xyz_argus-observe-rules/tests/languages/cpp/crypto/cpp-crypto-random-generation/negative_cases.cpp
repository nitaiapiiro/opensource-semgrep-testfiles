#include <cstdlib>

void non_crypto_random() {
    int value = rand();
}

void non_crypto_random_range() {
    int value = rand() % 100;
}
