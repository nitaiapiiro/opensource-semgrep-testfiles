#include <botan/hash.h>
#include <botan/cipher_mode.h>
#include <botan/rng.h>

void botan_hash_function_create() {
    auto hash = Botan::HashFunction::create("SHA-256");
}

void botan_cipher_mode_create_encryption() {
    auto cipher = Botan::Cipher_Mode::create("AES-256/CBC", Botan::ENCRYPTION);
}

void botan_cipher_mode_create_decryption() {
    auto cipher = Botan::Cipher_Mode::create("AES-256/GCM", Botan::DECRYPTION);
}

void botan_random_number_generator_create() {
    auto rng = Botan::RandomNumberGenerator::create("system");
}

void botan_pkcs11_rng() {
    Botan::PKCS11::Session session(nullptr);
    Botan::PKCS11::PKCS11_RNG rng(session);
}
