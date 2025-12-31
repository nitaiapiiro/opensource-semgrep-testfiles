#[cfg(test)]
mod tests {
    use secp256k1;

    #[test]
    fn test_secp256k1() {
        let secp = secp256k1::Secp256k1::new();
        let secret_key = secp256k1::SecretKey::from_slice(&[0u8; 32]).unwrap();
    }
}
