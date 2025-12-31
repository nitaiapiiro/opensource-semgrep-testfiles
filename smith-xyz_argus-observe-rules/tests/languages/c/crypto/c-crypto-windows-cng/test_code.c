#include <windows.h>
#include <bcrypt.h>

void bcrypt_open_algorithm() {
    BCRYPT_ALG_HANDLE hAlg = NULL;
    BCryptOpenAlgorithmProvider(&hAlg, BCRYPT_AES_ALGORITHM, NULL, 0);
    BCryptCloseAlgorithmProvider(hAlg, 0);
}

void bcrypt_generate_symmetric_key() {
    BCRYPT_ALG_HANDLE hAlg = NULL;
    BCRYPT_KEY_HANDLE hKey = NULL;
    PUCHAR pbKeyObject = NULL;
    ULONG cbKeyObject = 0;
    PUCHAR pbSecret = NULL;
    ULONG cbSecret = 0;

    BCryptOpenAlgorithmProvider(&hAlg, BCRYPT_AES_ALGORITHM, NULL, 0);
    BCryptGenerateSymmetricKey(hAlg, &hKey, pbKeyObject, cbKeyObject, pbSecret, cbSecret, 0);
    BCryptDestroyKey(hKey);
    BCryptCloseAlgorithmProvider(hAlg, 0);
}

void bcrypt_generate_key_pair() {
    BCRYPT_ALG_HANDLE hAlg = NULL;
    BCRYPT_KEY_HANDLE hKey = NULL;

    BCryptOpenAlgorithmProvider(&hAlg, BCRYPT_RSA_ALGORITHM, NULL, 0);
    BCryptGenerateKeyPair(hAlg, &hKey, 2048, 0);
    BCryptFinalizeKeyPair(hKey, 0);
    BCryptDestroyKey(hKey);
    BCryptCloseAlgorithmProvider(hAlg, 0);
}

void bcrypt_import_export_key() {
    BCRYPT_ALG_HANDLE hAlg = NULL;
    BCRYPT_KEY_HANDLE hKey = NULL;
    PUCHAR pbKeyBlob = NULL;
    ULONG cbKeyBlob = 0;
    ULONG cbResult = 0;

    BCryptOpenAlgorithmProvider(&hAlg, BCRYPT_RSA_ALGORITHM, NULL, 0);
    BCryptImportKeyPair(hAlg, NULL, BCRYPT_RSAPUBLIC_BLOB, &hKey, pbKeyBlob, cbKeyBlob, 0);
    BCryptExportKey(hKey, NULL, BCRYPT_RSAPUBLIC_BLOB, NULL, 0, &cbResult, 0);
    BCryptDestroyKey(hKey);
    BCryptCloseAlgorithmProvider(hAlg, 0);
}

void bcrypt_encrypt_decrypt() {
    BCRYPT_KEY_HANDLE hKey = NULL;
    PUCHAR pbInput = NULL;
    ULONG cbInput = 0;
    PUCHAR pbIV = NULL;
    ULONG cbIV = 0;
    PUCHAR pbOutput = NULL;
    ULONG cbOutput = 0;
    ULONG cbResult = 0;

    BCryptEncrypt(hKey, pbInput, cbInput, NULL, pbIV, cbIV, pbOutput, cbOutput, &cbResult, 0);
    BCryptDecrypt(hKey, pbInput, cbInput, NULL, pbIV, cbIV, pbOutput, cbOutput, &cbResult, 0);
}

void bcrypt_gen_random() {
    BCRYPT_ALG_HANDLE hProv = NULL;
    PUCHAR pbBuffer = NULL;
    ULONG cbBuffer = 32;

    BCryptOpenAlgorithmProvider(&hProv, BCRYPT_RNG_ALGORITHM, NULL, 0);
    BCryptGenRandom(hProv, pbBuffer, cbBuffer, 0);
    BCryptCloseAlgorithmProvider(hProv, 0);
}

void bcrypt_create_hash() {
    BCRYPT_ALG_HANDLE hAlg = NULL;
    BCRYPT_HASH_HANDLE hHash = NULL;
    PUCHAR pbHashObject = NULL;
    ULONG cbHashObject = 0;

    BCryptOpenAlgorithmProvider(&hAlg, BCRYPT_SHA256_ALGORITHM, NULL, 0);
    BCryptCreateHash(hAlg, &hHash, pbHashObject, cbHashObject, NULL, 0, 0);
    BCryptDestroyHash(hHash);
    BCryptCloseAlgorithmProvider(hAlg, 0);
}

void bcrypt_hash_data() {
    BCRYPT_HASH_HANDLE hHash = NULL;
    PUCHAR pbInput = NULL;
    ULONG cbInput = 0;

    BCryptHashData(hHash, pbInput, cbInput, 0);
}

void bcrypt_finish_hash() {
    BCRYPT_HASH_HANDLE hHash = NULL;
    PUCHAR pbOutput = NULL;
    ULONG cbOutput = 0;

    BCryptFinishHash(hHash, pbOutput, cbOutput, 0);
}

void bcrypt_sign_verify() {
    BCRYPT_KEY_HANDLE hKey = NULL;
    PUCHAR pbHash = NULL;
    ULONG cbHash = 0;
    PUCHAR pbSignature = NULL;
    ULONG cbSignature = 0;
    ULONG cbResult = 0;

    BCryptSignHash(hKey, NULL, pbHash, cbHash, pbSignature, cbSignature, &cbResult, 0);
    BCryptVerifySignature(hKey, NULL, pbHash, cbHash, pbSignature, cbSignature, 0);
}

void bcrypt_derive_key() {
    BCRYPT_SECRET_HANDLE hSecret = NULL;
    PUCHAR pbDerivedKey = NULL;
    ULONG cbDerivedKey = 0;
    ULONG cbResult = 0;

    BCryptDeriveKey(hSecret, NULL, pbDerivedKey, cbDerivedKey, &cbResult, 0);
}

void bcrypt_derive_key_pbkdf2() {
    BCRYPT_ALG_HANDLE hAlg = NULL;
    PUCHAR pbPassword = NULL;
    ULONG cbPassword = 0;
    PUCHAR pbSalt = NULL;
    ULONG cbSalt = 0;
    ULONGLONG cIterations = 10000;
    PUCHAR pbDerivedKey = NULL;
    ULONG cbDerivedKey = 0;

    BCryptOpenAlgorithmProvider(&hAlg, BCRYPT_SHA256_ALGORITHM, NULL, 0);
    BCryptDeriveKeyPBKDF2(hAlg, pbPassword, cbPassword, pbSalt, cbSalt, cIterations, pbDerivedKey, cbDerivedKey, 0);
    BCryptCloseAlgorithmProvider(hAlg, 0);
}
