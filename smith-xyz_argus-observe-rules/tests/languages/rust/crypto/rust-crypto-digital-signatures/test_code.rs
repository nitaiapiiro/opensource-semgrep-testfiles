use ring::signature;
use openssl::sign::{Signer, Verifier};
use openssl::hash::MessageDigest;
use ed25519_dalek::{SigningKey, VerifyingKey};
use rsa::{RsaPrivateKey, RsaPublicKey, PaddingScheme};
use rsa::rand_core::OsRng;

fn ring_signature_patterns() {
    let private_key = b"private key";
    let public_key = b"public key";
    let message = b"message";
    let algo = &signature::ECDSA_P256_SHA256_ASN1_SIGNING;

    // Pattern: ring::signature::sign($ALGO, $PRIVATE_KEY, $MESSAGE) - Line 10
    let sig = signature::sign(algo, private_key, message);

    // Pattern: ring::signature::verify($ALGO, $PUBLIC_KEY, $MESSAGE, $SIGNATURE) - Line 13
    let public_key_unparsed = signature::UnparsedPublicKey::new(algo, public_key);
    signature::verify(&public_key_unparsed, message, sig.as_ref()).unwrap();
}

fn openssl_signature_patterns() {
    let key = b"key";
    let message = b"message";
    let signature_bytes = b"signature";

    // Pattern: openssl::sign::Signer::new($MD, $KEY) - Line 22
    let mut signer = Signer::new(MessageDigest::sha256(), key).unwrap();

    // Pattern: openssl::sign::Verifier::new($MD, $KEY) - Line 25
    let mut verifier = Verifier::new(MessageDigest::sha256(), key).unwrap();

    // Pattern: let mut $SIGNER = openssl::sign::Signer::new($MD, $KEY); $SIGNER.update($MESSAGE); $SIGNER.sign_to_vec() - Line 28
    let mut signer2 = Signer::new(MessageDigest::sha256(), key).unwrap();
    signer2.update(message).unwrap();
    let _sig = signer2.sign_to_vec().unwrap();

    // Pattern: let mut $VERIFIER = openssl::sign::Verifier::new($MD, $KEY); $VERIFIER.update($MESSAGE); $VERIFIER.verify($SIGNATURE) - Line 33
    let mut verifier2 = Verifier::new(MessageDigest::sha256(), key).unwrap();
    verifier2.update(message).unwrap();
    verifier2.verify(signature_bytes).unwrap();
}

fn ed25519_patterns() {
    let message = b"message";
    let signature_bytes = b"signature";
    let mut rng = OsRng;

    // Pattern: ed25519_dalek::SigningKey::sign($MESSAGE) - Line 42
    let signing_key = SigningKey::generate(&mut rng);
    let _sig = signing_key.sign(message);

    // Pattern: ed25519_dalek::VerifyingKey::verify($MESSAGE, $SIGNATURE) - Line 46
    let verifying_key = VerifyingKey::from(&signing_key);
    verifying_key.verify_strict(message, signature_bytes).unwrap();
}

fn rsa_signature_patterns() {
    let mut rng = OsRng;
    let hash = b"hash";
    let signature_bytes = b"signature";

    // Pattern: rsa::RsaPrivateKey::sign($SCHEME, $HASH) - Line 54
    let private_key = RsaPrivateKey::new(&mut rng, 2048).unwrap();
    let _sig = private_key.sign(PaddingScheme::PKCS1v15Sign, hash).unwrap();

    // Pattern: rsa::RsaPublicKey::verify($SCHEME, $HASH, $SIGNATURE) - Line 58
    let public_key = RsaPublicKey::from(&private_key);
    public_key.verify(PaddingScheme::PKCS1v15Sign, hash, signature_bytes).unwrap();
}
