use secp256k1;

fn context_creation() {
    // Pattern: secp256k1::Secp256k1::new() - Line 5
    let secp = secp256k1::Secp256k1::new();

    // Pattern: secp256k1::Secp256k1::signing_only() - Line 8
    let secp_signing = secp256k1::Secp256k1::signing_only();

    // Pattern: secp256k1::Secp256k1::verification_only() - Line 11
    let secp_verification = secp256k1::Secp256k1::verification_only();
}

fn key_operations() {
    let secp = secp256k1::Secp256k1::new();
    let key_bytes = [0u8; 32];

    // Pattern: secp256k1::SecretKey::from_slice($BYTES) - Line 19
    let secret_key = secp256k1::SecretKey::from_slice(&key_bytes).unwrap();

    // Pattern: secp256k1::PublicKey::from_secret_key($SECP256K1, $SECRET_KEY) - Line 22
    let public_key = secp256k1::PublicKey::from_secret_key(&secp, &secret_key);

    // Pattern: secp256k1::PublicKey::from_slice($BYTES) - Line 25
    let public_key2 = secp256k1::PublicKey::from_slice(&key_bytes).unwrap();

    // Pattern: secp256k1::SecretKey::from_keypair($KEYPAIR) - Line 28
    let keypair = secp256k1::KeyPair::from_secret_key(&secp, &secret_key);
    let secret_key2 = secp256k1::SecretKey::from_keypair(&keypair);
}

fn message_operations() {
    let message_bytes = [0u8; 32];

    // Pattern: secp256k1::Message::from_slice($BYTES) - Line 35
    let message = secp256k1::Message::from_slice(&message_bytes).unwrap();
}

fn signature_operations() {
    let secp = secp256k1::Secp256k1::new();
    let secret_key = secp256k1::SecretKey::from_slice(&[0u8; 32]).unwrap();
    let public_key = secp256k1::PublicKey::from_secret_key(&secp, &secret_key);
    let message = secp256k1::Message::from_slice(&[0u8; 32]).unwrap();

    // Pattern: secp256k1::ecdsa::Signature::from_compact($BYTES) - Line 44
    let sig_bytes = [0u8; 64];
    let signature1 = secp256k1::ecdsa::Signature::from_compact(&sig_bytes).unwrap();

    // Pattern: secp256k1::ecdsa::Signature::from_der($BYTES) - Line 48
    let der_bytes = [0u8; 72];
    let signature2 = secp256k1::ecdsa::Signature::from_der(&der_bytes).unwrap();

    // Pattern: secp256k1::ecdsa::sign($SECP256K1, $MESSAGE, $SECRET_KEY) - Line 51
    let signature3 = secp256k1::ecdsa::sign(&message, &secret_key);

    // Pattern: secp256k1::ecdsa::verify($SECP256K1, $MESSAGE, $SIGNATURE, $PUBLIC_KEY) - Line 54
    secp256k1::ecdsa::verify(&message, &signature3, &public_key);
}

fn keypair_operations() {
    let secp = secp256k1::Secp256k1::new();
    let secret_key = secp256k1::SecretKey::from_slice(&[0u8; 32]).unwrap();

    // Pattern: secp256k1::KeyPair::from_secret_key($SECP256K1, $SECRET_KEY) - Line 62
    let keypair = secp256k1::KeyPair::from_secret_key(&secp, &secret_key);

    // Pattern: secp256k1::KeyPair::secret_key($KEYPAIR) - Line 65
    let secret_key2 = secp256k1::KeyPair::secret_key(&keypair);

    // Pattern: secp256k1::KeyPair::public_key($KEYPAIR) - Line 68
    let public_key = secp256k1::KeyPair::public_key(&keypair);
}

fn use_statement_patterns() {
    use secp256k1::{Secp256k1, Message, ecdsa};
    let secp = Secp256k1::new();
    let message = Message::from_slice(&[0u8; 32]).unwrap();
    let secret_key = secp256k1::SecretKey::from_slice(&[0u8; 32]).unwrap();

    // Pattern: use secp256k1::{Secp256k1, Message, ecdsa}; ecdsa::sign(...) - Line 77
    ecdsa::sign(&message, &secret_key);

    // Pattern: use secp256k1::{Secp256k1, Message, ecdsa}; ecdsa::verify(...) - Line 80
    let public_key = secp256k1::PublicKey::from_secret_key(&secp, &secret_key);
    let signature = ecdsa::sign(&message, &secret_key);
    ecdsa::verify(&message, &signature, &public_key);
}

async fn async_patterns() {
    let secp = secp256k1::Secp256k1::new();
    let message = secp256k1::Message::from_slice(&[0u8; 32]).unwrap();
    let secret_key = secp256k1::SecretKey::from_slice(&[0u8; 32]).unwrap();

    // Pattern: async fn $FUNC() { secp256k1::ecdsa::sign(...) } - Line 89
    secp256k1::ecdsa::sign(&message, &secret_key);
}
