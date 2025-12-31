package main

import (
	"crypto"
	"crypto/tls"
	"strings"
)

// Negative test cases - these should NOT be flagged

// Signature verification WITH fallback mechanism
func verifySignatureWithFallback(pub crypto.PublicKey, message, signature []byte) error {
	if supportsPQC {
		// Try PQC signature verification
		return verifyWithPQC(pub, message, signature)
	} else {
		// Fallback to traditional verification
		return verifyWithClassical(pub, message, signature)
	}
}

func verifyWithPQC(pub crypto.PublicKey, message, signature []byte) error {
	// PQC verification logic
	return nil
}

func verifyWithClassical(pub crypto.PublicKey, message, signature []byte) error {
	// Classical verification logic
	return nil
}

// Multiple cipher suites for negotiation
func configureMultipleCiphers() *tls.Config {
	config := &tls.Config{
		CipherSuites: []uint16{
			tls.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384,
			tls.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
			tls.TLS_RSA_WITH_AES_256_GCM_SHA384,
		},
	}
	return config
}

// Error handling WITH algorithm compatibility consideration
func handleErrorWithRetry(err error) error {
	if strings.Contains(err.Error(), "unsupported") {
		// Try fallback algorithm
		return retryWithFallbackAlgorithm()
	}
	return err
}

func retryWithFallbackAlgorithm() error {
	// Fallback implementation
	return nil
}

// Configuration with algorithm support detection
var supportsPQC bool

func checkAlgorithmSupport(alg string) bool {
	// Algorithm capability detection
	return true
}

func configureWithCapabilityCheck() {
	supported := checkAlgorithmSupport("ML-KEM")
	if supported {
		// Use advanced algorithms
	} else {
		// Use compatible algorithms
	}
}
