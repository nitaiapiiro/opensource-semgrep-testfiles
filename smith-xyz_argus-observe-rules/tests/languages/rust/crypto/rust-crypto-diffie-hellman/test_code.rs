use openssl::dh::{Dh, DhParams};
use x25519_dalek::{StaticSecret, PublicKey};
use ring::agreement;
use ring::rand::SystemRandom;

fn openssl_dh_patterns() {
    // Pattern: openssl::dh::Dh::from_params($P, $G) - Line 6
    let p = b"p";
    let g = b"g";
    let _dh1 = Dh::from_params(p, g);

    // Pattern: openssl::dh::Dh::from_pqg($P, $Q, $G) - Line 10
    let q = b"q";
    let _dh2 = Dh::from_pqg(p, q, g);

    // Pattern: openssl::dh::Dh::get_2048_256() - Line 14
    let dh3 = Dh::get_2048_256().unwrap();

    // Pattern: openssl::dh::Dh::get_3072_256() - Line 17
    let dh4 = Dh::get_3072_256().unwrap();

    // Pattern: openssl::dh::DhParams::get_2048_256() - Line 20
    let _params1 = DhParams::get_2048_256().unwrap();

    // Pattern: openssl::dh::DhParams::get_3072_256() - Line 23
    let _params2 = DhParams::get_3072_256().unwrap();

    // Pattern: let $DH = openssl::dh::Dh::get_2048_256(); let $PUBLIC_KEY = $DH.generate_key(); - Line 26
    let dh5 = Dh::get_2048_256().unwrap();
    let _public_key = dh5.generate_key().unwrap();

    // Pattern: let $DH = openssl::dh::Dh::get_2048_256(); let $PUBLIC_KEY = $DH.generate_key(); let $SHARED_SECRET = $DH.compute_key($PEER_PUBLIC_KEY); - Line 30
    let dh6 = Dh::get_2048_256().unwrap();
    let public_key = dh6.generate_key().unwrap();
    let peer_public_key = b"peer public key";
    let _shared_secret = dh6.compute_key(peer_public_key).unwrap();
}

fn x25519_patterns() {
    let rng = SystemRandom::new();
    let bytes = [0u8; 32];
    let peer_public_key_bytes = [0u8; 32];

    // Pattern: x25519_dalek::PublicKey::from($BYTES) - Line 40
    let public_key = PublicKey::from(bytes);

    // Pattern: x25519_dalek::StaticSecret::new($RNG) - Line 43
    let secret = StaticSecret::new(&mut rng);

    // Pattern: x25519_dalek::StaticSecret::from($BYTES) - Line 46
    let secret2 = StaticSecret::from(bytes);

    // Pattern: x25519_dalek::StaticSecret::diffie_hellman($SECRET, $PUBLIC_KEY) - Line 49
    let secret3 = StaticSecret::from(bytes);
    let public_key2 = PublicKey::from(peer_public_key_bytes);
    let _shared_secret = secret3.diffie_hellman(&public_key2);

    // Pattern: use x25519_dalek::{StaticSecret, PublicKey}; let $SECRET = StaticSecret::new($RNG); let $PUBLIC_KEY = PublicKey::from(&$SECRET); $SECRET.diffie_hellman(&$PEER_PUBLIC_KEY) - Line 54
    let secret4 = StaticSecret::new(&mut rng);
    let public_key3 = PublicKey::from(&secret4);
    let peer_public_key2 = PublicKey::from(peer_public_key_bytes);
    let _shared_secret2 = secret4.diffie_hellman(&peer_public_key2);
}

fn ring_agreement_patterns() {
    let rng = SystemRandom::new();
    let private_key = b"private key";
    let public_key_bytes = [0u8; 32];
    let mut output = [0u8; 32];

    // Pattern: ring::agreement::agree_ephemeral($ALGO, $PRIVATE_KEY, $PUBLIC_KEY, $OUTPUT) - Line 64
    let algo = &agreement::X25519;
    let public_key = agreement::UnparsedPublicKey::new(algo, &public_key_bytes);
    agreement::agree_ephemeral(algo, private_key, &public_key, &mut output).unwrap();

    // Pattern: ring::agreement::EphemeralPrivateKey::generate($ALGO, $RNG) - Line 69
    let private_key2 = agreement::EphemeralPrivateKey::generate(algo, &rng).unwrap();

    // Pattern: ring::agreement::UnparsedPublicKey::new($ALGO, $PUBLIC_KEY_BYTES) - Line 72
    let public_key2 = agreement::UnparsedPublicKey::new(algo, &public_key_bytes);

    // Pattern: ring::agreement::X25519 - Line 75
    let algo2 = &agreement::X25519;

    // Pattern: ring::agreement::P256 - Line 78
    let algo3 = &agreement::P256;

    // Pattern: ring::agreement::P384 - Line 81
    let algo4 = &agreement::P384;

    // Pattern: use ring::agreement; let $PRIVATE_KEY = agreement::EphemeralPrivateKey::generate(&agreement::X25519, $RNG); agreement::agree_ephemeral($PRIVATE_KEY, $PUBLIC_KEY, $OUTPUT) - Line 84
    let private_key3 = agreement::EphemeralPrivateKey::generate(&agreement::X25519, &rng).unwrap();
    let public_key3 = agreement::UnparsedPublicKey::new(&agreement::X25519, &public_key_bytes);
    agreement::agree_ephemeral(&private_key3, &public_key3, &mut output).unwrap();
}
