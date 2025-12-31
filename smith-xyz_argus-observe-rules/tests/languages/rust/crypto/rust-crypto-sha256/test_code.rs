use ring::digest;
use sha2::{Sha256, Digest};
use openssl::hash::{hash, MessageDigest, Hasher};

fn ring_digest_patterns() {
    let data = b"test data";

    // Pattern: ring::digest::digest(&ring::digest::SHA256, $DATA) - Line 8
    let hash1 = digest::digest(&digest::SHA256, data);

    // Pattern: ring::digest::SHA256 - Line 11
    let algo = digest::SHA256;

    // Pattern: ring::digest::SHA384 - Line 14
    let algo384 = digest::SHA384;

    // Pattern: ring::digest::SHA512 - Line 17
    let algo512 = digest::SHA512;
}

fn sha2_crate_patterns() {
    let data = b"test data";

    // Pattern: sha2::Sha256::new() - Line 25
    let mut hasher1 = Sha256::new();

    // Pattern: sha2::Sha256::default() - Line 28
    let mut hasher2 = Sha256::default();

    // Pattern: sha2::Sha256::from($DATA) - Line 31
    let hasher3 = Sha256::from(data);

    // Pattern: let mut $HASHER = sha2::Sha256::new(); $HASHER.update($DATA); let $HASH = $HASHER.finalize(); - Line 34
    let mut hasher4 = Sha256::new();
    hasher4.update(data);
    let hash4 = hasher4.finalize();

    // Pattern: $HASHER.finalize_into($OUTPUT) - Line 40
    let mut hasher5 = Sha256::new();
    hasher5.update(data);
    let mut output = [0u8; 32];
    hasher5.finalize_into((&mut output).into());

    // Pattern: use sha2::{Sha256, Digest}; let mut $HASHER = Sha256::new(); $HASHER.update($DATA); - Line 45
    let mut hasher6 = Sha256::new();
    hasher6.update(data);
}

fn openssl_patterns() {
    let data = b"test data";

    // Pattern: openssl::hash::hash(openssl::hash::MessageDigest::sha256(), $DATA) - Line 52
    let hash1 = hash(MessageDigest::sha256(), data);

    // Pattern: openssl::hash::hash(openssl::hash::MessageDigest::sha384(), $DATA) - Line 55
    let hash2 = hash(MessageDigest::sha384(), data);

    // Pattern: openssl::hash::hash(openssl::hash::MessageDigest::sha512(), $DATA) - Line 58
    let hash3 = hash(MessageDigest::sha512(), data);

    // Pattern: let $MD = openssl::hash::MessageDigest::sha256(); openssl::hash::hash($MD, $DATA) - Line 61
    let md = MessageDigest::sha256();
    let hash4 = hash(md, data);

    // Pattern: openssl::hash::Hasher::new(openssl::hash::MessageDigest::sha256()) - Line 65
    let mut hasher1 = Hasher::new(MessageDigest::sha256()).unwrap();

    // Pattern: let mut $HASHER = openssl::hash::Hasher::new(...); $HASHER.update($DATA); $HASHER.finish() - Line 68
    let mut hasher2 = Hasher::new(MessageDigest::sha256()).unwrap();
    hasher2.update(data).unwrap();
    let hash5 = hasher2.finish().unwrap();
}

fn digest_trait_patterns() {
    use digest::Digest;
    let data = b"test data";

    // Pattern: use digest::Digest; let mut $HASHER: Box<dyn Digest> = Box::new(sha2::Sha256::new()); ... - Line 76
    let mut hasher: Box<dyn Digest> = Box::new(Sha256::new());
    hasher.update(data);
    hasher.finalize();
}

async fn async_patterns() {
    let data = b"test data";

    // Pattern: async fn $FUNC() { sha2::Sha256::new(); ... } - Line 84
    let mut hasher = Sha256::new();
    hasher.update(data);
    hasher.finalize();
}
