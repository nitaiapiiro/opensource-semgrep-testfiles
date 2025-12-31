use sha3::{Sha3_224, Sha3_256, Sha3_384, Sha3_512, Digest};
use crypto::digest::Digest as CryptoDigest;
use crypto::sha3::Sha3_256 as CryptoSha3_256;
use crypto::sha3::Sha3_512 as CryptoSha3_512;

fn sha3_crate_patterns() {
    let data = b"test data";

    // Pattern: sha3::Sha3_224::new() - Line 8
    let mut hasher1 = Sha3_224::new();

    // Pattern: sha3::Sha3_256::new() - Line 11
    let mut hasher2 = Sha3_256::new();

    // Pattern: sha3::Sha3_384::new() - Line 14
    let mut hasher3 = Sha3_384::new();

    // Pattern: sha3::Sha3_512::new() - Line 17
    let mut hasher4 = Sha3_512::new();

    // Pattern: sha3::Sha3_224::default() - Line 20
    let mut hasher5 = Sha3_224::default();

    // Pattern: sha3::Sha3_256::default() - Line 23
    let mut hasher6 = Sha3_256::default();

    // Pattern: sha3::Sha3_384::default() - Line 26
    let mut hasher7 = Sha3_384::default();

    // Pattern: sha3::Sha3_512::default() - Line 29
    let mut hasher8 = Sha3_512::default();

    // Pattern: let mut $HASHER = sha3::Sha3_256::new(); $HASHER.update($DATA); let $HASH = $HASHER.finalize(); - Line 32
    let mut hasher9 = Sha3_256::new();
    hasher9.update(data);
    let hash9 = hasher9.finalize();

    // Pattern: use sha3::{Sha3_256, Digest}; let mut $HASHER = Sha3_256::new(); $HASHER.update($DATA); $HASHER.finalize() - Line 37
    let mut hasher10 = Sha3_256::new();
    hasher10.update(data);
    let _hash10 = hasher10.finalize();

    // Pattern: let mut $HASHER = sha3::Sha3_512::new(); $HASHER.update($DATA); $HASHER.finalize_into($OUTPUT) - Line 42
    let mut hasher11 = Sha3_512::new();
    hasher11.update(data);
    let mut output = [0u8; 64];
    hasher11.finalize_into((&mut output).into());
}

fn crypto_crate_patterns() {
    let data = b"test data";

    // Pattern: crypto::sha3::Sha3_256::new() - Line 52
    let mut hasher1 = CryptoSha3_256::new();

    // Pattern: crypto::sha3::Sha3_512::new() - Line 55
    let mut hasher2 = CryptoSha3_512::new();

    // Pattern: use crypto::digest::Digest; let mut $HASHER = crypto::sha3::Sha3_256::new(); $HASHER.input($DATA); $HASHER.result_str() - Line 58
    let mut hasher3 = CryptoSha3_256::new();
    hasher3.input(data);
    let _result = hasher3.result_str();
}
