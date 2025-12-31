use hkdf::Hkdf;
use sha2::Sha256;
use sha2::Sha512;
use ring::hkdf;

fn hkdf_crate_patterns() {
    let salt = b"salt";
    let ikm = b"input key material";
    let info = b"info";
    let mut okm = [0u8; 32];

    // Pattern: hkdf::Hkdf::<sha2::Sha256>::extract($SALT, $IKM) - Line 11
    let _hkdf1 = Hkdf::<Sha256>::extract(Some(salt), ikm);

    // Pattern: hkdf::Hkdf::<sha2::Sha512>::extract($SALT, $IKM) - Line 14
    let _hkdf2 = Hkdf::<Sha512>::extract(Some(salt), ikm);

    // Pattern: hkdf::Hkdf::<sha2::Sha256>::expand($PRK, $INFO, $OKM) - Line 17
    let hkdf3 = Hkdf::<Sha256>::extract(Some(salt), ikm);
    hkdf3.expand(info, &mut okm).unwrap();

    // Pattern: hkdf::Hkdf::<sha2::Sha512>::expand($PRK, $INFO, $OKM) - Line 21
    let hkdf4 = Hkdf::<Sha512>::extract(Some(salt), ikm);
    hkdf4.expand(info, &mut okm).unwrap();

    // Pattern: let $HKDF = hkdf::Hkdf::<sha2::Sha256>::extract($SALT, $IKM); $HKDF.expand($INFO, $OKM) - Line 25
    let hkdf5 = Hkdf::<Sha256>::extract(Some(salt), ikm);
    hkdf5.expand(info, &mut okm).unwrap();

    // Pattern: use hkdf::Hkdf; use sha2::Sha256; let $HKDF = Hkdf::<Sha256>::new($SALT, $IKM); $HKDF.expand($INFO, $OKM) - Line 29
    let hkdf6 = Hkdf::<Sha256>::new(Some(salt), ikm);
    hkdf6.expand(info, &mut okm).unwrap();
}

fn ring_hkdf_patterns() {
    let salt_bytes = [0u8; 32];
    let ikm = b"input key material";
    let info = b"info";
    let mut okm = [0u8; 32];

    // Pattern: ring::hkdf::extract($ALGO, $SALT, $IKM) - Line 40
    let _prk1 = hkdf::extract(&hkdf::HKDF_SHA256, &salt_bytes, ikm);

    // Pattern: ring::hkdf::expand($ALGO, $PRK, $INFO, $OKM) - Line 43
    let prk2 = hkdf::extract(&hkdf::HKDF_SHA256, &salt_bytes, ikm);
    hkdf::expand(&hkdf::HKDF_SHA256, &prk2, info, &mut okm).unwrap();

    // Pattern: let $PRK = ring::hkdf::extract(&ring::hkdf::HKDF_SHA256, $SALT, $IKM); ring::hkdf::expand(...) - Line 47
    let prk3 = hkdf::extract(&hkdf::HKDF_SHA256, &salt_bytes, ikm);
    hkdf::expand(&hkdf::HKDF_SHA256, &prk3, info, &mut okm).unwrap();

    // Pattern: ring::hkdf::Salt::new($ALGO, $SALT_BYTES) - Line 51
    let _salt1 = hkdf::Salt::new(&hkdf::HKDF_SHA256, &salt_bytes);

    // Pattern: ring::hkdf::Prk::new($ALGO, $PRK_BYTES) - Line 54
    let prk_bytes = [0u8; 32];
    let _prk4 = hkdf::Prk::new_less_safe(&hkdf::HKDF_SHA256, &prk_bytes);

    // Pattern: ring::hkdf::HKDF_SHA256 - Line 58
    let algo1 = hkdf::HKDF_SHA256;

    // Pattern: ring::hkdf::HKDF_SHA384 - Line 61
    let algo2 = hkdf::HKDF_SHA384;

    // Pattern: ring::hkdf::HKDF_SHA512 - Line 64
    let algo3 = hkdf::HKDF_SHA512;

    // Pattern: use ring::hkdf; let $SALT = hkdf::Salt::new(&hkdf::HKDF_SHA256, $SALT_BYTES); let $PRK = $SALT.extract($IKM); $PRK.expand($INFO, $OKM) - Line 67
    let salt2 = hkdf::Salt::new(&hkdf::HKDF_SHA256, &salt_bytes);
    let prk5 = salt2.extract(ikm);
    prk5.expand(info, &mut okm).unwrap();
}
