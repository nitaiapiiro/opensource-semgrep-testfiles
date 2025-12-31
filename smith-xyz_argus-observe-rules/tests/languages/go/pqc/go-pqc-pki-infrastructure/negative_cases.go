package main

import (
	"crypto/ecdsa"
	"crypto/rsa"
	"crypto/x509"
	"fmt"
)

// Negative test cases - these should NOT be flagged

// Certificate handling with flexible algorithm support
func flexibleCertificateHandling(cert *x509.Certificate) {
	// Check if algorithm is supported, don't hard-fail
	if isAlgorithmSupported(cert.SignatureAlgorithm) {
		fmt.Println("Supported algorithm")
	} else {
		fmt.Println("Unsupported algorithm, using fallback")
	}
}

func isAlgorithmSupported(alg x509.SignatureAlgorithm) bool {
	// Flexible algorithm checking
	supportedAlgorithms := getSupportedAlgorithms()
	for _, supported := range supportedAlgorithms {
		if alg == supported {
			return true
		}
	}
	return false
}

func getSupportedAlgorithms() []x509.SignatureAlgorithm {
	return []x509.SignatureAlgorithm{
		x509.SHA256WithRSA,
		x509.ECDSAWithSHA256,
		// Could include PQC algorithms in the future
	}
}

// Public key handling with fallback
func handlePublicKeyFlexibly(cert *x509.Certificate) {
	switch key := cert.PublicKey.(type) {
	case *rsa.PublicKey:
		handleRSAKey(key)
	case *ecdsa.PublicKey:
		handleECDSAKey(key)
	default:
		// Graceful handling of unknown key types
		handleUnknownKey(key)
	}
}

func handleRSAKey(key *rsa.PublicKey) {
	// RSA key handling
}

func handleECDSAKey(key *ecdsa.PublicKey) {
	// ECDSA key handling
}

func handleUnknownKey(key interface{}) {
	// Graceful fallback for unknown key types
	fmt.Printf("Unknown key type: %T\n", key)
}

// Non-certificate related operations
func regularStringOperations() {
	message := "Certificate processing complete"
	fmt.Println(message)
}

// Certificate-related but not PKI operations
func printCertificateInfo(cert *x509.Certificate) {
	fmt.Printf("Subject: %s\n", cert.Subject)
	fmt.Printf("Issuer: %s\n", cert.Issuer)
	fmt.Printf("Not Before: %s\n", cert.NotBefore)
	fmt.Printf("Not After: %s\n", cert.NotAfter)
}
