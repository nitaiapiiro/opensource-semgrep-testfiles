use argon2::{Argon2, PasswordHasher, PasswordVerifier};
use scrypt::{scrypt, ScryptParams};
use pbkdf2::{pbkdf2, pbkdf2_hmac};
use sha2::{Sha256, Sha512};

fn argon2_patterns() {
    let password = b"password";
    let salt = b"salt";
    let hash_bytes = b"hash";

    // Pattern: argon2::hash_encoded($PASSWORD, $SALT, &$CONFIG, $ALGO) - Line 8
    let config = argon2::Config::default();
    let hash1 = argon2::hash_encoded(password, salt, &config).unwrap();

    // Pattern: argon2::verify_encoded($HASH, $PASSWORD) - Line 12
    argon2::verify_encoded(&hash1, password).unwrap();

    // Pattern: argon2::Argon2::new($ALGO, $VERSION, $PARAMS) - Line 15
    let algo = argon2::Algorithm::Argon2id;
    let version = argon2::Version::V0x13;
    let params = argon2::Params::default();
    let argon2_1 = Argon2::new(algo, version, params);

    // Pattern: argon2::Argon2::default() - Line 20
    let argon2_2 = Argon2::default();

    // Pattern: argon2::Argon2::new_with_secret($SECRET, $ALGO, $VERSION, $PARAMS) - Line 23
    let secret = b"secret";
    let argon2_3 = Argon2::new_with_secret(secret, algo, version, params).unwrap();

    // Pattern: use argon2::{Argon2, PasswordHasher, PasswordVerifier}; let $ARGON2 = Argon2::default(); $ARGON2.hash_password($PASSWORD, $SALT) - Line 27
    let argon2_4 = Argon2::default();
    let hash2 = argon2_4.hash_password(password, salt).unwrap();
}

fn scrypt_patterns() {
    let password = b"password";
    let salt = b"salt";
    let mut output = [0u8; 32];

    // Pattern: scrypt::scrypt($PASSWORD, $SALT, &$PARAMS, $OUTPUT) - Line 35
    let params = ScryptParams::recommended();
    scrypt(password, salt, &params, &mut output).unwrap();

    // Pattern: scrypt::ScryptParams::new($LOG_N, $R, $P) - Line 39
    let params2 = ScryptParams::new(14, 8, 1).unwrap();

    // Pattern: scrypt::ScryptParams::recommended() - Line 42
    let params3 = ScryptParams::recommended();

    // Pattern: let $PARAMS = scrypt::ScryptParams::new($LOG_N, $R, $P); scrypt::scrypt($PASSWORD, $SALT, &$PARAMS, $OUTPUT); - Line 45
    let params4 = ScryptParams::new(14, 8, 1).unwrap();
    scrypt(password, salt, &params4, &mut output).unwrap();

    // Pattern: use scrypt::{scrypt, ScryptParams}; let $PARAMS = ScryptParams::recommended(); scrypt($PASSWORD, $SALT, &$PARAMS, $OUTPUT) - Line 49
    let params5 = ScryptParams::recommended();
    scrypt(password, salt, &params5, &mut output).unwrap();
}

fn pbkdf2_patterns() {
    let password = b"password";
    let salt = b"salt";
    let mut output = [0u8; 32];
    let iterations = 10000u32;

    // Pattern: pbkdf2::pbkdf2($PASSWORD, $SALT, $ITER, $OUTPUT) - Line 58
    pbkdf2(password, salt, iterations, &mut output);

    // Pattern: pbkdf2::pbkdf2_hmac::<sha2::Sha256>($PASSWORD, $SALT, $ITER, $OUTPUT) - Line 61
    pbkdf2_hmac::<Sha256>(password, salt, iterations, &mut output);

    // Pattern: pbkdf2::pbkdf2_hmac::<sha2::Sha512>($PASSWORD, $SALT, $ITER, $OUTPUT) - Line 64
    pbkdf2_hmac::<Sha512>(password, salt, iterations, &mut output);

    // Pattern: use pbkdf2::{pbkdf2_hmac, pbkdf2}; use sha2::Sha256; pbkdf2_hmac::<Sha256>($PASSWORD, $SALT, $ITER, $OUTPUT) - Line 67
    pbkdf2_hmac::<Sha256>(password, salt, iterations, &mut output);

    // Pattern: pbkdf2::pbkdf2($PASSWORD, $SALT, $ITER, $OUTPUT) with metavariable-comparison >= 10000 - Line 70
    let iter = 10000u32;
    pbkdf2(password, salt, iter, &mut output);

    // Pattern: pbkdf2::pbkdf2_hmac::<$HASH>($PASSWORD, $SALT, $ITER, $OUTPUT) with metavariable-comparison >= 10000 - Line 74
    let iter2 = 10000u32;
    pbkdf2_hmac::<Sha256>(password, salt, iter2, &mut output);
}
