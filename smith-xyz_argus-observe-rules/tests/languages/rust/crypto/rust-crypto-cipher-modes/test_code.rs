use openssl::symm;
use cbc::{Encryptor, Decryptor};
use cbc::cipher::{BlockEncrypt, BlockDecrypt, KeyInit};
use aes::Aes256;
use ctr::{Ctr128BE, Ctr128LE};
use cfb8::{Encryptor as Cfb8Encryptor, Decryptor as Cfb8Decryptor};
use ofb::Ofb;

fn openssl_cipher_modes() {
    // Pattern: openssl::symm::Cipher::aes_128_ecb() - Line 6
    let cipher1 = symm::Cipher::aes_128_ecb();

    // Pattern: openssl::symm::Cipher::aes_256_ecb() - Line 9
    let cipher2 = symm::Cipher::aes_256_ecb();

    // Pattern: openssl::symm::Cipher::aes_128_cbc() - Line 12
    let cipher3 = symm::Cipher::aes_128_cbc();

    // Pattern: openssl::symm::Cipher::aes_256_cbc() - Line 15
    let cipher4 = symm::Cipher::aes_256_cbc();

    // Pattern: openssl::symm::Cipher::aes_128_cfb128() - Line 18
    let cipher5 = symm::Cipher::aes_128_cfb128();

    // Pattern: openssl::symm::Cipher::aes_256_cfb128() - Line 21
    let cipher6 = symm::Cipher::aes_256_cfb128();

    // Pattern: openssl::symm::Cipher::aes_128_ofb() - Line 24
    let cipher7 = symm::Cipher::aes_128_ofb();

    // Pattern: openssl::symm::Cipher::aes_256_ofb() - Line 27
    let cipher8 = symm::Cipher::aes_256_ofb();

    // Pattern: openssl::symm::Cipher::aes_128_ctr() - Line 30
    let cipher9 = symm::Cipher::aes_128_ctr();

    // Pattern: openssl::symm::Cipher::aes_256_ctr() - Line 33
    let cipher10 = symm::Cipher::aes_256_ctr();

    // Pattern: openssl::symm::Cipher::aes_128_gcm() - Line 36
    let cipher11 = symm::Cipher::aes_128_gcm();

    // Pattern: openssl::symm::Cipher::aes_256_gcm() - Line 39
    let cipher12 = symm::Cipher::aes_256_gcm();
}

fn cbc_crate_patterns() {
    let key = [0u8; 32];
    let iv = [0u8; 16];

    // Pattern: cbc::Encryptor::<aes::Aes256>::new($KEY, $IV) - Line 45
    let encryptor = Encryptor::<Aes256>::new(&key.into(), &iv.into());

    // Pattern: cbc::Decryptor::<aes::Aes256>::new($KEY, $IV) - Line 48
    let decryptor = Decryptor::<Aes256>::new(&key.into(), &iv.into());
}

fn ctr_crate_patterns() {
    let key = [0u8; 32];
    let iv = [0u8; 16];

    // Pattern: ctr::Ctr128BE::<aes::Aes256>::new($KEY, $IV) - Line 55
    let ctr_be = Ctr128BE::<Aes256>::new(&key.into(), &iv.into());

    // Pattern: ctr::Ctr128LE::<aes::Aes256>::new($KEY, $IV) - Line 58
    let ctr_le = Ctr128LE::<Aes256>::new(&key.into(), &iv.into());
}

fn cfb8_crate_patterns() {
    let key = [0u8; 32];
    let iv = [0u8; 16];

    // Pattern: cfb8::Encryptor::<aes::Aes256>::new($KEY, $IV) - Line 65
    let encryptor = Cfb8Encryptor::<Aes256>::new(&key.into(), &iv.into());

    // Pattern: cfb8::Decryptor::<aes::Aes256>::new($KEY, $IV) - Line 68
    let decryptor = Cfb8Decryptor::<Aes256>::new(&key.into(), &iv.into());
}

fn ofb_crate_patterns() {
    let key = [0u8; 32];
    let iv = [0u8; 16];

    // Pattern: ofb::Ofb::<aes::Aes256>::new($KEY, $IV) - Line 75
    let ofb = Ofb::<Aes256>::new(&key.into(), &iv.into());
}
