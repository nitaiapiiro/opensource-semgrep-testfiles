#include <openssl/ssl.h>
#include <openssl/evp.h>
#include <gcrypt.h>
#include <windows.h>
#include <wincrypt.h>
#include <bcrypt.h>

void openssl_with_libgcrypt() {
    SSL_library_init();
    gcry_cipher_hd_t handle;
    gcry_cipher_open(&handle, GCRY_CIPHER_AES256, GCRY_CIPHER_MODE_CBC, 0);
    gcry_cipher_close(handle);
}

void openssl_with_windows_cryptoapi() {
    OPENSSL_init_crypto(0, NULL);
    HCRYPTPROV hProv = 0;
    CryptAcquireContext(&hProv, NULL, NULL, PROV_RSA_FULL, 0);
    CryptReleaseContext(hProv, 0);
}

void openssl_with_windows_cng() {
    OpenSSL_add_all_algorithms();
    BCRYPT_ALG_HANDLE hAlg = NULL;
    BCryptOpenAlgorithmProvider(&hAlg, BCRYPT_AES_ALGORITHM, NULL, 0);
    BCryptCloseAlgorithmProvider(hAlg, 0);
}

void mixed_crypto_libraries() {
    EVP_CIPHER_CTX *ctx = EVP_CIPHER_CTX_new();
    gcry_cipher_hd_t gcrypt_handle;
    BCRYPT_ALG_HANDLE cng_handle = NULL;

    EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, NULL, NULL);
    gcry_cipher_open(&gcrypt_handle, GCRY_CIPHER_AES256, GCRY_CIPHER_MODE_CBC, 0);
    BCryptOpenAlgorithmProvider(&cng_handle, BCRYPT_AES_ALGORITHM, NULL, 0);

    EVP_CIPHER_CTX_free(ctx);
    gcry_cipher_close(gcrypt_handle);
    BCryptCloseAlgorithmProvider(cng_handle, 0);
}
