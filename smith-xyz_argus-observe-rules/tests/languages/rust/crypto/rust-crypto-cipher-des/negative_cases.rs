fn non_crypto_usage() {
    let data = "this is just a string";
    println!("{}", data);
}

fn other_crypto_operations() {
    use openssl::symm;
    let key = b"01234567890123456789012345678901";
    let iv = b"0123456789012345";
    let data = b"test data";
    let cipher = symm::Cipher::aes_256_cbc();
    let _ciphertext = symm::encrypt(cipher, key, Some(iv), data).unwrap();
}

fn non_crypto_string_operations() {
    let key = "not a crypto key";
    let value = key.len();
}
