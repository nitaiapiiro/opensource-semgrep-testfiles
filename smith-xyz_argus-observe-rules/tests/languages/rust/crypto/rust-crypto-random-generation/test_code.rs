use ring::rand;
use rand::{RngCore, Rng, thread_rng, rngs::{OsRng, ThreadRng, StdRng}};
use getrandom;
use ring::aead;
use openssl::rsa;
use rsa;
use ed25519_dalek;
use x25519_dalek;
use ring::agreement;

fn ring_patterns() {
    // Pattern: ring::rand::SystemRandom::new() - Line 7
    let rng1 = rand::SystemRandom::new();

    // Pattern: ring::rand::generate($RNG) - Line 10
    let _bytes1 = rand::generate(&rng1);

    // Pattern: ring::rand::SecureRandom::fill($RNG, $BUF) - Line 13
    let mut buf = [0u8; 32];
    rng1.fill(&mut buf).unwrap();

    // Pattern: let $RNG = ring::rand::SystemRandom::new(); let $BYTES = ring::rand::generate(&$RNG); - Line 17
    let rng2 = rand::SystemRandom::new();
    let _bytes2 = rand::generate(&rng2);

    // Pattern: use ring::rand; let $RNG = rand::SystemRandom::new(); rand::generate(&$RNG, $OUTPUT) - Line 21
    let rng3 = rand::SystemRandom::new();
    let mut output = [0u8; 32];
    rand::generate(&mut output, &rng3).unwrap();
}

fn getrandom_patterns() {
    // Pattern: getrandom::getrandom($BUF) - Line 30
    let mut buf = [0u8; 32];
    getrandom::getrandom(&mut buf).unwrap();

    // Pattern: getrandom::fill($BUF) - Line 34
    getrandom::fill(&mut buf).unwrap();
}

fn openssl_patterns() {
    // Pattern: openssl::rand::rand_bytes($BUF) - Line 40
    let mut buf = [0u8; 32];
    openssl::rand::rand_bytes(&mut buf).unwrap();

    // Pattern: openssl::rand::rand_priv_bytes($BUF) - Line 44
    openssl::rand::rand_priv_bytes(&mut buf).unwrap();

    // Pattern: use openssl::rand; rand::rand_bytes($BUF) - Line 47
    use openssl::rand;
    rand::rand_bytes(&mut buf).unwrap();
}

fn rand_crate_patterns() {
    // Pattern: rand::thread_rng() - Line 53
    let mut rng1 = thread_rng();

    // Pattern: rand::random() - Line 56
    let _value: u32 = rand::random();

    // Pattern: rand::rngs::ThreadRng::default() - Line 59
    let mut rng2 = ThreadRng::default();

    // Pattern: use rand::RngCore; let mut $RNG = rand::thread_rng(); $RNG.fill_bytes($BUF) - Line 62
    use rand::RngCore;
    let mut rng3 = thread_rng();
    let mut buf = [0u8; 32];
    rng3.fill_bytes(&mut buf);
}

fn taint_mode_sinks() {
    let key_bytes = [0u8; 32];
    let nonce = aead::Nonce::assume_unique_for_key([0u8; 12]);
    let aad = aead::Aad::empty();
    let mut in_out = vec![0u8; 100];

    // Taint sink: ring::aead::SealingKey::new($ALGO, $KEY) where $KEY comes from RNG - Line 72
    let rng = rand::SystemRandom::new();
    let mut key = [0u8; 32];
    rand::generate(&mut key, &rng).unwrap();
    let sealing_key = aead::SealingKey::new(&aead::AES_256_GCM, &key);
    aead::seal_in_place(&sealing_key, nonce, aad, &mut in_out).unwrap();

    // Taint sink: openssl::rsa::Rsa::generate($BITS) - Line 79
    let rng2 = rand::SystemRandom::new();
    openssl::rsa::Rsa::generate(2048).unwrap();

    // Taint sink: rsa::RsaPrivateKey::new($RNG, $BITS) - Line 82
    let mut rng3 = thread_rng();
    rsa::RsaPrivateKey::new(&mut rng3, 2048).unwrap();

    // Taint sink: ed25519_dalek::SigningKey::generate($RNG) - Line 85
    let mut rng4 = thread_rng();
    ed25519_dalek::SigningKey::generate(&mut rng4);

    // Taint sink: x25519_dalek::StaticSecret::new($RNG) - Line 88
    let mut rng5 = thread_rng();
    x25519_dalek::StaticSecret::new(&mut rng5);

    // Taint sink: ring::agreement::EphemeralPrivateKey::generate($ALGO, $RNG) - Line 91
    let rng6 = rand::SystemRandom::new();
    agreement::EphemeralPrivateKey::generate(&agreement::X25519, &rng6).unwrap();
}

async fn async_patterns() {
    // Pattern: async fn $FUNC() { ring::rand::SystemRandom::new(); ring::rand::generate(...) } - Line 96
    let rng = rand::SystemRandom::new();
    let mut output = [0u8; 32];
    rand::generate(&mut output, &rng).unwrap();
}
