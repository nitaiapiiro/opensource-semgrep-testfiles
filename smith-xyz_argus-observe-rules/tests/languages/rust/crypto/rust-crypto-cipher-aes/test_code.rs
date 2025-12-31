use ring::aead;
use openssl::symm;
use aes::{Aes256, Aes128, Aes192};
use aes_gcm::{Aes256Gcm, Aes128Gcm, KeyInit};
use cbc::{Encryptor, Decryptor};

fn ring_aead_patterns() {
    let key = [0u8; 32];

    // Pattern: ring::aead::AES_256_GCM - Line 10
    let algo1 = aead::AES_256_GCM;

    // Pattern: ring::aead::AES_128_GCM - Line 13
    let algo2 = aead::AES_128_GCM;

    // Pattern: ring::aead::AES_128_GCM_SIV - Line 16
    let algo3 = aead::AES_128_GCM_SIV;

    // Pattern: ring::aead::AES_256_GCM_SIV - Line 19
    let algo4 = aead::AES_256_GCM_SIV;

    // Pattern: ring::aead::SealingKey::new($ALGO, $KEY) - Line 22
    let sealing_key1 = aead::SealingKey::new(&aead::AES_256_GCM, &key);

    // Pattern: ring::aead::OpeningKey::new($ALGO, $KEY) - Line 25
    let opening_key1 = aead::OpeningKey::new(&aead::AES_256_GCM, &key);

    // Pattern: ring::aead::LessSafeKey::new($ALGO, $KEY) - Line 28
    let less_safe_key = aead::LessSafeKey::new(aead::UnboundKey::new(&aead::AES_256_GCM, &key).unwrap());

    // Pattern: ring::aead::LessSafeKey::seal_in_place($KEY, $NONCE, $AAD, $PLAINTEXT, $OUT) - Line 31
    let nonce2 = aead::Nonce::assume_unique_for_key([0u8; 12]);
    let mut in_out2 = vec![0u8; 100];
    less_safe_key.seal_in_place(nonce2, aead::Aad::empty(), &mut in_out2).unwrap();

    // Pattern: ring::aead::LessSafeKey::open_in_place($KEY, $NONCE, $AAD, $CIPHERTEXT, $OUT) - Line 35
    less_safe_key.open_in_place(nonce2, aead::Aad::empty(), &mut in_out2).unwrap();

    // Pattern: ring::aead::UnboundKey::new($ALGO, $KEY) - Line 38
    let unbound_key = aead::UnboundKey::new(&aead::AES_256_GCM, &key);

    // Pattern: ring::aead::UnboundKey::new_less_safe_key($ALGO, $KEY) - Line 41
    let unbound_key2 = aead::UnboundKey::new_less_safe_key(&aead::AES_256_GCM, &key);

    // Pattern: ring::aead::SealingKey::new_from_unbound_key($UNBOUND_KEY) - Line 44
    let sealing_key3 = aead::SealingKey::new_from_unbound_key(unbound_key);

    // Pattern: ring::aead::OpeningKey::new_from_unbound_key($UNBOUND_KEY) - Line 47
    let opening_key3 = aead::OpeningKey::new_from_unbound_key(unbound_key2);

    // Pattern: seal_in_place pattern - Line 34
    let sealing_key2 = aead::SealingKey::new(&aead::AES_256_GCM, &key);
    let nonce = aead::Nonce::assume_unique_for_key([0u8; 12]);
    let mut in_out = vec![0u8; 16];
    let _ = aead::seal_in_place(&sealing_key2, nonce, aead::Aad::empty(), &mut in_out);

    // Pattern: open_in_place pattern - Line 40
    let opening_key2 = aead::OpeningKey::new(&aead::AES_256_GCM, &key);
    let _ = aead::open_in_place(&opening_key2, nonce, aead::Aad::empty(), &mut in_out);
}

fn openssl_patterns() {
    let key = [0u8; 32];
    let iv = [0u8; 16];
    let data = b"test data";

    // Pattern: openssl::symm::Cipher::aes_128_cbc() - Line 48
    let cipher1 = symm::Cipher::aes_128_cbc();

    // Pattern: openssl::symm::Cipher::aes_128_ecb() - Line 51
    let cipher2 = symm::Cipher::aes_128_ecb();

    // Pattern: openssl::symm::Cipher::aes_128_gcm() - Line 54
    let cipher3 = symm::Cipher::aes_128_gcm();

    // Pattern: openssl::symm::Cipher::aes_192_cbc() - Line 57
    let cipher4 = symm::Cipher::aes_192_cbc();

    // Pattern: openssl::symm::Cipher::aes_256_cbc() - Line 60
    let cipher5 = symm::Cipher::aes_256_cbc();

    // Pattern: openssl::symm::Cipher::aes_256_ecb() - Line 63
    let cipher6 = symm::Cipher::aes_256_ecb();

    // Pattern: openssl::symm::Cipher::aes_256_gcm() - Line 66
    let cipher7 = symm::Cipher::aes_256_gcm();

    // Pattern: openssl::symm::Cipher::aes_256_ctr() - Line 69
    let cipher8 = symm::Cipher::aes_256_ctr();

    // Pattern: openssl::symm::Cipher::aes_256_cfb128() - Line 72
    let cipher9 = symm::Cipher::aes_256_cfb128();

    // Pattern: openssl::symm::Cipher::aes_256_ofb() - Line 75
    let cipher10 = symm::Cipher::aes_256_ofb();

    // Pattern: openssl::symm::Cipher::aes_128_ctr() - Line 78
    let cipher11 = symm::Cipher::aes_128_ctr();

    // Pattern: openssl::symm::Cipher::aes_256_ctr() - Line 81
    let cipher12 = symm::Cipher::aes_256_ctr();

    // Pattern: openssl::symm::Crypter::new($MODE, $CIPHER, $KEY, $IV) - Line 84
    use openssl::symm::Mode;
    let mut crypter = symm::Crypter::new(Mode::Encrypt, cipher5, &key, Some(&iv)).unwrap();

    // Pattern: openssl::symm::Crypter::encrypt($CRYPTER, $DATA) - Line 88
    let mut ciphertext = vec![0u8; data.len() + cipher5.block_size()];
    crypter.encrypt(data, &mut ciphertext).unwrap();

    // Pattern: openssl::symm::Crypter::decrypt($CRYPTER, $DATA) - Line 92
    let mut crypter2 = symm::Crypter::new(Mode::Decrypt, cipher5, &key, Some(&iv)).unwrap();
    let mut plaintext = vec![0u8; ciphertext.len()];
    crypter2.decrypt(&ciphertext, &mut plaintext).unwrap();

    // Pattern: openssl::symm::encrypt($CIPHER, $KEY, $IV, $DATA) - Line 78
    let _ciphertext = symm::encrypt(cipher5, &key, Some(&iv), data);

    // Pattern: openssl::symm::decrypt($CIPHER, $KEY, $IV, $DATA) - Line 81
    let _plaintext = symm::decrypt(cipher5, &key, Some(&iv), data);

    // Pattern: let $CIPHER = openssl::symm::Cipher::aes_256_gcm(); openssl::symm::encrypt(...) - Line 84
    let cipher = symm::Cipher::aes_256_gcm();
    let _ciphertext2 = symm::encrypt(cipher, &key, Some(&iv), data);
}

fn aes_crate_patterns() {
    let key = [0u8; 32];

    // Pattern: aes::Aes256::new($KEY) - Line 92
    let cipher1 = Aes256::new(&key.into());

    // Pattern: aes::Aes128::new($KEY) - Line 95
    let cipher2 = Aes128::new(&key[..16].into());

    // Pattern: aes::Aes192::new($KEY) - Line 98
    let cipher3 = Aes192::new(&key[..24].into());

    // Pattern: aes_gcm::Aes256Gcm::new($KEY) - Line 101
    let cipher4 = Aes256Gcm::new(&key.into());

    // Pattern: aes_gcm::Aes128Gcm::new($KEY) - Line 104
    let cipher5 = Aes128Gcm::new(&key[..16].into());

    // Pattern: use aes_gcm::{Aes256Gcm, KeyInit}; let $CIPHER = Aes256Gcm::new($KEY); $CIPHER.encrypt(...) - Line 107
    let cipher6 = Aes256Gcm::new(&key.into());
    let nonce = [0u8; 12];
    let _ = cipher6.encrypt(&nonce.into(), b"data");
}

async fn async_patterns() {
    let key = [0u8; 32];
    let nonce = aead::Nonce::assume_unique_for_key([0u8; 12]);
    let aad = aead::Aad::empty();
    let mut in_out = vec![0u8; 100];

    // Pattern: async fn $FUNC() { ring::aead::SealingKey::new(...); ring::aead::seal_in_place(...) } - Line 120
    let sealing_key = aead::SealingKey::new(&aead::AES_256_GCM, &key);
    aead::seal_in_place(&sealing_key, nonce, aad, &mut in_out).unwrap();
}

fn cbc_mode_patterns() {
    let key = [0u8; 32];
    let iv = [0u8; 16];

    // Pattern: cbc::Encryptor::<aes::Aes256>::new($KEY, $IV) - Line 116
    let encryptor = Encryptor::<Aes256>::new(&key.into(), &iv.into());

    // Pattern: cbc::Decryptor::<aes::Aes256>::new($KEY, $IV) - Line 119
    let decryptor = Decryptor::<Aes256>::new(&key.into(), &iv.into());
}
