package main

import (
	"crypto/tls"
	"os"
)

// Negative test cases - these should NOT be flagged

// Dynamic cipher suite configuration
func configureDynamicCiphers() {
	var ciphers []uint16
	if os.Getenv("SECURE_MODE") == "true" {
		ciphers = getSecureCiphers()
	} else {
		ciphers = getDefaultCiphers()
	}

	config := &tls.Config{
		CipherSuites: ciphers,
	}
	_ = config
}

func getSecureCiphers() []uint16 {
	return []uint16{tls.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384}
}

func getDefaultCiphers() []uint16 {
	return []uint16{tls.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256}
}

// Using default TLS configuration
func useDefaultTLSConfig() {
	config := &tls.Config{
		MinVersion: tls.VersionTLS12,
		// No hardcoded cipher suites - using defaults
	}
	_ = config
}

// Non-TLS related uint16 slices
func regularUint16Slice() {
	ports := []uint16{80, 443, 8080}
	_ = ports
}

// Configuration loaded from external source
func loadTLSConfigFromFile() {
	// This would typically load from config file
	config := &tls.Config{
		ServerName: "example.com",
	}
	_ = config
}
