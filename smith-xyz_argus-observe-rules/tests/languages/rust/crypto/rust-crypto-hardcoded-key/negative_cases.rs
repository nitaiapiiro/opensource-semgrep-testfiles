fn dynamic_key_generation() {
    use ring::rand;
    use ring::aead;

    let rng = rand::SystemRandom::new();
    let mut key_bytes = [0u8; 32];
    rng.fill(&mut key_bytes).unwrap();

    let sealing_key = aead::SealingKey::new(&aead::AES_256_GCM, &key_bytes);
}

fn key_from_environment() {
    use ring::aead;
    use std::env;

    let key_str = env::var("SECRET_KEY").unwrap();
    let sealing_key = aead::SealingKey::new(&aead::AES_256_GCM, key_str.as_bytes());
}

fn key_from_config() {
    use ring::aead;

    struct Config {
        key: Vec<u8>,
    }

    let config = Config { key: vec![0u8; 32] };
    let sealing_key = aead::SealingKey::new(&aead::AES_256_GCM, &config.key);
}

fn non_crypto_strings() {
    let message = "this is just a message";
    println!("{}", message);
}
