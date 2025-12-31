use native_tls::{TlsConnector, TlsAcceptor};
use rustls::{ClientConfig, ServerConfig};
use openssl::ssl::{SslConnector, SslAcceptor, SslMethod, SslVerifyMode};

fn native_tls_certificate_patterns() {
    // Pattern: native_tls::TlsConnector::builder().danger_accept_invalid_certs(true) - Line 5
    let connector1 = TlsConnector::builder()
        .danger_accept_invalid_certs(true)
        .build()
        .unwrap();

    // Pattern: native_tls::TlsAcceptor::builder().danger_accept_invalid_certs(true) - Line 10
    let acceptor1 = TlsAcceptor::builder()
        .danger_accept_invalid_certs(true)
        .build()
        .unwrap();

    // Pattern: let $CONNECTOR = native_tls::TlsConnector::builder() .danger_accept_invalid_certs(true) .build(); - Line 15
    let connector2 = TlsConnector::builder()
        .danger_accept_invalid_certs(true)
        .build()
        .unwrap();

    // Pattern: let $ACCEPTOR = native_tls::TlsAcceptor::builder() .danger_accept_invalid_certs(true) .build(); - Line 20
    let acceptor2 = TlsAcceptor::builder()
        .danger_accept_invalid_certs(true)
        .build()
        .unwrap();
}

fn rustls_certificate_patterns() {
    // Pattern: rustls::ClientConfig::builder().with_safe_defaults().dangerous() - Line 27
    let config1 = ClientConfig::builder()
        .with_safe_defaults()
        .dangerous()
        .with_no_client_auth();

    // Pattern: rustls::ServerConfig::builder().with_safe_defaults().dangerous() - Line 32
    let config2 = ServerConfig::builder()
        .with_safe_defaults()
        .dangerous()
        .with_no_client_auth()
        .unwrap();

    // Pattern: let $CONFIG = rustls::ClientConfig::builder() .with_safe_defaults() .dangerous() .set_certificate_verifier($VERIFIER); - Line 38
    struct NoVerifier;
    impl rustls::client::ServerCertVerifier for NoVerifier {
        fn verify_server_cert(
            &self,
            _end_entity: &rustls::Certificate,
            _intermediates: &[rustls::Certificate],
            _server_name: &rustls::ServerName,
            _scts: &mut dyn Iterator<Item = &[u8]>,
            _ocsp_response: &[u8],
            _now: std::time::SystemTime,
        ) -> Result<rustls::client::ServerCertVerified, rustls::Error> {
            Ok(rustls::client::ServerCertVerified::assertion())
        }
    }
    let verifier = std::sync::Arc::new(NoVerifier);
    let config3 = ClientConfig::builder()
        .with_safe_defaults()
        .dangerous()
        .set_certificate_verifier(verifier)
        .with_no_client_auth();

    // Pattern: rustls::client::DangerousClientConfig::set_certificate_verifier($VERIFIER) - Line 52
    let verifier2 = std::sync::Arc::new(NoVerifier);
    let config4 = ClientConfig::builder()
        .with_safe_defaults()
        .dangerous()
        .set_certificate_verifier(verifier2)
        .with_no_client_auth();
}

fn openssl_certificate_patterns() {
    // Pattern: openssl::ssl::SslConnector::builder($METHOD).unwrap().set_verify(openssl::ssl::SslVerifyMode::NONE) - Line 60
    let connector = SslConnector::builder(SslMethod::tls()).unwrap();
    connector.set_verify(SslVerifyMode::NONE);

    // Pattern: openssl::ssl::SslAcceptor::builder($METHOD).unwrap().set_verify(openssl::ssl::SslVerifyMode::NONE) - Line 64
    let acceptor = SslAcceptor::builder(SslMethod::tls()).unwrap();
    acceptor.set_verify(SslVerifyMode::NONE);
}
