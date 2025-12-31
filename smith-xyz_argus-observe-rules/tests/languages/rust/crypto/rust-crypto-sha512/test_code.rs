use ring::digest;
use sha2::{Sha512, Digest};
use openssl::hash::{hash, MessageDigest, Hasher};

fn ring_digest_patterns() {
    let data = b"test data";

    // Pattern: ring::digest::digest(&ring::digest::SHA512, $DATA) - Line 8
    let hash1 = digest::digest(&digest::SHA512, data);

    // Pattern: ring::digest::SHA512 - Line 11
    let algo = digest::SHA512;
}

fn sha2_crate_patterns() {
    let data = b"test data";

    // Pattern: sha2::Sha512::new() - Line 18
    let mut hasher1 = Sha512::new();

    // Pattern: sha2::Sha512::default() - Line 21
    let mut hasher2 = Sha512::default();

    // Pattern: sha2::Sha512::from($DATA) - Line 24
    let hasher3 = Sha512::from(data);

    // Pattern: let mut $HASHER = sha2::Sha512::new(); $HASHER.update($DATA); let $HASH = $HASHER.finalize(); - Line 27
    let mut hasher4 = Sha512::new();
    hasher4.update(data);
    let hash4 = hasher4.finalize();

    // Pattern: $HASHER.finalize_into($OUTPUT) - Line 32
    let mut hasher5 = Sha512::new();
    hasher5.update(data);
    let mut output = [0u8; 64];
    hasher5.finalize_into((&mut output).into());

    // Pattern: use sha2::{Sha512, Digest}; let mut $HASHER = Sha512::new(); $HASHER.update($DATA); $HASHER.finalize() - Line 38
    let mut hasher6 = Sha512::new();
    hasher6.update(data);
    let _hash6 = hasher6.finalize();
}

fn openssl_patterns() {
    let data = b"test data";

    // Pattern: openssl::hash::hash(openssl::hash::MessageDigest::sha512(), $DATA) - Line 48
    let hash1 = hash(MessageDigest::sha512(), data);

    // Pattern: let $MD = openssl::hash::MessageDigest::sha512(); openssl::hash::hash($MD, $DATA) - Line 51
    let md = MessageDigest::sha512();
    let hash2 = hash(md, data);

    // Pattern: openssl::hash::Hasher::new(openssl::hash::MessageDigest::sha512()) - Line 55
    let mut hasher1 = Hasher::new(MessageDigest::sha512()).unwrap();

    // Pattern: let mut $HASHER = openssl::hash::Hasher::new(...); $HASHER.update($DATA); $HASHER.finish() - Line 58
    let mut hasher2 = Hasher::new(MessageDigest::sha512()).unwrap();
    hasher2.update(data).unwrap();
    let hash3 = hasher2.finish().unwrap();
}
