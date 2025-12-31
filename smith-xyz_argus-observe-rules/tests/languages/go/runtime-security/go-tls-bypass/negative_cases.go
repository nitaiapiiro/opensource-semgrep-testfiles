package main

import (
	"crypto/tls"
	"crypto/x509"
	"database/sql"
	"net/http"
)

func main() {
	// Secure TLS configuration - should NOT trigger
	config := &tls.Config{
		InsecureSkipVerify: false,
		ServerName:         "example.com",
		MinVersion:         tls.VersionTLS12,
	}

	// Proper variable assignment - should NOT trigger
	secureVar := false
	_ = secureVar                     // Use variable to avoid unused variable error
	config.InsecureSkipVerify = false // Direct assignment instead

	// Secure TLS Config struct - should NOT trigger
	tlsConfig := tls.Config{
		ServerName:         "example.com",
		InsecureSkipVerify: false,
		MinVersion:         tls.VersionTLS12,
	}

	// Secure Kubernetes REST client - should NOT trigger
	restConfig.TLSClientConfig.Insecure = false
	restConfig.Insecure = false

	// Proper conditional with secure config - should NOT trigger
	if isProduction() {
		config.MinVersion = tls.VersionTLS12 // Use different field
	}

	// HTTPS URL (not HTTP) - should NOT trigger
	url := "https://" + hostname
	http.Get(url)

	// Proper certificate validation - should NOT trigger
	config.VerifyPeerCertificate = verifyCert

	// TLS enabled properly - should NOT trigger
	kafkaConfig.TLS.Enable = true
	networkConfig.Net.TLS.Enable = true
	sslConfig.SSL.Enable = true

	// Proper TLS configuration strings - should NOT trigger
	mysqlConfig.TLSConfig = "preferred"
	postgresConfig.SSLMode = "require"

	// Secure DSN connections - should NOT trigger
	sql.Open("postgres", connectionString+"?sslmode=require")
	sql.Open("mysql", dsn+"&tls=true")
	sql.Open("driver", connStr+"?ssl=true")

	// Comments mentioning insecure - should NOT trigger
	// This code uses InsecureSkipVerify = false for security
	/*
	 * Never set InsecureSkipVerify to true in production
	 */
}

// Helper functions
func isProduction() bool {
	return true
}

func verifyCert(rawCerts [][]byte, verifiedChains [][]*x509.Certificate) error {
	// Custom certificate verification logic
	return nil
}
