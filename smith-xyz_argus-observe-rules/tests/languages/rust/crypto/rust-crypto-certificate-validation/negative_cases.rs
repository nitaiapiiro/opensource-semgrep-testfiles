fn non_crypto_usage() {
    let data = "this is just a string";
    println!("{}", data);
}

fn secure_certificate_validation() {
    use native_tls::{TlsConnector, TlsAcceptor};
    use rustls::{ClientConfig, ServerConfig};

    let connector = TlsConnector::builder()
        .danger_accept_invalid_certs(false)
        .build()
        .unwrap();

    let config = ClientConfig::builder()
        .with_safe_defaults()
        .with_no_client_auth();
}

fn other_crypto_operations() {
    use sha2::{Sha256, Digest};
    let mut hasher = Sha256::new();
    hasher.update(b"data");
    let _hash = hasher.finalize();
}
