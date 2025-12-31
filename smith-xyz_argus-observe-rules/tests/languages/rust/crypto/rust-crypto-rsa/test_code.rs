use openssl::rsa::{Rsa, Padding};
use rsa::{RsaPrivateKey, RsaPublicKey, PaddingScheme};
use rsa::rand_core::OsRng;

fn openssl_rsa_patterns() {
    // Pattern: openssl::rsa::Rsa::generate($BITS) - Line 6
    let rsa1 = Rsa::generate(2048).unwrap();

    // Pattern: openssl::rsa::Rsa::generate_with_e($BITS, $E) - Line 9
    let rsa2 = Rsa::generate_with_e(2048, &65537u32.to_be_bytes()).unwrap();

    // Pattern: openssl::rsa::Rsa::public_key_from_der($DATA) - Line 12
    let der_data = b"der data";
    let _rsa3 = Rsa::public_key_from_der(der_data);

    // Pattern: openssl::rsa::Rsa::public_key_from_pem($DATA) - Line 16
    let pem_data = b"pem data";
    let _rsa4 = Rsa::public_key_from_pem(pem_data);

    // Pattern: openssl::rsa::Rsa::private_key_from_der($DATA) - Line 20
    let _rsa5 = Rsa::private_key_from_der(der_data);

    // Pattern: openssl::rsa::Rsa::private_key_from_pem($DATA) - Line 23
    let _rsa6 = Rsa::private_key_from_pem(pem_data);

    // Pattern: openssl::rsa::Rsa::private_key_from_pem_passphrase($DATA, $PASS) - Line 26
    let passphrase = b"passphrase";
    let _rsa7 = Rsa::private_key_from_pem_passphrase(pem_data, passphrase);

    // Pattern: openssl::rsa::Rsa::public_encrypt($DATA, $OUTPUT, $PADDING) - Line 30
    let data = b"test";
    let mut output = vec![0u8; rsa1.size() as usize];
    rsa1.public_encrypt(data, &mut output, Padding::PKCS1).unwrap();

    // Pattern: openssl::rsa::Rsa::private_decrypt($DATA, $OUTPUT, $PADDING) - Line 34
    let mut plaintext = vec![0u8; rsa1.size() as usize];
    rsa1.private_decrypt(&output, &mut plaintext, Padding::PKCS1).unwrap();

    // Pattern: openssl::rsa::Rsa::private_encrypt($DATA, $OUTPUT, $PADDING) - Line 38
    rsa1.private_encrypt(data, &mut output, Padding::PKCS1).unwrap();

    // Pattern: openssl::rsa::Rsa::public_decrypt($DATA, $OUTPUT, $PADDING) - Line 41
    rsa1.public_decrypt(&output, &mut plaintext, Padding::PKCS1).unwrap();

    // Pattern: openssl::rsa::Padding::PKCS1 - Line 44
    let _padding1 = Padding::PKCS1;

    // Pattern: openssl::rsa::Padding::PKCS1_OAEP - Line 47
    let _padding2 = Padding::PKCS1_OAEP;

    // Pattern: openssl::rsa::Padding::PKCS1_PSS - Line 50
    let _padding3 = Padding::PKCS1_PSS;

    // Pattern: let $RSA = openssl::rsa::Rsa::generate($BITS); ... $RSA.public_key_to_der(); - Line 53
    let rsa8 = Rsa::generate(2048).unwrap();
    let _public_key_der = rsa8.public_key_to_der().unwrap();

    // Pattern: let $RSA = openssl::rsa::Rsa::generate($BITS); let $PUBLIC_KEY = $RSA.public_key_to_pem(); let $PRIVATE_KEY = $RSA.private_key_to_pem(); - Line 57
    let rsa9 = Rsa::generate(2048).unwrap();
    let public_key_pem = rsa9.public_key_to_pem().unwrap();
    let private_key_pem = rsa9.private_key_to_pem().unwrap();

    // Pattern: let $RSA = openssl::rsa::Rsa::generate($BITS); $RSA.public_encrypt($DATA, $OUTPUT, openssl::rsa::Padding::PKCS1) - Line 62
    let rsa10 = Rsa::generate(2048).unwrap();
    let mut output2 = vec![0u8; rsa10.size() as usize];
    rsa10.public_encrypt(data, &mut output2, Padding::PKCS1).unwrap();

    // Pattern: openssl::rsa::Rsa::generate($BITS) with metavariable-comparison >= 2048 - Line 66
    let rsa11 = Rsa::generate(2048).unwrap();
}

fn rsa_crate_patterns() {
    let mut rng = OsRng;

    // Pattern: rsa::RsaPrivateKey::new($RNG, $BITS) - Line 73
    let private_key = RsaPrivateKey::new(&mut rng, 2048).unwrap();

    // Pattern: rsa::RsaPublicKey::from($PRIVATE_KEY) - Line 76
    let public_key = RsaPublicKey::from(&private_key);

    // Pattern: rsa::RsaPrivateKey::sign($SCHEME, $HASH) - Line 79
    let hash = b"hash";
    let _signature = private_key.sign(PaddingScheme::PKCS1v15Sign, hash).unwrap();

    // Pattern: rsa::RsaPublicKey::encrypt($RNG, $PADDING, $MESSAGE) - Line 83
    let message = b"message";
    let _ciphertext = public_key.encrypt(&mut rng, PaddingScheme::PKCS1v15Encrypt, message).unwrap();

    // Pattern: rsa::RsaPublicKey::verify($SCHEME, $HASH, $SIGNATURE) - Line 87
    let signature = b"signature";
    let _verified = public_key.verify(PaddingScheme::PKCS1v15Sign, hash, signature).unwrap();

    // Pattern: rsa::RsaPrivateKey::new($RNG, $BITS) with metavariable-comparison >= 2048 - Line 91
    let private_key2 = RsaPrivateKey::new(&mut rng, 2048).unwrap();
}
