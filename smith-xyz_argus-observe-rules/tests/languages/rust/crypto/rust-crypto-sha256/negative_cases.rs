fn non_crypto_usage() {
    let data = "this is just a string";
    println!("{}", data);
}

fn other_hash_functions() {
    use blake2::{Blake2b512, Digest};
    let mut hasher = Blake2b512::new();
    hasher.update(b"data");
    let _hash = hasher.finalize();
}

fn non_crypto_string_operations() {
    let key = "not a crypto key";
    let value = key.len();
}
