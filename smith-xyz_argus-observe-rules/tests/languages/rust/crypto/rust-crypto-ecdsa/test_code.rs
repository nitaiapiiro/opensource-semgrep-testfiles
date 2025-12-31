use ring::signature;
use openssl::ec::{EcGroup, EcKey};
use openssl::nid::Nid;
use openssl::hash::MessageDigest;
use openssl::sign::{Signer, Verifier};

fn ring_ecdsa_patterns() {
    let private_key = b"private key";
    let public_key_bytes = b"public key bytes";
    let message = b"message";

    // Pattern: ring::signature::ECDSA_P256_SHA256_ASN1_SIGNING - Line 9
    let algo1 = &signature::ECDSA_P256_SHA256_ASN1_SIGNING;

    // Pattern: ring::signature::ECDSA_P384_SHA384_ASN1_SIGNING - Line 12
    let algo2 = &signature::ECDSA_P384_SHA384_ASN1_SIGNING;

    // Pattern: ring::signature::ECDSA_P256_SHA256_FIXED_SIGNING - Line 15
    let algo3 = &signature::ECDSA_P256_SHA256_FIXED_SIGNING;

    // Pattern: ring::signature::ECDSA_P384_SHA384_FIXED_SIGNING - Line 18
    let algo4 = &signature::ECDSA_P384_SHA384_FIXED_SIGNING;

    // Pattern: ring::signature::sign($ALGO, $PRIVATE_KEY, $MESSAGE) - Line 21
    let signature_bytes = signature::sign(algo1, private_key, message);

    // Pattern: ring::signature::verify($ALGO, $PUBLIC_KEY, $MESSAGE, $SIGNATURE) - Line 24
    let public_key = signature::UnparsedPublicKey::new(algo1, public_key_bytes);
    signature::verify(&public_key, message, signature_bytes.as_ref()).unwrap();

    // Pattern: ring::signature::UnparsedPublicKey::new($ALGO, $PUBLIC_KEY) - Line 28
    let public_key2 = signature::UnparsedPublicKey::new(algo1, public_key_bytes);

    // Pattern: let $ALGO = &ring::signature::ECDSA_P256_SHA256_ASN1_SIGNING; let $SIGNATURE = ring::signature::sign($ALGO, $PRIVATE_KEY, $MESSAGE); - Line 31
    let algo5 = &signature::ECDSA_P256_SHA256_ASN1_SIGNING;
    let sig = signature::sign(algo5, private_key, message);

    // Pattern: let $PUBLIC_KEY = ring::signature::UnparsedPublicKey::new(&ring::signature::ECDSA_P256_SHA256_ASN1_SIGNING, $PUBLIC_KEY_BYTES); ring::signature::verify(&$PUBLIC_KEY, $MESSAGE, $SIGNATURE) - Line 35
    let public_key3 = signature::UnparsedPublicKey::new(&signature::ECDSA_P256_SHA256_ASN1_SIGNING, public_key_bytes);
    signature::verify(&public_key3, message, sig.as_ref()).unwrap();
}

fn openssl_ecdsa_patterns() {
    // Pattern: openssl::ec::EcGroup::from_curve_name($CURVE) - Line 42
    let group1 = EcGroup::from_curve_name(Nid::X9_62_PRIME256V1).unwrap();

    // Pattern: openssl::ec::EcGroup::from_curve_name(openssl::nid::Nid::X9_62_PRIME256V1) - Line 45
    let group2 = EcGroup::from_curve_name(Nid::X9_62_PRIME256V1).unwrap();

    // Pattern: openssl::ec::EcGroup::from_curve_name(openssl::nid::Nid::SECP384R1) - Line 48
    let group3 = EcGroup::from_curve_name(Nid::SECP384R1).unwrap();

    // Pattern: openssl::ec::EcGroup::from_curve_name(openssl::nid::Nid::SECP521R1) - Line 51
    let group4 = EcGroup::from_curve_name(Nid::SECP521R1).unwrap();

    // Pattern: openssl::ec::EcKey::generate($GROUP) - Line 54
    let ec_key1 = EcKey::generate(&group1).unwrap();

    // Pattern: openssl::ec::EcKey::from_private_components($GROUP, $PRIVATE_KEY) - Line 57
    let private_key_bytes = b"private key";
    let _ec_key2 = EcKey::from_private_components(&group1, private_key_bytes);

    // Pattern: openssl::ec::EcKey::from_public_key($GROUP, $PUBLIC_KEY) - Line 61
    let public_key_bytes = b"public key";
    let _ec_key3 = EcKey::from_public_key(&group1, public_key_bytes);

    // Pattern: let $GROUP = openssl::ec::EcGroup::from_curve_name(openssl::nid::Nid::X9_62_PRIME256V1); let $KEY = openssl::ec::EcKey::generate(&$GROUP); - Line 65
    let group5 = EcGroup::from_curve_name(Nid::X9_62_PRIME256V1).unwrap();
    let ec_key4 = EcKey::generate(&group5).unwrap();

    // Pattern: openssl::sign::Signer::new(openssl::hash::MessageDigest::sha256(), $EC_KEY) - Line 69
    let mut signer = Signer::new(MessageDigest::sha256(), &ec_key1).unwrap();

    // Pattern: openssl::sign::Verifier::new(openssl::hash::MessageDigest::sha256(), $EC_KEY) - Line 72
    let mut verifier = Verifier::new(MessageDigest::sha256(), &ec_key1).unwrap();
}
