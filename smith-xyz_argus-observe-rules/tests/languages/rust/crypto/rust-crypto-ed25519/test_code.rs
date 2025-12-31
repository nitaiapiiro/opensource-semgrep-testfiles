use ring::signature;
use ed25519_dalek::{SigningKey, VerifyingKey, Signer, Verifier};
use rand::rngs::OsRng;

fn ring_patterns() {
    let private_key = [0u8; 32];
    let public_key = [0u8; 32];
    let message = b"test message";

    // Pattern: ring::signature::ED25519 - Line 9
    let algo = signature::ED25519;

    // Pattern: ring::signature::sign(&ring::signature::ED25519, $PRIVATE_KEY, $MESSAGE) - Line 12
    let _sig1 = signature::sign(&signature::ED25519, &private_key, message);

    // Pattern: ring::signature::verify(&ring::signature::ED25519, $PUBLIC_KEY, $MESSAGE, $SIGNATURE) - Line 15
    let sig = signature::sign(&signature::ED25519, &private_key, message);
    let _verified = signature::verify(&signature::ED25519, &public_key, message, sig.as_ref());

    // Pattern: let $SIGNATURE = ring::signature::sign(&ring::signature::ED25519, $PRIVATE_KEY, $MESSAGE); ring::signature::verify(...) - Line 19
    let sig2 = signature::sign(&signature::ED25519, &private_key, message);
    let _verified2 = signature::verify(&signature::ED25519, &public_key, message, sig2.as_ref());
}

fn ed25519_dalek_patterns() {
    let bytes = [0u8; 32];
    let mut rng = OsRng;
    let message = b"test message";

    // Pattern: ed25519_dalek::SigningKey::from_bytes($BYTES) - Line 30
    let _signing_key1 = SigningKey::from_bytes(&bytes);

    // Pattern: ed25519_dalek::VerifyingKey::from_bytes($BYTES) - Line 33
    let _verifying_key1 = VerifyingKey::from_bytes(&bytes);

    // Pattern: ed25519_dalek::SigningKey::from_bytes($BYTES)? - Line 36
    let _signing_key2 = SigningKey::from_bytes(&bytes)?;

    // Pattern: ed25519_dalek::VerifyingKey::from_bytes($BYTES)? - Line 39
    let _verifying_key2 = VerifyingKey::from_bytes(&bytes)?;

    // Pattern: ed25519_dalek::SigningKey::from_bytes($BYTES).unwrap() - Line 42
    let signing_key3 = SigningKey::from_bytes(&bytes).unwrap();

    // Pattern: ed25519_dalek::VerifyingKey::from_bytes($BYTES).unwrap() - Line 45
    let verifying_key3 = VerifyingKey::from_bytes(&bytes).unwrap();

    // Pattern: ed25519_dalek::SigningKey::generate($RNG) - Line 48
    let signing_key4 = SigningKey::generate(&mut rng);

    // Pattern: use ed25519_dalek::{SigningKey, Signer}; let $SIGNING_KEY = SigningKey::generate($RNG); let $SIGNATURE = $SIGNING_KEY.sign($MESSAGE); - Line 51
    let signing_key5 = SigningKey::generate(&mut rng);
    let _sig = signing_key5.sign(message);

    // Pattern: use ed25519_dalek::{VerifyingKey, Verifier}; let $VERIFYING_KEY = VerifyingKey::from_bytes($BYTES); $VERIFYING_KEY.verify($MESSAGE, $SIGNATURE) - Line 55
    let verifying_key4 = VerifyingKey::from_bytes(&bytes).unwrap();
    let sig = signing_key3.sign(message);
    let _verified = verifying_key4.verify(message, &sig);
}
