use ring::hmac;
use hmac::{Hmac, Mac};
use sha2::Sha256;
use sha2::Sha512;
use openssl::sign::Signer;
use openssl::hash::MessageDigest;

fn ring_hmac_patterns() {
    let key = b"secret key";
    let message = b"message";

    // Pattern: ring::hmac::sign($KEY, $MESSAGE) - Line 11
    let _sig1 = hmac::sign(key, message);

    // Pattern: ring::hmac::verify($KEY, $MESSAGE, $SIGNATURE) - Line 14
    let sig = hmac::sign(key, message);
    let _verified = hmac::verify(key, message, sig.as_ref());

    // Pattern: ring::hmac::Key::new($ALGO, $KEY) - Line 18
    let hmac_key1 = hmac::Key::new(hmac::HMAC_SHA256, key);

    // Pattern: ring::hmac::HMAC_SHA256 - Line 21
    let algo1 = hmac::HMAC_SHA256;

    // Pattern: ring::hmac::HMAC_SHA384 - Line 24
    let algo2 = hmac::HMAC_SHA384;

    // Pattern: ring::hmac::HMAC_SHA512 - Line 27
    let algo3 = hmac::HMAC_SHA512;

    // Pattern: ring::hmac::HMAC_SHA1_FOR_LEGACY_USE_ONLY - Line 30
    let algo4 = hmac::HMAC_SHA1_FOR_LEGACY_USE_ONLY;

    // Pattern: let $HMAC_KEY = ring::hmac::Key::new(&ring::hmac::HMAC_SHA256, $KEY); let $SIGNATURE = ring::hmac::sign(&$HMAC_KEY, $MESSAGE); - Line 33
    let hmac_key2 = hmac::Key::new(&hmac::HMAC_SHA256, key);
    let _sig2 = hmac::sign(&hmac_key2, message);

    // Pattern: let $HMAC_KEY = ring::hmac::Key::new(&ring::hmac::HMAC_SHA256, $KEY); ring::hmac::verify(&$HMAC_KEY, $MESSAGE, $SIGNATURE) - Line 37
    let hmac_key3 = hmac::Key::new(&hmac::HMAC_SHA256, key);
    let sig3 = hmac::sign(&hmac_key3, message);
    let _verified2 = hmac::verify(&hmac_key3, message, sig3.as_ref());

    // Pattern: use ring::hmac; let $KEY = hmac::Key::new(hmac::HMAC_SHA256, $KEY_BYTES); - Line 41
    let hmac_key4 = hmac::Key::new(hmac::HMAC_SHA256, key);
}

fn hmac_crate_patterns() {
    let key = b"secret key";
    let message = b"message";

    // Pattern: hmac::Hmac::<sha2::Sha256>::new($KEY) - Line 48
    let mut mac1 = Hmac::<Sha256>::new_from_slice(key).unwrap();

    // Pattern: hmac::Hmac::<sha2::Sha512>::new($KEY) - Line 51
    let mut mac2 = Hmac::<Sha512>::new_from_slice(key).unwrap();

    // Pattern: use hmac::{Hmac, Mac}; let mut $MAC = Hmac::<sha2::Sha256>::new($KEY); $MAC.update($MESSAGE); $MAC.finalize() - Line 54
    let mut mac3 = Hmac::<Sha256>::new_from_slice(key).unwrap();
    mac3.update(message);
    let _result = mac3.finalize();
}

fn openssl_hmac_patterns() {
    let key = openssl::pkey::PKey::hmac(b"secret").unwrap();

    // Pattern: openssl::sign::Signer::new(openssl::hash::MessageDigest::sha256(), $KEY) - Line 62
    let mut signer1 = Signer::new(MessageDigest::sha256(), &key).unwrap();

    // Pattern: let mut $SIGNER = openssl::sign::Signer::new(openssl::hash::MessageDigest::sha256(), $KEY); $SIGNER.update($MESSAGE); $SIGNER.sign_to_vec() - Line 65
    let mut signer2 = Signer::new(MessageDigest::sha256(), &key).unwrap();
    signer2.update(b"message").unwrap();
    let _sig = signer2.sign_to_vec().unwrap();
}
