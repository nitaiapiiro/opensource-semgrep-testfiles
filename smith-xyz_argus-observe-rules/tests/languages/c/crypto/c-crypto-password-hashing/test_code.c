#include <crypt.h>
#include <bcrypt.h>
#include <argon2.h>

void crypt_usage() {
    char *hash = crypt("password", "$2a$10$salt");
    char *hash2 = crypt_r("password", "$2a$10$salt", NULL);
}

void bcrypt_usage() {
    char hash[BCRYPT_HASHSIZE];
    bcrypt_hashpw("password", "$2a$10$salt", hash);
    int result = bcrypt_checkpw("password", hash);
}

void argon2_usage() {
    unsigned char hash[32];
    unsigned char salt[16];
    argon2i_hash(2, 65536, 1, "password", 8, salt, 16, hash, 32);
    argon2id_hash(2, 65536, 1, "password", 8, salt, 16, hash, 32);
    argon2d_hash(2, 65536, 1, "password", 8, salt, 16, hash, 32);
    int result1 = argon2i_verify(hash, "password", 8);
    int result2 = argon2id_verify(hash, "password", 8);
    int result3 = argon2d_verify(hash, "password", 8);
}
