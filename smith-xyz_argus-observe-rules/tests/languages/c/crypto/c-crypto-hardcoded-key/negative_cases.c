#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>

void dynamic_key() {
    unsigned char *key = malloc(32);
    for (int i = 0; i < 32; i++) {
        key[i] = rand() % 256;
    }
    free(key);
}

void non_key_string() {
    const char *text = "Not a key";
    const char *message = "This is just a message";
}

void key_from_environment() {
    const char *api_key = getenv("API_KEY");
    const char *secret = getenv("SECRET_KEY");
}

void key_from_file() {
    FILE *fp = fopen("keyfile.txt", "r");
    unsigned char key[32];
    if (fp) {
        fread(key, 1, 32, fp);
        fclose(fp);
    }
}

void key_from_function() {
    unsigned char key[32];
    memset(key, 0, 32);
    strncpy((char*)key, "loaded_from_function", 32);
}

void non_crypto_variables() {
    const char *algo_name = "MD5";
    const char *cipher_name = "AES";
    int key_length = 256;
    unsigned char buffer[1024];
}

void comments_with_keywords() {
    /* This comment mentions secret key but is not code */
    // Another comment about encryption_key
    printf("String literal with 'key' word");
}
