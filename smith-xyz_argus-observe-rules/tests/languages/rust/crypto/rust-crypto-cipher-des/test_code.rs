use openssl::symm;

fn des_cipher_patterns() {
    let key = b"01234567";
    let iv = b"01234567";
    let data = b"test data";

    // Pattern: openssl::symm::Cipher::des_cbc() - Line 6
    let cipher1 = symm::Cipher::des_cbc();

    // Pattern: openssl::symm::Cipher::des_ecb() - Line 9
    let cipher2 = symm::Cipher::des_ecb();

    // Pattern: openssl::symm::Cipher::des_cfb64() - Line 12
    let cipher3 = symm::Cipher::des_cfb64();

    // Pattern: openssl::symm::Cipher::des_ofb() - Line 15
    let cipher4 = symm::Cipher::des_ofb();

    // Pattern: openssl::symm::Cipher::des3_cbc() - Line 18
    let cipher5 = symm::Cipher::des3_cbc();

    // Pattern: openssl::symm::Cipher::des3_ecb() - Line 21
    let cipher6 = symm::Cipher::des3_ecb();

    // Pattern: openssl::symm::Cipher::des3_cfb64() - Line 24
    let cipher7 = symm::Cipher::des3_cfb64();

    // Pattern: openssl::symm::Cipher::des3_ofb() - Line 27
    let cipher8 = symm::Cipher::des3_ofb();

    // Pattern: openssl::symm::encrypt(openssl::symm::Cipher::des_cbc(), $KEY, $IV, $DATA) - Line 30
    let ciphertext1 = symm::encrypt(symm::Cipher::des_cbc(), key, Some(iv), data).unwrap();

    // Pattern: openssl::symm::decrypt(openssl::symm::Cipher::des_cbc(), $KEY, $IV, $DATA) - Line 33
    let plaintext1 = symm::decrypt(symm::Cipher::des_cbc(), key, Some(iv), &ciphertext1).unwrap();

    // Pattern: openssl::symm::encrypt(openssl::symm::Cipher::des3_cbc(), $KEY, $IV, $DATA) - Line 36
    let key3des = b"012345678901234567890123";
    let ciphertext2 = symm::encrypt(symm::Cipher::des3_cbc(), key3des, Some(iv), data).unwrap();

    // Pattern: openssl::symm::decrypt(openssl::symm::Cipher::des3_cbc(), $KEY, $IV, $DATA) - Line 40
    let plaintext2 = symm::decrypt(symm::Cipher::des3_cbc(), key3des, Some(iv), &ciphertext2).unwrap();

    // Pattern: let $CIPHER = openssl::symm::Cipher::des_cbc(); let $CIPHERTEXT = openssl::symm::encrypt($CIPHER, $KEY, $IV, $DATA); - Line 43
    let cipher9 = symm::Cipher::des_cbc();
    let ciphertext3 = symm::encrypt(cipher9, key, Some(iv), data).unwrap();

    // Pattern: let $CIPHER = openssl::symm::Cipher::des3_cbc(); let $CIPHERTEXT = openssl::symm::encrypt($CIPHER, $KEY, $IV, $DATA); - Line 47
    let cipher10 = symm::Cipher::des3_cbc();
    let ciphertext4 = symm::encrypt(cipher10, key3des, Some(iv), data).unwrap();

    // Pattern: let $CIPHER = openssl::symm::Cipher::des_ecb(); openssl::symm::encrypt($CIPHER, $KEY, None, $DATA) - Line 51
    let cipher11 = symm::Cipher::des_ecb();
    let ciphertext5 = symm::encrypt(cipher11, key, None, data).unwrap();
}
