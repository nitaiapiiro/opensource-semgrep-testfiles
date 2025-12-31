use ring::aead;
use ring::hmac;
use openssl::symm;
use aes::Aes256;
use aes_gcm::Aes256Gcm;

fn string_key_patterns() {
    // Pattern: let $KEY = "$VALUE"; ... ring::aead::SealingKey::new(&ring::aead::AES_256_GCM, $KEY.as_bytes()) - Line 9
    let key = "secret_key_12345";
    let sealing_key = aead::SealingKey::new(&aead::AES_256_GCM, key.as_bytes());

    // Pattern: let $KEY_STR = "$VALUE"; openssl::symm::encrypt($CIPHER, $KEY_STR.as_bytes(), $IV, $DATA) - Line 13
    let key_str = "my_secret_key";
    let cipher = symm::Cipher::aes_256_cbc();
    let iv = [0u8; 16];
    let _ciphertext = symm::encrypt(cipher, key_str.as_bytes(), Some(&iv), b"data");
}

fn const_static_patterns() {
    // Pattern: const KEY: &[u8] = b"$VALUE"; ... ring::aead::SealingKey::new(&ring::aead::AES_256_GCM, KEY) - Line 20
    const KEY: &[u8] = b"hardcoded_secret_key_32bytes!!";
    let sealing_key = aead::SealingKey::new(&aead::AES_256_GCM, KEY);

    // Pattern: static KEY: &[u8] = b"$VALUE"; ... ring::aead::SealingKey::new(&ring::aead::AES_256_GCM, KEY) - Line 24
    static STATIC_KEY: &[u8] = b"another_hardcoded_key_32bytes!!";
    let sealing_key2 = aead::SealingKey::new(&aead::AES_256_GCM, STATIC_KEY);

    // Pattern: const SECRET_KEY: &str = "$VALUE"; ... ring::aead::SealingKey::new(&ring::aead::AES_256_GCM, SECRET_KEY.as_bytes()) - Line 28
    const SECRET_KEY: &str = "hardcoded_string_key_32bytes_long!!";
    let sealing_key3 = aead::SealingKey::new(&aead::AES_256_GCM, SECRET_KEY.as_bytes());
}

fn direct_byte_literal_patterns() {
    // Pattern: ring::aead::SealingKey::new(&ring::aead::AES_256_GCM, b"$KEY") - Line 34
    let sealing_key1 = aead::SealingKey::new(&aead::AES_256_GCM, b"direct_hardcoded_key_32bytes!!");

    // Pattern: ring::aead::OpeningKey::new(&ring::aead::AES_256_GCM, b"$KEY") - Line 37
    let opening_key1 = aead::OpeningKey::new(&aead::AES_256_GCM, b"another_direct_key_32bytes_long!!");

    // Pattern: ring::hmac::Key::new(&ring::hmac::HMAC_SHA256, b"$KEY") - Line 40
    let hmac_key = hmac::Key::new(&hmac::HMAC_SHA256, b"hmac_secret_key");

    // Pattern: openssl::symm::encrypt($CIPHER, b"$KEY", $IV, $DATA) - Line 43
    let cipher = symm::Cipher::aes_256_cbc();
    let iv = [0u8; 16];
    let _ciphertext = symm::encrypt(cipher, b"openssl_hardcoded_key", Some(&iv), b"data");

    // Pattern: openssl::symm::decrypt($CIPHER, b"$KEY", $IV, $DATA) - Line 47
    let _plaintext = symm::decrypt(cipher, b"decrypt_hardcoded_key", Some(&iv), b"data");

    // Pattern: aes::Aes256::new(b"$KEY") - Line 50
    let _cipher1 = Aes256::new(b"aes256_hardcoded_key_32bytes!!".into());

    // Pattern: aes_gcm::Aes256Gcm::new(b"$KEY") - Line 53
    let _cipher2 = Aes256Gcm::new(b"aes_gcm_hardcoded_key_32bytes!!".into());
}
