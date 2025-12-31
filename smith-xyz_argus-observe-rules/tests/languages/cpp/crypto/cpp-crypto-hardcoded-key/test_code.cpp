void hardcoded_string_key() {
    unsigned char key[] = "my_secret_key_12345";
}

void hardcoded_const_string_key() {
    const unsigned char key[] = "secret_key_abcdef";
}

void hardcoded_static_string_key() {
    static unsigned char key[] = "static_secret_key";
}

void hardcoded_char_key() {
    char key[] = "another_secret";
}

void hardcoded_const_char_key() {
    const char key[] = "const_secret";
}

void hardcoded_define_key() {
    #define SECRET_KEY "defined_key_value"
}

void hardcoded_byte_array() {
    unsigned char key[16] = {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08,
                             0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f, 0x10};
}

void hardcoded_const_byte_array() {
    const unsigned char key[16] = {0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x88,
                                   0x99, 0xaa, 0xbb, 0xcc, 0xdd, 0xee, 0xff, 0x00};
}

void hardcoded_static_byte_array() {
    static unsigned char key[32] = {0x00, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77,
                                    0x88, 0x99, 0xaa, 0xbb, 0xcc, 0xdd, 0xee, 0xff,
                                    0x00, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77,
                                    0x88, 0x99, 0xaa, 0xbb, 0xcc, 0xdd, 0xee, 0xff};
}

void hardcoded_std_string() {
    std::string key = "std_string_secret";
}

void hardcoded_const_std_string() {
    const std::string key = "const_std_string_secret";
}

void hardcoded_static_std_string() {
    static std::string key = "static_std_string_secret";
}

void hardcoded_vector() {
    std::vector<unsigned char> key = {0x01, 0x02, 0x03, 0x04};
}

void hardcoded_const_vector() {
    const std::vector<unsigned char> key = {0xaa, 0xbb, 0xcc, 0xdd};
}
