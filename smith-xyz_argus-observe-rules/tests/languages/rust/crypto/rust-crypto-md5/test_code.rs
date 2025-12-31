use md5::{Md5, Digest};
use crypto::digest::Digest as CryptoDigest;
use crypto::md5::Md5 as CryptoMd5;

fn md5_crate_patterns() {
    let data = b"test data";

    // Pattern: md5::Md5::new() - Line 8
    let mut hasher1 = Md5::new();

    // Pattern: md5::Md5::default() - Line 11
    let mut hasher2 = Md5::default();

    // Pattern: md5::Md5::from($DATA) - Line 14
    let hasher3 = Md5::from(data);

    // Pattern: let mut $HASHER = md5::Md5::new(); $HASHER.update($DATA); let $HASH = $HASHER.finalize(); - Line 17
    let mut hasher4 = Md5::new();
    hasher4.update(data);
    let hash4 = hasher4.finalize();

    // Pattern: use md5::{Md5, Digest}; let mut $HASHER = Md5::new(); $HASHER.update($DATA); $HASHER.finalize() - Line 22
    let mut hasher5 = Md5::new();
    hasher5.update(data);
    let _hash5 = hasher5.finalize();

    // Pattern: md5::compute($DATA) - Line 26
    let _hash6 = md5::compute(data);

    // Pattern: md5::Md5::digest($DATA) - Line 29
    let _hash7 = Md5::digest(data);
}

fn crypto_crate_patterns() {
    let data = b"test data";

    // Pattern: crypto::md5::Md5::new() - Line 36
    let mut hasher1 = CryptoMd5::new();

    // Pattern: use crypto::digest::Digest; let mut $HASHER = crypto::md5::Md5::new(); $HASHER.input($DATA); $HASHER.result_str() - Line 39
    let mut hasher2 = CryptoMd5::new();
    hasher2.input(data);
    let _result = hasher2.result_str();
}
