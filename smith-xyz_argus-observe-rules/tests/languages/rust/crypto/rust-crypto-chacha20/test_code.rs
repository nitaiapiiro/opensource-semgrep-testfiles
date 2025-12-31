use ring::aead;
use chacha20::ChaCha20;
use chacha20::ChaCha20Rng;
use chacha20poly1305::{ChaCha20Poly1305, KeyInit, Aead};

fn ring_chacha20_patterns() {
    let key = [0u8; 32];
    let nonce = [0u8; 12];
    let aad = b"additional data";
    let mut in_out = vec![0u8; 100];

    // Pattern: ring::aead::CHACHA20_POLY1305 - Line 9
    let algo = &aead::CHACHA20_POLY1305;

    // Pattern: ring::aead::SealingKey::new(&ring::aead::CHACHA20_POLY1305, $KEY) - Line 12
    let sealing_key = aead::SealingKey::new(&aead::CHACHA20_POLY1305, &key);

    // Pattern: ring::aead::OpeningKey::new(&ring::aead::CHACHA20_POLY1305, $KEY) - Line 15
    let opening_key = aead::OpeningKey::new(&aead::CHACHA20_POLY1305, &key);

    // Pattern: ring::aead::LessSafeKey::new(ring::aead::UnboundKey::new(&ring::aead::CHACHA20_POLY1305, $KEY)) - Line 18
    let unbound_key = aead::UnboundKey::new(&aead::CHACHA20_POLY1305, &key);
    let less_safe_key = aead::LessSafeKey::new(unbound_key);

    // Pattern: let $SEALING_KEY = ring::aead::SealingKey::new(&ring::aead::CHACHA20_POLY1305, $KEY); let $CIPHERTEXT = ring::aead::seal_in_place($SEALING_KEY, $NONCE, $AAD, $PLAINTEXT, $OUT); - Line 22
    let sealing_key2 = aead::SealingKey::new(&aead::CHACHA20_POLY1305, &key);
    let nonce2 = aead::Nonce::assume_unique_for_key(nonce);
    aead::seal_in_place(&sealing_key2, nonce2, aead::Aad::from(aad), &mut in_out).unwrap();

    // Pattern: let $OPENING_KEY = ring::aead::OpeningKey::new(&ring::aead::CHACHA20_POLY1305, $KEY); let $PLAINTEXT = ring::aead::open_in_place($OPENING_KEY, $NONCE, $AAD, $CIPHERTEXT, $OUT); - Line 27
    let opening_key2 = aead::OpeningKey::new(&aead::CHACHA20_POLY1305, &key);
    let nonce3 = aead::Nonce::assume_unique_for_key(nonce);
    aead::open_in_place(&opening_key2, nonce3, aead::Aad::from(aad), &mut in_out).unwrap();
}

fn chacha20_crate_patterns() {
    let key = [0u8; 32];
    let nonce = [0u8; 12];
    let seed = [0u8; 32];

    // Pattern: chacha20::ChaCha20::new($KEY, $NONCE) - Line 36
    let cipher = ChaCha20::new(&key.into(), &nonce.into());

    // Pattern: chacha20::ChaCha20Rng::from_seed($SEED) - Line 39
    let rng = ChaCha20Rng::from_seed(seed);
}

fn chacha20poly1305_patterns() {
    let key = [0u8; 32];
    let nonce = [0u8; 12];
    let plaintext = b"plaintext";

    // Pattern: chacha20poly1305::ChaCha20Poly1305::new_from_slice($KEY) - Line 45
    let cipher = ChaCha20Poly1305::new_from_slice(&key).unwrap();

    // Pattern: use chacha20poly1305::{ChaCha20Poly1305, KeyInit, Aead}; let $CIPHER = ChaCha20Poly1305::new_from_slice($KEY); $CIPHER.encrypt($NONCE, $PLAINTEXT) - Line 48
    let cipher2 = ChaCha20Poly1305::new_from_slice(&key).unwrap();
    let nonce_bytes = nonce.as_slice().try_into().unwrap();
    let _ciphertext = cipher2.encrypt(nonce_bytes, plaintext).unwrap();

    // Pattern: use chacha20poly1305::{ChaCha20Poly1305, KeyInit, Aead}; let $CIPHER = ChaCha20Poly1305::new_from_slice($KEY); $CIPHER.decrypt($NONCE, $CIPHERTEXT) - Line 53
    let cipher3 = ChaCha20Poly1305::new_from_slice(&key).unwrap();
    let ciphertext = b"ciphertext";
    let _plaintext = cipher3.decrypt(nonce_bytes, ciphertext).unwrap();

    // Pattern: use chacha20poly1305::{ChaCha20Poly1305, KeyInit, Aead}; let $CIPHER = ChaCha20Poly1305::new(KeyInit::init($KEY)); $CIPHER.encrypt($NONCE, $PLAINTEXT) - Line 58
    let cipher4 = ChaCha20Poly1305::new(KeyInit::init(&key));
    let _ciphertext2 = cipher4.encrypt(nonce_bytes, plaintext).unwrap();
}
