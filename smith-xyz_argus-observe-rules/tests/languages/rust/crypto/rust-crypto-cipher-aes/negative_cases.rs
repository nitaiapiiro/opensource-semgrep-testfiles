fn non_crypto_operations() {
    let data = "just a string";
    println!("{}", data);
}

fn non_aes_crypto() {
    use chacha20::ChaCha20;
    let key = [0u8; 32];
    let _cipher = ChaCha20::new(&key.into(), &[0u8; 12].into());
}

fn string_operations() {
    let key = "not a crypto key";
    let len = key.len();
}
