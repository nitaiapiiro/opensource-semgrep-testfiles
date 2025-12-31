#include <openssl/rsa.h>
#include <openssl/evp.h>
#include <cryptopp/rsa.h>
#include <botan/rsa.h>

void openssl_rsa_new() {
    RSA *rsa = RSA_new();
}

void openssl_rsa_generate_key_ex() {
    RSA *rsa = RSA_new();
    BIGNUM *e = BN_new();
    BN_set_word(e, RSA_F4);
    RSA_generate_key_ex(rsa, 2048, e, NULL);
}

void openssl_rsa_public_encrypt() {
    RSA *rsa = RSA_new();
    unsigned char from[256];
    unsigned char to[256];
    RSA_public_encrypt(256, from, to, rsa, RSA_PKCS1_PADDING);
}

void openssl_rsa_private_decrypt() {
    RSA *rsa = RSA_new();
    unsigned char from[256];
    unsigned char to[256];
    RSA_private_decrypt(256, from, to, rsa, RSA_PKCS1_PADDING);
}

void openssl_rsa_sign() {
    RSA *rsa = RSA_new();
    unsigned char m[32];
    unsigned char sigret[256];
    unsigned int siglen;
    RSA_sign(NID_sha256, m, 32, sigret, &siglen, rsa);
}

void openssl_rsa_verify() {
    RSA *rsa = RSA_new();
    unsigned char m[32];
    unsigned char sigbuf[256];
    RSA_verify(NID_sha256, m, 32, sigbuf, 256, rsa);
}

void openssl_evp_pkey_rsa() {
    RSA *rsa = RSA_new();
    EVP_PKEY *pkey = EVP_PKEY_new_RSA(rsa);
}

void openssl_evp_pkey_keygen() {
    EVP_PKEY_CTX *ctx = EVP_PKEY_CTX_new_id(EVP_PKEY_RSA, NULL);
    EVP_PKEY_keygen_init(ctx);
    EVP_PKEY *pkey = NULL;
    EVP_PKEY_keygen(ctx, &pkey);
}

void cryptopp_rsa_private_key() {
    CryptoPP::RSA::PrivateKey privKey;
}

void cryptopp_rsa_public_key() {
    CryptoPP::RSA::PublicKey pubKey;
}

void cryptopp_rsaes_oaep_encryptor() {
    CryptoPP::RSA::PublicKey pubKey;
    CryptoPP::RSAES_OAEP_SHA_Encryptor encryptor(pubKey);
}

void cryptopp_rsass_pss_signer() {
    CryptoPP::RSA::PrivateKey privKey;
    CryptoPP::RSASS<CryptoPP::PSS, CryptoPP::SHA256>::Signer signer(privKey);
}

void botan_rsa_private_key() {
    Botan::AutoSeeded_RNG rng;
    Botan::RSA_PrivateKey key(rng, 2048);
}

void botan_rsa_public_key() {
    Botan::BigInt n, e;
    Botan::RSA_PublicKey key(n, e);
}
