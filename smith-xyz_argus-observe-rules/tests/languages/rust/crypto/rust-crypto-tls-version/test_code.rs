use rustls::{ClientConfig, ServerConfig, ProtocolVersion};
use native_tls::{TlsConnector, TlsAcceptor, Protocol};
use openssl::ssl::{SslConnector, SslAcceptor, SslMethod};

fn rustls_version_patterns() {
    // Pattern: rustls::ProtocolVersion::TLSv1_0 - Line 5
    let version1 = ProtocolVersion::TLSv1_0;

    // Pattern: rustls::ProtocolVersion::TLSv1_1 - Line 8
    let version2 = ProtocolVersion::TLSv1_1;

    // Pattern: rustls::ProtocolVersion::TLSv1_2 - Line 11
    let version3 = ProtocolVersion::TLSv1_2;

    // Pattern: rustls::ProtocolVersion::TLSv1_3 - Line 14
    let version4 = ProtocolVersion::TLSv1_3;

    // Pattern: let $CONFIG = rustls::ClientConfig::builder() .with_safe_defaults() .with_protocol_versions(&[rustls::ProtocolVersion::TLSv1_2]) .unwrap(); - Line 17
    let config1 = ClientConfig::builder()
        .with_safe_defaults()
        .with_protocol_versions(&[ProtocolVersion::TLSv1_2])
        .unwrap()
        .with_no_client_auth();

    // Pattern: let $CONFIG = rustls::ServerConfig::builder() .with_safe_defaults() .with_protocol_versions(&[rustls::ProtocolVersion::TLSv1_2]) .unwrap(); - Line 24
    let config2 = ServerConfig::builder()
        .with_safe_defaults()
        .with_protocol_versions(&[ProtocolVersion::TLSv1_2])
        .unwrap()
        .with_no_client_auth()
        .unwrap();

    // Pattern: rustls::ClientConfig::builder().with_protocol_versions($VERSIONS) - Line 31
    let versions = &[ProtocolVersion::TLSv1_2];
    let config3 = ClientConfig::builder()
        .with_safe_defaults()
        .with_protocol_versions(versions)
        .unwrap()
        .with_no_client_auth();

    // Pattern: rustls::ServerConfig::builder().with_protocol_versions($VERSIONS) - Line 37
    let config4 = ServerConfig::builder()
        .with_safe_defaults()
        .with_protocol_versions(versions)
        .unwrap()
        .with_no_client_auth()
        .unwrap();
}

fn native_tls_version_patterns() {
    // Pattern: native_tls::TlsConnector::builder().min_protocol_version($VERSION) - Line 45
    let connector1 = TlsConnector::builder()
        .min_protocol_version(Some(Protocol::Tlsv12))
        .build()
        .unwrap();

    // Pattern: native_tls::TlsConnector::builder().max_protocol_version($VERSION) - Line 50
    let connector2 = TlsConnector::builder()
        .max_protocol_version(Some(Protocol::Tlsv13))
        .build()
        .unwrap();

    // Pattern: native_tls::TlsAcceptor::builder().min_protocol_version($VERSION) - Line 55
    let acceptor1 = TlsAcceptor::builder()
        .min_protocol_version(Some(Protocol::Tlsv12))
        .build()
        .unwrap();

    // Pattern: native_tls::TlsAcceptor::builder().max_protocol_version($VERSION) - Line 60
    let acceptor2 = TlsAcceptor::builder()
        .max_protocol_version(Some(Protocol::Tlsv13))
        .build()
        .unwrap();

    // Pattern: native_tls::Protocol::Tlsv10 - Line 65
    let protocol1 = Protocol::Tlsv10;

    // Pattern: native_tls::Protocol::Tlsv11 - Line 68
    let protocol2 = Protocol::Tlsv11;

    // Pattern: native_tls::Protocol::Tlsv12 - Line 71
    let protocol3 = Protocol::Tlsv12;

    // Pattern: native_tls::Protocol::Tlsv13 - Line 74
    let protocol4 = Protocol::Tlsv13;

    // Pattern: let $CONNECTOR = native_tls::TlsConnector::builder() .min_protocol_version(Some(native_tls::Protocol::Tlsv12)) .build(); - Line 77
    let connector3 = TlsConnector::builder()
        .min_protocol_version(Some(Protocol::Tlsv12))
        .build()
        .unwrap();
}

fn openssl_version_patterns() {
    // Pattern: openssl::ssl::SslMethod::tls() - Line 85
    let method1 = SslMethod::tls();

    // Pattern: openssl::ssl::SslMethod::tlsv1() - Line 88
    let method2 = SslMethod::tlsv1();

    // Pattern: openssl::ssl::SslMethod::tlsv1_1() - Line 91
    let method3 = SslMethod::tlsv1_1();

    // Pattern: openssl::ssl::SslMethod::tlsv1_2() - Line 94
    let method4 = SslMethod::tlsv1_2();

    // Pattern: openssl::ssl::SslMethod::tlsv1_3() - Line 97
    let method5 = SslMethod::tlsv1_3();

    // Pattern: openssl::ssl::SslConnector::builder($METHOD) - Line 100
    let connector = SslConnector::builder(method4).unwrap();

    // Pattern: openssl::ssl::SslAcceptor::builder($METHOD) - Line 103
    let acceptor = SslAcceptor::builder(method4).unwrap();
}
