package main

import (
	"crypto/tls"
)

// Test cases for go-pqc-hardcoded-cipher-dependencies rule

// SHOULD BE FLAGGED: Hardcoded cipher suite assignment
func configureHardcodedCiphers() {
	config := &tls.Config{}
	config.CipherSuites = []uint16{
		tls.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384,
		tls.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
	}
}

// SHOULD BE FLAGGED: Hardcoded TLS config with cipher suites
func createTLSConfig() *tls.Config {
	return &tls.Config{
		CipherSuites: []uint16{
			tls.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384,
		},
		MinVersion: tls.VersionTLS12,
	}
}

// SHOULD BE FLAGGED: Hardcoded signature schemes
func configureSignatureSchemes() {
	config := &tls.Config{}
	config.SignatureSchemes = []tls.SignatureScheme{
		tls.ECDSAWithP256AndSHA256,
		tls.RSASSAPSSWithSHA256,
	}
}

// SHOULD BE FLAGGED: Hardcoded curve preferences
func configureCurvePreferences() {
	config := &tls.Config{}
	config.CurvePreferences = []tls.CurveID{
		tls.CurveP256,
		tls.CurveP384,
	}
}

// SHOULD BE FLAGGED: Hardcoded certificate types
func configureClientCertTypes() {
	config := &tls.Config{}
	config.ClientCertificateTypes = []byte{
		byte(tls.CertTypeRSASign),
		byte(tls.CertTypeECDSASign),
	}
}

// SHOULD BE FLAGGED: Variable assignment with hardcoded ciphers
func assignCipherSuites() {
	ciphers := []uint16{
		tls.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384,
		tls.TLS_RSA_WITH_AES_256_GCM_SHA384,
	}
	_ = ciphers
}
