#include <cstdlib>
#include <random>

void c_rand() {
    int x = rand();
}

void c_random() {
    long x = random();
}

void c_srand() {
    srand(12345);
}

void c_srandom() {
    srandom(12345);
}

void std_mt19937() {
    std::mt19937 gen;
}

void std_mt19937_64() {
    std::mt19937_64 gen;
}

void std_linear_congruential_engine() {
    std::linear_congruential_engine<unsigned int, 16807, 0, 2147483647> lce;
}

void std_mersenne_twister_engine() {
    std::mersenne_twister_engine<unsigned int, 32, 624, 397, 31, 0x9908b0df, 11, 0xffffffff, 7, 0x9d2c5680, 15, 0xefc60000, 18, 1812433253> mte;
}
