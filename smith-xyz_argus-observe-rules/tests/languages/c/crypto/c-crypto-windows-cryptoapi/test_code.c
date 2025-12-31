#include <windows.h>
#include <wincrypt.h>

void crypt_acquire_context() {
    HCRYPTPROV hProv;
    CryptAcquireContext(&hProv, NULL, NULL, PROV_RSA_FULL, 0);
    CryptReleaseContext(hProv, 0);
}

void crypt_gen_key() {
    HCRYPTPROV hProv;
    HCRYPTKEY hKey;
    CryptAcquireContext(&hProv, NULL, NULL, PROV_RSA_FULL, 0);
    CryptGenKey(hProv, CALG_RC4, 0, &hKey);
    CryptReleaseContext(hProv, 0);
}

void crypt_derive_key() {
    HCRYPTPROV hProv;
    HCRYPTKEY hKey;
    HCRYPTHASH hHash;
    CryptAcquireContext(&hProv, NULL, NULL, PROV_RSA_FULL, 0);
    CryptCreateHash(hProv, CALG_SHA1, 0, 0, &hHash);
    CryptDeriveKey(hProv, CALG_RC4, hHash, 0, &hKey);
    CryptReleaseContext(hProv, 0);
}

void crypt_import_export_key() {
    HCRYPTPROV hProv;
    HCRYPTKEY hKey;
    BYTE pbData[256];
    DWORD dwDataLen = 256;
    CryptAcquireContext(&hProv, NULL, NULL, PROV_RSA_FULL, 0);
    CryptImportKey(hProv, pbData, dwDataLen, 0, 0, &hKey);
    CryptExportKey(hKey, 0, 0, 0, pbData, &dwDataLen);
    CryptReleaseContext(hProv, 0);
}

void crypt_encrypt_decrypt() {
    HCRYPTPROV hProv;
    HCRYPTKEY hKey;
    BYTE pbData[256];
    DWORD dwDataLen = 256;
    DWORD dwBufLen = 256;
    CryptAcquireContext(&hProv, NULL, NULL, PROV_RSA_FULL, 0);
    CryptGenKey(hProv, CALG_RC4, 0, &hKey);
    CryptEncrypt(hKey, 0, TRUE, 0, pbData, &dwDataLen, dwBufLen);
    CryptDecrypt(hKey, 0, TRUE, 0, pbData, &dwDataLen);
    CryptReleaseContext(hProv, 0);
}

void crypt_create_hash() {
    HCRYPTPROV hProv;
    HCRYPTHASH hHash;
    CryptAcquireContext(&hProv, NULL, NULL, PROV_RSA_FULL, 0);
    CryptCreateHash(hProv, CALG_SHA1, 0, 0, &hHash);
    CryptReleaseContext(hProv, 0);
}

void crypt_hash_data() {
    HCRYPTPROV hProv;
    HCRYPTHASH hHash;
    BYTE pbData[] = "test data";
    DWORD dwDataLen = sizeof(pbData) - 1;
    CryptAcquireContext(&hProv, NULL, NULL, PROV_RSA_FULL, 0);
    CryptCreateHash(hProv, CALG_SHA1, 0, 0, &hHash);
    CryptHashData(hHash, pbData, dwDataLen, 0);
    CryptReleaseContext(hProv, 0);
}

void crypt_hash_session_key() {
    HCRYPTPROV hProv;
    HCRYPTHASH hHash;
    HCRYPTKEY hKey;
    CryptAcquireContext(&hProv, NULL, NULL, PROV_RSA_FULL, 0);
    CryptCreateHash(hProv, CALG_SHA1, 0, 0, &hHash);
    CryptGenKey(hProv, CALG_RC4, 0, &hKey);
    CryptHashSessionKey(hHash, hKey, 0);
    CryptReleaseContext(hProv, 0);
}

void crypt_get_hash_param() {
    HCRYPTPROV hProv;
    HCRYPTHASH hHash;
    BYTE pbData[256];
    DWORD dwDataLen = 256;
    CryptAcquireContext(&hProv, NULL, NULL, PROV_RSA_FULL, 0);
    CryptCreateHash(hProv, CALG_SHA1, 0, 0, &hHash);
    CryptGetHashParam(hHash, HP_HASHVAL, pbData, &dwDataLen, 0);
    CryptReleaseContext(hProv, 0);
}

void crypt_sign_hash() {
    HCRYPTPROV hProv;
    HCRYPTHASH hHash;
    BYTE pbSignature[256];
    DWORD dwSigLen = 256;
    CryptAcquireContext(&hProv, NULL, NULL, PROV_RSA_FULL, 0);
    CryptCreateHash(hProv, CALG_SHA1, 0, 0, &hHash);
    CryptSignHash(hHash, AT_KEYEXCHANGE, NULL, 0, pbSignature, &dwSigLen);
    CryptReleaseContext(hProv, 0);
}

void crypt_verify_signature() {
    HCRYPTPROV hProv;
    HCRYPTHASH hHash;
    BYTE pbSignature[256];
    DWORD dwSigLen = 256;
    HCRYPTKEY hKey;
    CryptAcquireContext(&hProv, NULL, NULL, PROV_RSA_FULL, 0);
    CryptCreateHash(hProv, CALG_SHA1, 0, 0, &hHash);
    CryptGenKey(hProv, CALG_RSA_KEYX, 0, &hKey);
    CryptVerifySignature(hHash, pbSignature, dwSigLen, hKey, NULL, 0);
    CryptReleaseContext(hProv, 0);
}

void crypt_gen_random() {
    HCRYPTPROV hProv;
    BYTE pbBuffer[32];
    DWORD dwLen = 32;
    CryptAcquireContext(&hProv, NULL, NULL, PROV_RSA_FULL, 0);
    CryptGenRandom(hProv, dwLen, pbBuffer);
    CryptReleaseContext(hProv, 0);
}
