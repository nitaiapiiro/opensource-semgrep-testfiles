// Negative test cases - should NOT match

void not_twofish() {
    // AES, not Twofish
    unsigned char key[32];
    CryptoPP::AES::Encryption aes(key, 32);
}

void not_twofish_botan() {
    // AES, not Twofish
    auto cipher = Botan::Cipher_Mode::create("AES-256/CBC", Botan::ENCRYPTION);
}
