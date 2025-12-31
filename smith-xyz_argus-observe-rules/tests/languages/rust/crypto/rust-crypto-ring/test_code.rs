use ring::digest;
use ring::aead;
use ring::hmac;
use ring::signature;
use ring::rand::{SystemRandom, generate};

fn digest_patterns() {
    let data = b"test data";

    // Pattern: ring::digest::digest($ALGO, $DATA) - Line 8
    let hash1 = digest::digest(&digest::SHA256, data);

    // Pattern: use ring::digest; digest::digest(&digest::SHA256, $DATA) - Line 11
    let hash2 = digest::digest(&digest::SHA256, data);
}

fn aead_patterns() {
    let key = [0u8; 32];
    let nonce = [0u8; 12];
    let aad = b"additional data";
    let mut in_out = vec![0u8; 100];

    // Pattern: ring::aead::seal_in_place($SEALING_KEY, $NONCE, ring::aead::Aad::from($AAD), $IN_OUT) - Line 20
    let sealing_key = aead::SealingKey::new(&aead::CHACHA20_POLY1305, &key);
    let nonce = aead::Nonce::assume_unique_for_key(nonce);
    aead::seal_in_place(&sealing_key, nonce, aead::Aad::from(aad), &mut in_out).unwrap();

    // Pattern: ring::aead::open_in_place($OPENING_KEY, $NONCE, ring::aead::Aad::from($AAD), $IN_OUT) - Line 25
    let opening_key = aead::OpeningKey::new(&aead::CHACHA20_POLY1305, &key);
    let nonce2 = aead::Nonce::assume_unique_for_key(nonce);
    aead::open_in_place(&opening_key, nonce2, aead::Aad::from(aad), &mut in_out).unwrap();

    // Pattern: ring::aead::seal_in_place($SEALING_KEY, $NONCE, ring::aead::Aad::empty(), $IN_OUT) - Line 30
    let sealing_key2 = aead::SealingKey::new(&aead::CHACHA20_POLY1305, &key);
    let nonce3 = aead::Nonce::assume_unique_for_key(nonce);
    aead::seal_in_place(&sealing_key2, nonce3, aead::Aad::empty(), &mut in_out).unwrap();

    // Pattern: ring::aead::open_in_place($OPENING_KEY, $NONCE, ring::aead::Aad::empty(), $IN_OUT) - Line 35
    let opening_key2 = aead::OpeningKey::new(&aead::CHACHA20_POLY1305, &key);
    let nonce4 = aead::Nonce::assume_unique_for_key(nonce);
    aead::open_in_place(&opening_key2, nonce4, aead::Aad::empty(), &mut in_out).unwrap();

    // Pattern: use ring::aead; aead::seal_in_place($KEY, $NONCE, $AAD, $PLAINTEXT, $OUT) - Line 40
    let sealing_key3 = aead::SealingKey::new(&aead::CHACHA20_POLY1305, &key);
    let nonce5 = aead::Nonce::assume_unique_for_key(nonce);
    aead::seal_in_place(&sealing_key3, nonce5, aead::Aad::from(aad), &mut in_out).unwrap();
}

fn hmac_patterns() {
    let key = b"secret key";
    let message = b"message";

    // Pattern: ring::hmac::sign($KEY, $MESSAGE) - Line 50
    let hmac_key = hmac::Key::new(&hmac::HMAC_SHA256, key);
    let signature = hmac::sign(&hmac_key, message);

    // Pattern: ring::hmac::verify($KEY, $MESSAGE, $SIGNATURE) - Line 54
    hmac::verify(&hmac_key, message, signature.as_ref()).unwrap();
}

fn signature_patterns() {
    let private_key = b"private key";
    let public_key = b"public key";
    let message = b"message";

    // Pattern: ring::signature::sign($ALGO, $PRIVATE_KEY, $MESSAGE) - Line 62
    let algo = &signature::ECDSA_P256_SHA256_ASN1_SIGNING;
    let _sig = signature::sign(algo, private_key, message);

    // Pattern: ring::signature::verify($ALGO, $PUBLIC_KEY, $MESSAGE, $SIGNATURE) - Line 66
    let public_key_unparsed = signature::UnparsedPublicKey::new(algo, public_key);
    let signature_bytes = b"signature";
    signature::verify(&public_key_unparsed, message, signature_bytes).unwrap();
}

fn rand_patterns() {
    // Pattern: ring::rand::SystemRandom::new() - Line 73
    let rng = SystemRandom::new();

    // Pattern: ring::rand::generate($RNG, $OUTPUT) - Line 76
    let mut output = [0u8; 32];
    generate(&rng, &mut output).unwrap();
}
