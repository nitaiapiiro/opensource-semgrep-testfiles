fn non_crypto_usage() {
    let data = "this is just a string";
    println!("{}", data);
}

fn other_crypto_operations() {
    use sha2::{Sha256, Digest};
    let mut hasher = Sha256::new();
    hasher.update(b"data");
    let _hash = hasher.finalize();
}

fn non_crypto_string_operations() {
    let key = "not a crypto key";
    let value = key.len();
}
