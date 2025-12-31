#include <openssl/ec.h>
#include <openssl/evp.h>
#include <cryptopp/eccrypto.h>
#include <botan/ecdsa.h>

void openssl_ec_key_new() {
    EC_KEY *key = EC_KEY_new();
}

void openssl_ec_key_by_curve() {
    EC_KEY *key = EC_KEY_new_by_curve_name(NID_secp256r1);
}

void openssl_ec_key_generate() {
    EC_KEY *key = EC_KEY_new_by_curve_name(NID_secp256r1);
    EC_KEY_generate_key(key);
}

void openssl_ecdsa_sign() {
    EC_KEY *key = EC_KEY_new();
    unsigned char dgst[32];
    unsigned char sig[72];
    unsigned int siglen;
    ECDSA_sign(NID_sha256, dgst, 32, sig, &siglen, key);
}

void openssl_ecdsa_verify() {
    EC_KEY *key = EC_KEY_new();
    unsigned char dgst[32];
    unsigned char sig[72];
    ECDSA_verify(NID_sha256, dgst, 32, sig, 72, key);
}

void openssl_ecdsa_do_sign() {
    EC_KEY *key = EC_KEY_new();
    unsigned char dgst[32];
    ECDSA_SIG *sig = ECDSA_do_sign(dgst, 32, key);
}

void openssl_ecdsa_do_verify() {
    EC_KEY *key = EC_KEY_new();
    unsigned char dgst[32];
    ECDSA_SIG *sig = NULL;
    ECDSA_do_verify(dgst, 32, sig, key);
}

void openssl_evp_pkey_ec() {
    EC_KEY *ec_key = EC_KEY_new();
    EVP_PKEY *pkey = EVP_PKEY_new_EC_KEY(ec_key);
}

void openssl_evp_pkey_assign_ec() {
    EC_KEY *ec_key = EC_KEY_new();
    EVP_PKEY *pkey = EVP_PKEY_new();
    EVP_PKEY_assign_EC_KEY(pkey, ec_key);
}

void openssl_evp_pkey_ctx_ec() {
    EVP_PKEY_CTX *ctx = EVP_PKEY_CTX_new_id(EVP_PKEY_EC, NULL);
}

void openssl_evp_pkey_ctx_ecdsa() {
    EVP_PKEY_CTX *ctx = EVP_PKEY_CTX_new_id(EVP_PKEY_ECDSA, NULL);
}

void openssl_evp_digest_sign_init() {
    EVP_MD_CTX *ctx = EVP_MD_CTX_new();
    EVP_PKEY_CTX *pctx = NULL;
    EVP_PKEY *pkey = NULL;
    EVP_DigestSignInit(ctx, &pctx, EVP_sha256(), NULL, pkey);
}

void openssl_ec_group_by_curve() {
    EC_GROUP *group = EC_GROUP_new_by_curve_name(NID_secp256r1);
}

void openssl_ec_point_operations() {
    EC_GROUP *group = EC_GROUP_new_by_curve_name(NID_secp256r1);
    EC_POINT *point = EC_POINT_new(group);
    unsigned char buf[65];
    EC_POINT_point2oct(group, point, POINT_CONVERSION_UNCOMPRESSED, buf, 65, NULL);
    EC_POINT_oct2point(group, point, buf, 65, NULL);
}

void openssl_ed25519_raw_key() {
    unsigned char key[32];
    EVP_PKEY *pkey = EVP_PKEY_new_raw_private_key(EVP_PKEY_ED25519, NULL, key, 32);
}

void openssl_x25519_raw_key() {
    unsigned char key[32];
    EVP_PKEY *pkey = EVP_PKEY_new_raw_private_key(EVP_PKEY_X25519, NULL, key, 32);
}

void cryptopp_ecdsa_signer() {
    CryptoPP::ECDSA<CryptoPP::ECP, CryptoPP::SHA256>::Signer signer;
}

void cryptopp_ecdsa_verifier() {
    CryptoPP::ECDSA<CryptoPP::ECP, CryptoPP::SHA256>::Verifier verifier;
}

void botan_ecdsa_private_key() {
    Botan::AutoSeeded_RNG rng;
    Botan::EC_Group group("secp256r1");
    Botan::ECDSA_PrivateKey key(rng, group);
}

void botan_ecdsa_public_key() {
    Botan::EC_Group group("secp256r1");
    Botan::BigInt x, y;
    Botan::ECDSA_PublicKey key(group, x, y);
}
