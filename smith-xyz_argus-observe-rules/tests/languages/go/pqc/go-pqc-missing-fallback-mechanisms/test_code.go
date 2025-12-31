package main

import (
	"crypto"
	"crypto/tls"
	"errors"
	"strings"
)

// Test cases for go-pqc-missing-fallback-mechanisms rule

// SHOULD BE FLAGGED: Signature verification without fallback
func verifySignatureWithoutFallback(pub crypto.PublicKey, message, signature []byte) error {
	valid := pub.Verify(message, signature, nil)
	if !valid {
		return errors.New("signature verification failed")
	}
	return nil
}

// SHOULD BE FLAGGED: Another signature verification pattern
func checkSignature(key crypto.PublicKey, data, sig []byte) bool {
	result := key.Verify(data, sig, nil)
	if !result {
		return false
	}
	return true
}

// SHOULD BE FLAGGED: Single cipher suite configuration
func configureSingleCipher() *tls.Config {
	config := &tls.Config{
		CipherSuites: []uint16{tls.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384},
	}
	return config
}

// SHOULD BE FLAGGED: Error handling that doesn't consider algorithm incompatibility
func handleUnsupportedError(err error) error {
	if strings.Contains(err.Error(), "unsupported") {
		return err
	}
	return nil
}

func handleUnknownError(err error) error {
	if strings.Contains(err.Error(), "unknown") {
		return err
	}
	return nil
}

// SHOULD BE FLAGGED: Cipher suite assignment without capability detection
func assignCipherSuites() {
	config := &tls.Config{}
	config.CipherSuites = []uint16{
		tls.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384,
		tls.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384,
	}
}
