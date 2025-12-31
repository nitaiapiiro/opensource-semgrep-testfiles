use rustls::{ClientConfig, ServerConfig};

fn rustls_cipher_suite_patterns() {
    // Pattern: rustls::cipher_suite::TLS13_AES_256_GCM_SHA384 - Line 5
    let suite1 = rustls::cipher_suite::TLS13_AES_256_GCM_SHA384;

    // Pattern: rustls::cipher_suite::TLS13_AES_128_GCM_SHA256 - Line 8
    let suite2 = rustls::cipher_suite::TLS13_AES_128_GCM_SHA256;

    // Pattern: rustls::cipher_suite::TLS13_CHACHA20_POLY1305_SHA256 - Line 11
    let suite3 = rustls::cipher_suite::TLS13_CHACHA20_POLY1305_SHA256;

    // Pattern: rustls::cipher_suite::TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384 - Line 14
    let suite4 = rustls::cipher_suite::TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384;

    // Pattern: rustls::cipher_suite::TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384 - Line 17
    let suite5 = rustls::cipher_suite::TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384;

    // Pattern: rustls::cipher_suite::TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256 - Line 20
    let suite6 = rustls::cipher_suite::TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256;

    // Pattern: rustls::ClientConfig::builder() .with_safe_default_cipher_suites() .with_safe_default_kx_groups() - Line 23
    let config1 = ClientConfig::builder()
        .with_safe_defaults()
        .with_safe_default_cipher_suites()
        .with_safe_default_kx_groups()
        .with_no_client_auth();

    // Pattern: rustls::ServerConfig::builder() .with_safe_default_cipher_suites() .with_safe_default_kx_groups() - Line 30
    let config2 = ServerConfig::builder()
        .with_safe_defaults()
        .with_safe_default_cipher_suites()
        .with_safe_default_kx_groups()
        .with_no_client_auth()
        .unwrap();

    // Pattern: rustls::ClientConfig::builder().with_cipher_suites($SUITES) - Line 37
    let suites = &[rustls::cipher_suite::TLS13_AES_256_GCM_SHA384];
    let config3 = ClientConfig::builder()
        .with_safe_defaults()
        .with_cipher_suites(suites)
        .with_no_client_auth();

    // Pattern: rustls::ServerConfig::builder().with_cipher_suites($SUITES) - Line 43
    let config4 = ServerConfig::builder()
        .with_safe_defaults()
        .with_cipher_suites(suites)
        .with_no_client_auth()
        .unwrap();
}
