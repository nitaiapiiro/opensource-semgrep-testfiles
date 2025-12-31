#[cfg(test)]
mod tests {
    use sodiumoxide;

    #[test]
    fn test_sodiumoxide() {
        sodiumoxide::init().unwrap();
        let (public_key, secret_key) = sodiumoxide::crypto::box_::gen_keypair();
    }
}
