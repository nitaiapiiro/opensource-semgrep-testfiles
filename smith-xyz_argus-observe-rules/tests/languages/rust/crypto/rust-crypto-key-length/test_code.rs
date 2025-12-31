use openssl::rsa::Rsa;
use rsa::{RsaPrivateKey, RsaPublicKey};
use rsa::rand_core::OsRng;

fn openssl_rsa_key_length_patterns() {
    // Pattern: openssl::rsa::Rsa::generate($BITS) with metavariable-regex - Line 6
    let rsa1 = Rsa::generate(1024).unwrap();

    // Pattern: openssl::rsa::Rsa::generate($BITS) with metavariable-pattern and metavariable-regex - Line 9
    let bits: u32 = 2048;
    let rsa2 = Rsa::generate(bits).unwrap();

    // Pattern: openssl::rsa::Rsa::generate($BITS) with metavariable-regex - Line 13
    let rsa3 = Rsa::generate(4096).unwrap();
}

fn rsa_crate_key_length_patterns() {
    let mut rng = OsRng;

    // Pattern: rsa::RsaPrivateKey::new($RNG, $BITS) with metavariable-regex - Line 20
    let private_key1 = RsaPrivateKey::new(&mut rng, 1024).unwrap();

    // Pattern: rsa::RsaPrivateKey::new($RNG, $BITS) with metavariable-pattern and metavariable-regex - Line 23
    let bits: usize = 2048;
    let private_key2 = RsaPrivateKey::new(&mut rng, bits).unwrap();

    // Pattern: rsa::RsaPrivateKey::new($RNG, $BITS) with metavariable-regex - Line 27
    let private_key3 = RsaPrivateKey::new(&mut rng, 4096).unwrap();
}
