use openssl::hash::{hash, MessageDigest, Hasher};
use openssl::symm;
use openssl::rsa::{Rsa, Padding};
use openssl::ec::{EcGroup, EcKey};
use openssl::nid::Nid;
use openssl::sign::{Signer, Verifier};
use openssl::ssl::{SslConnector, SslAcceptor, SslMethod};

fn hash_patterns() {
    let data = b"test data";

    // Pattern: openssl::hash::hash($MD, $DATA) - Line 8
    let hash1 = hash(MessageDigest::sha256(), data);

    // Pattern: openssl::hash::Hasher::new($MD) - Line 11
    let mut hasher1 = Hasher::new(MessageDigest::sha256()).unwrap();

    // Pattern: use openssl::hash; hash::hash(hash::MessageDigest::sha256(), $DATA) - Line 14
    let hash2 = hash(MessageDigest::sha256(), data);
}

fn symm_patterns() {
    let key = b"01234567890123456789012345678901";
    let iv = b"0123456789012345";
    let data = b"test data";

    // Pattern: openssl::symm::encrypt($CIPHER, $KEY, $IV, $DATA) - Line 22
    let cipher = symm::Cipher::aes_256_cbc();
    let ciphertext = symm::encrypt(cipher, key, Some(iv), data).unwrap();

    // Pattern: openssl::symm::decrypt($CIPHER, $KEY, $IV, $DATA) - Line 26
    let plaintext = symm::decrypt(cipher, key, Some(iv), &ciphertext).unwrap();

    // Pattern: use openssl::symm; symm::encrypt(symm::Cipher::aes_256_gcm(), $KEY, $IV, $DATA) - Line 29
    let cipher2 = symm::Cipher::aes_256_gcm();
    let ciphertext2 = symm::encrypt(cipher2, key, Some(iv), data).unwrap();
}

fn rsa_patterns() {
    // Pattern: openssl::rsa::Rsa::generate($BITS) - Line 35
    let rsa = Rsa::generate(2048).unwrap();

    // Pattern: openssl::rsa::Rsa::public_encrypt($DATA, $OUTPUT, $PADDING) - Line 38
    let data = b"test";
    let mut output = vec![0u8; rsa.size() as usize];
    rsa.public_encrypt(data, &mut output, Padding::PKCS1).unwrap();

    // Pattern: openssl::rsa::Rsa::private_decrypt($DATA, $OUTPUT, $PADDING) - Line 42
    let mut plaintext = vec![0u8; rsa.size() as usize];
    rsa.private_decrypt(&output, &mut plaintext, Padding::PKCS1).unwrap();
}

fn ec_patterns() {
    // Pattern: openssl::ec::EcKey::generate($GROUP) - Line 48
    let group = EcGroup::from_curve_name(Nid::X9_62_PRIME256V1).unwrap();
    let ec_key = EcKey::generate(&group).unwrap();
}

fn sign_patterns() {
    let key = b"key";
    let message = b"message";

    // Pattern: openssl::sign::Signer::new($MD, $KEY) - Line 56
    let mut signer = Signer::new(MessageDigest::sha256(), key).unwrap();

    // Pattern: openssl::sign::Verifier::new($MD, $KEY) - Line 59
    let mut verifier = Verifier::new(MessageDigest::sha256(), key).unwrap();
}

fn ssl_patterns() {
    // Pattern: openssl::ssl::SslConnector::builder($METHOD) - Line 65
    let connector = SslConnector::builder(SslMethod::tls()).unwrap();

    // Pattern: openssl::ssl::SslAcceptor::builder($METHOD) - Line 68
    let acceptor = SslAcceptor::builder(SslMethod::tls()).unwrap();
}
