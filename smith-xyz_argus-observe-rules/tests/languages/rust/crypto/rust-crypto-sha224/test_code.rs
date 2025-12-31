use sha2::{Sha224, Digest};
use openssl::hash::{hash, MessageDigest, Hasher};

fn sha2_crate_patterns() {
    let data = b"test data";

    // Pattern: sha2::Sha224::new() - Line 6
    let mut hasher1 = Sha224::new();

    // Pattern: sha2::Sha224::default() - Line 9
    let mut hasher2 = Sha224::default();

    // Pattern: sha2::Sha224::from($DATA) - Line 12
    let hasher3 = Sha224::from(data);

    // Pattern: let mut $HASHER = sha2::Sha224::new(); $HASHER.update($DATA); let $HASH = $HASHER.finalize(); - Line 15
    let mut hasher4 = Sha224::new();
    hasher4.update(data);
    let hash4 = hasher4.finalize();

    // Pattern: use sha2::{Sha224, Digest}; let mut $HASHER = Sha224::new(); $HASHER.update($DATA); $HASHER.finalize() - Line 20
    let mut hasher5 = Sha224::new();
    hasher5.update(data);
    let _hash5 = hasher5.finalize();
}

fn openssl_patterns() {
    let data = b"test data";

    // Pattern: openssl::hash::hash(openssl::hash::MessageDigest::sha224(), $DATA) - Line 29
    let hash1 = hash(MessageDigest::sha224(), data);

    // Pattern: let $MD = openssl::hash::MessageDigest::sha224(); openssl::hash::hash($MD, $DATA) - Line 32
    let md = MessageDigest::sha224();
    let hash2 = hash(md, data);

    // Pattern: openssl::hash::Hasher::new(openssl::hash::MessageDigest::sha224()) - Line 36
    let mut hasher1 = Hasher::new(MessageDigest::sha224()).unwrap();

    // Pattern: let mut $HASHER = openssl::hash::Hasher::new(...); $HASHER.update($DATA); $HASHER.finish() - Line 39
    let mut hasher2 = Hasher::new(MessageDigest::sha224()).unwrap();
    hasher2.update(data).unwrap();
    let hash3 = hasher2.finish().unwrap();
}
