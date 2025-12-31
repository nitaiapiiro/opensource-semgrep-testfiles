use sodiumoxide;

fn key_generation() {
    sodiumoxide::init().unwrap();

    // Pattern: sodiumoxide::crypto::box_::gen_keypair() - Line 7
    let (public_key, secret_key) = sodiumoxide::crypto::box_::gen_keypair();

    // Pattern: sodiumoxide::crypto::sign::gen_keypair() - Line 10
    let (sign_public, sign_secret) = sodiumoxide::crypto::sign::gen_keypair();

    // Pattern: sodiumoxide::crypto::secretbox::gen_key() - Line 13
    let secretbox_key = sodiumoxide::crypto::secretbox::gen_key();
}

fn encryption() {
    sodiumoxide::init().unwrap();
    let (public_key, secret_key) = sodiumoxide::crypto::box_::gen_keypair();
    let nonce = sodiumoxide::crypto::box_::gen_nonce();
    let message = b"test message";

    // Pattern: sodiumoxide::crypto::box_::seal($MESSAGE, $NONCE, $PUBLIC_KEY, $SECRET_KEY) - Line 20
    let ciphertext = sodiumoxide::crypto::box_::seal(message, &nonce, &public_key, &secret_key);

    // Pattern: sodiumoxide::crypto::box_::open($CIPHERTEXT, $NONCE, $PUBLIC_KEY, $SECRET_KEY) - Line 23
    let plaintext = sodiumoxide::crypto::box_::open(&ciphertext, &nonce, &public_key, &secret_key);

    // Pattern: sodiumoxide::crypto::secretbox::seal($MESSAGE, $NONCE, $KEY) - Line 26
    let secretbox_key = sodiumoxide::crypto::secretbox::gen_key();
    let secretbox_nonce = sodiumoxide::crypto::secretbox::gen_nonce();
    let secretbox_ciphertext = sodiumoxide::crypto::secretbox::seal(message, &secretbox_nonce, &secretbox_key);

    // Pattern: sodiumoxide::crypto::secretbox::open($CIPHERTEXT, $NONCE, $KEY) - Line 30
    let secretbox_plaintext = sodiumoxide::crypto::secretbox::open(&secretbox_ciphertext, &secretbox_nonce, &secretbox_key);
}

fn signatures() {
    sodiumoxide::init().unwrap();
    let (public_key, secret_key) = sodiumoxide::crypto::sign::gen_keypair();
    let message = b"test message";

    // Pattern: sodiumoxide::crypto::sign::sign($MESSAGE, $SECRET_KEY) - Line 37
    let signature = sodiumoxide::crypto::sign::sign(message, &secret_key);

    // Pattern: sodiumoxide::crypto::sign::verify($SIGNATURE, $PUBLIC_KEY) - Line 40
    let verified = sodiumoxide::crypto::sign::verify(&signature, &public_key);
}

fn hashing() {
    sodiumoxide::init().unwrap();
    let data = b"test data";

    // Pattern: sodiumoxide::crypto::hash::hash($DATA) - Line 46
    let hash1 = sodiumoxide::crypto::hash::hash(data);

    // Pattern: sodiumoxide::crypto::hash::sha256::hash($DATA) - Line 49
    let hash2 = sodiumoxide::crypto::hash::sha256::hash(data);

    // Pattern: sodiumoxide::crypto::hash::sha512::hash($DATA) - Line 52
    let hash3 = sodiumoxide::crypto::hash::sha512::hash(data);
}

fn password_hashing() {
    sodiumoxide::init().unwrap();
    let password = b"password";
    let salt = sodiumoxide::crypto::pwhash::gen_salt();

    // Pattern: sodiumoxide::crypto::pwhash::derive_key($PASSWORD, $SALT, $OPS_LIMIT, $MEM_LIMIT, $KEY_LEN) - Line 58
    let mut key = [0u8; 32];
    sodiumoxide::crypto::pwhash::derive_key(&mut key, password, &salt, sodiumoxide::crypto::pwhash::OPSLIMIT_INTERACTIVE, sodiumoxide::crypto::pwhash::MEMLIMIT_INTERACTIVE).unwrap();

    // Pattern: sodiumoxide::crypto::pwhash::pwhash($PASSWORD, $OPS_LIMIT, $MEM_LIMIT) - Line 61
    let hash = sodiumoxide::crypto::pwhash::pwhash(password, sodiumoxide::crypto::pwhash::OPSLIMIT_INTERACTIVE, sodiumoxide::crypto::pwhash::MEMLIMIT_INTERACTIVE).unwrap();

    // Pattern: sodiumoxide::crypto::pwhash::pwhash_verify($HASH, $PASSWORD) - Line 64
    sodiumoxide::crypto::pwhash::pwhash_verify(&hash, password).unwrap();
}

fn random_generation() {
    sodiumoxide::init().unwrap();

    // Pattern: sodiumoxide::randombytes::randombytes($LEN) - Line 70
    let bytes = sodiumoxide::randombytes::randombytes(32);

    // Pattern: sodiumoxide::randombytes::randombytes_into($BUF) - Line 73
    let mut buf = [0u8; 32];
    sodiumoxide::randombytes::randombytes_into(&mut buf);
}

fn use_statement_patterns() {
    sodiumoxide::init().unwrap();
    use sodiumoxide::crypto::box_;
    let (public_key, secret_key) = box_::gen_keypair();
    let nonce = box_::gen_nonce();
    let message = b"test";

    // Pattern: use sodiumoxide::crypto::box_; box_::seal(...) - Line 81
    box_::seal(message, &nonce, &public_key, &secret_key);

    // Pattern: use sodiumoxide::crypto::sign; sign::sign(...) - Line 84
    use sodiumoxide::crypto::sign;
    let (sign_public, sign_secret) = sign::gen_keypair();
    sign::sign(message, &sign_secret);

    // Pattern: use sodiumoxide::randombytes; randombytes::randombytes(...) - Line 88
    use sodiumoxide::randombytes;
    randombytes::randombytes(32);
}

async fn async_patterns() {
    sodiumoxide::init().unwrap();
    let (public_key, secret_key) = sodiumoxide::crypto::box_::gen_keypair();
    let nonce = sodiumoxide::crypto::box_::gen_nonce();
    let message = b"test";

    // Pattern: async fn $FUNC() { sodiumoxide::crypto::box_::seal(...) } - Line 95
    sodiumoxide::crypto::box_::seal(message, &nonce, &public_key, &secret_key);
}
