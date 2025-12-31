fn non_crypto_operations() {
    let data = "just a string";
    println!("{}", data);
}

fn hash_operations() {
    use sha2::{Sha256, Digest};
    let mut hasher = Sha256::new();
    hasher.update(b"data");
    let _hash = hasher.finalize();
}

fn string_operations() {
    let key = "not a crypto key";
    let len = key.len();
}
