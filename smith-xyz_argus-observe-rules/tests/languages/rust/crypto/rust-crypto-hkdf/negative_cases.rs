fn non_crypto_operations() {
    let data = "just a string";
    println!("{}", data);
}

fn other_key_derivation() {
    use pbkdf2::pbkdf2_hmac;
    use sha2::Sha256;
    let password = b"password";
    let salt = b"salt";
    let mut output = [0u8; 32];
    pbkdf2_hmac::<Sha256>(password, salt, 10000, &mut output);
}

fn string_operations() {
    let key = "not a crypto key";
    let len = key.len();
}
