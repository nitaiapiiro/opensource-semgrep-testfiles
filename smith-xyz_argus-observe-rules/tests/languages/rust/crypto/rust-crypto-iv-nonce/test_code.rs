use ring::aead;
use openssl::symm;
use cbc::{Encryptor, Decryptor};
use cbc::cipher::KeyInit;
use aes::Aes256;

fn ring_nonce_patterns() {
    let key = [0u8; 32];
    let nonce_bytes = [0u8; 12];
    let aad = b"additional data";
    let mut in_out = vec![0u8; 100];

    // Pattern: ring::aead::Nonce::assume_unique_for_key($BYTES) - Line 9
    let nonce = aead::Nonce::assume_unique_for_key(nonce_bytes);

    // Pattern: ring::aead::Nonce::try_assume_unique_for_key($BYTES) - Line 12
    let nonce2 = aead::Nonce::try_assume_unique_for_key(nonce_bytes).unwrap();

    // Pattern: let $NONCE = ring::aead::Nonce::assume_unique_for_key([0u8; 12]); ring::aead::seal_in_place($KEY, $NONCE, $AAD, $PLAINTEXT, $OUT) - Line 15
    let nonce3 = aead::Nonce::assume_unique_for_key([0u8; 12]);
    let sealing_key = aead::SealingKey::new(&aead::CHACHA20_POLY1305, &key);
    aead::seal_in_place(&sealing_key, nonce3, aead::Aad::from(aad), &mut in_out).unwrap();

    // Pattern: static NONCE: [u8; 12] = [0u8; 12]; ring::aead::seal_in_place($KEY, ring::aead::Nonce::assume_unique_for_key(NONCE), $AAD, $PLAINTEXT, $OUT) - Line 20
    static NONCE: [u8; 12] = [0u8; 12];
    let sealing_key2 = aead::SealingKey::new(&aead::CHACHA20_POLY1305, &key);
    aead::seal_in_place(&sealing_key2, aead::Nonce::assume_unique_for_key(NONCE), aead::Aad::from(aad), &mut in_out).unwrap();
}

fn openssl_iv_patterns() {
    let cipher = symm::Cipher::aes_256_cbc();
    let key = b"01234567890123456789012345678901";
    let data = b"test data";

    // Pattern: let $IV = [0u8; 16]; openssl::symm::encrypt($CIPHER, $KEY, Some(&$IV), $DATA) - Line 28
    let iv = [0u8; 16];
    symm::encrypt(cipher, key, Some(&iv), data).unwrap();

    // Pattern: const IV: [u8; 16] = [0u8; 16]; openssl::symm::encrypt($CIPHER, $KEY, Some(&IV), $DATA) - Line 32
    const IV: [u8; 16] = [0u8; 16];
    symm::encrypt(cipher, key, Some(&IV), data).unwrap();
}

fn cbc_iv_patterns() {
    let key = [0u8; 32];

    // Pattern: cbc::Encryptor::<aes::Aes256>::new($KEY, &[0u8; 16]) - Line 39
    let encryptor = Encryptor::<Aes256>::new(&key.into(), &[0u8; 16].into());

    // Pattern: cbc::Decryptor::<aes::Aes256>::new($KEY, &[0u8; 16]) - Line 42
    let decryptor = Decryptor::<Aes256>::new(&key.into(), &[0u8; 16].into());
}

fn predictable_iv_patterns() {
    let cipher = symm::Cipher::aes_256_cbc();
    let key = b"01234567890123456789012345678901";
    let data = b"test data";

    // Pattern: let mut $IV = [0u8; 16]; $IV[0] = 1; openssl::symm::encrypt(...) - Line 52
    let mut iv = [0u8; 16];
    iv[0] = 1;
    symm::encrypt(cipher, key, Some(&iv), data).unwrap();

    // Pattern: let mut $COUNTER = 0u64; let $NONCE = $COUNTER.to_le_bytes(); ring::aead::seal_in_place(...) - Line 57
    let mut counter = 0u64;
    let nonce_bytes = counter.to_le_bytes();
    let nonce = aead::Nonce::assume_unique_for_key(nonce_bytes);
    let sealing_key = aead::SealingKey::new(&aead::AES_256_GCM, &[0u8; 32]);
    let mut in_out = vec![0u8; 100];
    aead::seal_in_place(&sealing_key, nonce, aead::Aad::empty(), &mut in_out).unwrap();

    // Pattern: let mut $COUNTER = 0u32; let $IV = $COUNTER.to_le_bytes(); openssl::symm::encrypt(...) - Line 64
    let mut counter2 = 0u32;
    let iv2 = counter2.to_le_bytes();
    symm::encrypt(cipher, key, Some(&iv2), data).unwrap();
}
