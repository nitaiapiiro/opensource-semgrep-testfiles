#include <cryptopp/twofish.h>
#include <botan/cipher_mode.h>

void cryptopp_twofish_encryption() {
    unsigned char key[32];
    CryptoPP::Twofish::Encryption twofish(key, 32);
}

void cryptopp_twofish_decryption() {
    unsigned char key[32];
    CryptoPP::Twofish::Decryption twofish(key, 32);
}

void botan_twofish_cbc() {
    auto cipher = Botan::Cipher_Mode::create("Twofish/CBC", Botan::ENCRYPTION);
}

void botan_twofish_gcm() {
    auto cipher = Botan::Cipher_Mode::create("Twofish/GCM", Botan::ENCRYPTION);
}
