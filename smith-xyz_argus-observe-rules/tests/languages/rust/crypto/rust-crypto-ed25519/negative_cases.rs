fn non_crypto_operations() {
    let data = "just a string";
    println!("{}", data);
}

fn other_signature_algorithms() {
    use ring::signature;
    let private_key = [0u8; 32];
    let message = b"test";
    let _sig = signature::sign(&signature::ECDSA_P256_SHA256_ASN1_SIGNING, &private_key, message);
}

fn string_operations() {
    let key = "not a crypto key";
    let len = key.len();
}
