use sha1::{Sha1, Digest};
use crypto::digest::Digest as CryptoDigest;
use crypto::sha1::Sha1 as CryptoSha1;
use openssl::hash::{hash, MessageDigest, Hasher};

fn sha1_crate_patterns() {
    let data = b"test data";

    // Pattern: sha1::Sha1::new() - Line 8
    let mut hasher1 = Sha1::new();

    // Pattern: sha1::Sha1::default() - Line 11
    let mut hasher2 = Sha1::default();

    // Pattern: sha1::Sha1::from($DATA) - Line 14
    let hasher3 = Sha1::from(data);

    // Pattern: let mut $HASHER = sha1::Sha1::new(); $HASHER.update($DATA); let $HASH = $HASHER.finalize(); - Line 17
    let mut hasher4 = Sha1::new();
    hasher4.update(data);
    let hash4 = hasher4.finalize();

    // Pattern: use sha1::{Sha1, Digest}; let mut $HASHER = Sha1::new(); $HASHER.update($DATA); $HASHER.finalize() - Line 22
    let mut hasher5 = Sha1::new();
    hasher5.update(data);
    let _hash5 = hasher5.finalize();

    // Pattern: sha1::Sha1::digest($DATA) - Line 26
    let _hash6 = Sha1::digest(data);

    // Pattern: sha1::digest($DATA) - Line 29
    let _hash7 = sha1::digest(data);
}

fn crypto_crate_patterns() {
    let data = b"test data";

    // Pattern: crypto::sha1::Sha1::new() - Line 36
    let mut hasher1 = CryptoSha1::new();

    // Pattern: use crypto::digest::Digest; let mut $HASHER = crypto::sha1::Sha1::new(); $HASHER.input($DATA); $HASHER.result_str() - Line 39
    let mut hasher2 = CryptoSha1::new();
    hasher2.input(data);
    let _result = hasher2.result_str();
}

fn openssl_patterns() {
    let data = b"test data";

    // Pattern: openssl::hash::hash(openssl::hash::MessageDigest::sha1(), $DATA) - Line 52
    let hash1 = hash(MessageDigest::sha1(), data);

    // Pattern: openssl::hash::Hasher::new(openssl::hash::MessageDigest::sha1()) - Line 55
    let mut hasher1 = Hasher::new(MessageDigest::sha1()).unwrap();

    // Pattern: let mut $HASHER = openssl::hash::Hasher::new(...); $HASHER.update($DATA); $HASHER.finish() - Line 58
    let mut hasher2 = Hasher::new(MessageDigest::sha1()).unwrap();
    hasher2.update(data).unwrap();
    let _hash2 = hasher2.finish().unwrap();
}
