use bcrypt::{hash, verify, DEFAULT_COST};
use argon2::{Argon2, PasswordHasher, PasswordVerifier};
use scrypt::{scrypt, ScryptParams};
use pbkdf2::{pbkdf2, pbkdf2_hmac};
use sha2::Sha256;
use sha2::Sha512;

fn bcrypt_patterns() {
    let password = b"password";
    let input = b"input";

    // Pattern: bcrypt::hash($PASSWORD, $COST) - Line 9
    let hash1 = hash(password, DEFAULT_COST).unwrap();

    // Pattern: bcrypt::verify($PASSWORD, $HASH) - Line 12
    verify(input, &hash1).unwrap();

    // Pattern: bcrypt::DEFAULT_COST - Line 15
    let cost = DEFAULT_COST;

    // Pattern: let $HASH = bcrypt::hash($PASSWORD, bcrypt::DEFAULT_COST); ... bcrypt::verify($INPUT, &$HASH); - Line 18
    let hash2 = hash(password, DEFAULT_COST).unwrap();
    verify(input, &hash2).unwrap();

    // Pattern: use bcrypt::{hash, verify, DEFAULT_COST}; let $HASH = hash($PASSWORD, DEFAULT_COST); verify($INPUT, &$HASH) - Line 22
    let hash3 = hash(password, DEFAULT_COST).unwrap();
    let _result = verify(input, &hash3);
}

fn argon2_patterns() {
    let password = b"password";
    let salt = b"salt";
    let hash_bytes = b"hash";

    // Pattern: argon2::hash_encoded($PASSWORD, $SALT, &$CONFIG, $ALGO) - Line 31
    let config = argon2::Config::default();
    let hash1 = argon2::hash_encoded(password, salt, &config).unwrap();

    // Pattern: argon2::verify_encoded($HASH, $PASSWORD) - Line 35
    argon2::verify_encoded(&hash1, password).unwrap();

    // Pattern: argon2::Argon2::hash_password($PASSWORD, $SALT) - Line 38
    let argon2 = Argon2::default();
    let hash2 = argon2.hash_password(password, salt).unwrap();

    // Pattern: argon2::Argon2::verify_password($HASH, $PASSWORD) - Line 42
    argon2.verify_password(hash_bytes, password).unwrap();

    // Pattern: use argon2::{Argon2, PasswordHasher, PasswordVerifier}; let $ARGON2 = Argon2::default(); let $HASH = $ARGON2.hash_password($PASSWORD, $SALT); $ARGON2.verify_password($HASH, $INPUT) - Line 46
    let argon2_2 = Argon2::default();
    let hash3 = argon2_2.hash_password(password, salt).unwrap();
    argon2_2.verify_password(&hash3.hash.unprotected_as_bytes(), password).unwrap();
}

fn scrypt_patterns() {
    let password = b"password";
    let salt = b"salt";
    let mut output = [0u8; 32];

    // Pattern: scrypt::scrypt($PASSWORD, $SALT, &$PARAMS, $OUTPUT) - Line 55
    let params = ScryptParams::recommended();
    scrypt(password, salt, &params, &mut output).unwrap();

    // Pattern: scrypt::verify($HASH, $PASSWORD) - Line 59
    let hash_bytes = b"hash";
    scrypt::verify(hash_bytes, password).unwrap();

    // Pattern: use scrypt::{scrypt, ScryptParams}; let $PARAMS = ScryptParams::recommended(); scrypt($PASSWORD, $SALT, &$PARAMS, $OUTPUT) - Line 63
    let params2 = ScryptParams::recommended();
    scrypt(password, salt, &params2, &mut output).unwrap();
}

fn pbkdf2_patterns() {
    let password = b"password";
    let salt = b"salt";
    let mut output = [0u8; 32];
    let iterations = 10000u32;

    // Pattern: pbkdf2::pbkdf2($PASSWORD, $SALT, $ITER, $OUTPUT) - Line 72
    pbkdf2(password, salt, iterations, &mut output);

    // Pattern: pbkdf2::pbkdf2_hmac::<sha2::Sha256>($PASSWORD, $SALT, $ITER, $OUTPUT) - Line 75
    pbkdf2_hmac::<Sha256>(password, salt, iterations, &mut output);

    // Pattern: pbkdf2::pbkdf2_hmac::<sha2::Sha512>($PASSWORD, $SALT, $ITER, $OUTPUT) - Line 78
    pbkdf2_hmac::<Sha512>(password, salt, iterations, &mut output);

    // Pattern: use pbkdf2::{pbkdf2_hmac, pbkdf2}; use sha2::Sha256; pbkdf2_hmac::<Sha256>($PASSWORD, $SALT, $ITER, $OUTPUT) - Line 81
    pbkdf2_hmac::<Sha256>(password, salt, iterations, &mut output);

    // Pattern: pbkdf2::pbkdf2($PASSWORD, $SALT, $ITER, $OUTPUT) with metavariable-comparison >= 10000 - Line 84
    let iter = 10000u32;
    pbkdf2(password, salt, iter, &mut output);

    // Pattern: pbkdf2::pbkdf2_hmac::<$HASH>($PASSWORD, $SALT, $ITER, $OUTPUT) with metavariable-comparison >= 10000 - Line 88
    let iter2 = 10000u32;
    pbkdf2_hmac::<Sha256>(password, salt, iter2, &mut output);
}
