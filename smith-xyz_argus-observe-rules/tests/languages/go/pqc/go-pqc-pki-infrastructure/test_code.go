package main

import (
	"crypto/ecdsa"
	"crypto/rsa"
	"crypto/sha256"
	"crypto/tls"
	"crypto/x509"
	"errors"
)

// Test cases for go-pqc-pki-infrastructure rule (includes our added patterns)

// SHOULD BE FLAGGED: Certificate parsing
func parseCertificate(certBytes []byte) (*x509.Certificate, error) {
	cert, err := x509.ParseCertificate(certBytes)
	return cert, err
}

func parseCertificates(certBytes []byte) ([]*x509.Certificate, error) {
	certs, err := x509.ParseCertificates(certBytes)
	return certs, err
}

// SHOULD BE FLAGGED: Certificate verification
func verifyCertificate(cert *x509.Certificate, parent *x509.Certificate) error {
	return cert.CheckSignatureFrom(parent)
}

// SHOULD BE FLAGGED: Signature algorithm validation (our added pattern)
func validateSignatureAlgorithm(cert *x509.Certificate) error {
	if cert.SignatureAlgorithm != x509.SHA256WithRSA {
		return errors.New("unsupported signature algorithm")
	}
	return nil
}

func checkSignatureAlgorithm(cert *x509.Certificate) {
	switch cert.SignatureAlgorithm {
	case x509.SHA256WithRSA:
		// Handle RSA
	case x509.ECDSAWithSHA256:
		// Handle ECDSA
	default:
		// Unknown algorithm
	}
}

// SHOULD BE FLAGGED: Public key type checking (our added patterns)
func extractRSAPublicKey(cert *x509.Certificate) *rsa.PublicKey {
	if key := cert.PublicKey.(*rsa.PublicKey); key != nil {
		return key
	}
	return nil
}

func extractECDSAPublicKey(cert *x509.Certificate) *ecdsa.PublicKey {
	if key := cert.PublicKey.(*ecdsa.PublicKey); key != nil {
		return key
	}
	return nil
}

func handlePublicKey(cert *x509.Certificate) {
	switch key := cert.PublicKey.(type) {
	case *rsa.PublicKey:
		// Handle RSA key
		_ = key
	case *ecdsa.PublicKey:
		// Handle ECDSA key
		_ = key
	default:
		// Unknown key type
	}
}

// SHOULD BE FLAGGED: Custom certificate verification (our added pattern)
func setupCustomVerification() *tls.Config {
	return &tls.Config{
		VerifyPeerCertificate: func(certs [][]byte, chains [][]*x509.Certificate) error {
			// Custom verification logic
			for _, cert := range chains[0] {
				if cert.SignatureAlgorithm == x509.SHA1WithRSA {
					return errors.New("weak signature algorithm")
				}
			}
			return nil
		},
	}
}

// SHOULD BE FLAGGED: System certificate pool and fingerprinting (our added patterns)
func loadSystemCerts() (*x509.CertPool, error) {
	return x509.SystemCertPool()
}

func fingerprintCertificate(cert *x509.Certificate) [32]byte {
	return sha256.Sum256(cert.Raw)
}

// SHOULD BE FLAGGED: Existing PKI patterns
func createCertificate() {
	// This would be flagged by existing patterns
	template := &x509.Certificate{}
	_, err := x509.CreateCertificate(nil, template, template, nil, nil)
	_ = err
}
