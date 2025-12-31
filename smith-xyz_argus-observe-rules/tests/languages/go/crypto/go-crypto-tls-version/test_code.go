package gocryptotlsversion

import (
	"crypto/tls"
)

func tlsVersionConfig() {
	config1 := &tls.Config{
		MinVersion: tls.VersionSSL30,
	}

	config2 := &tls.Config{
		MinVersion: tls.VersionTLS10,
	}

	config3 := &tls.Config{
		MinVersion: tls.VersionTLS11,
	}

	_ = config1
	_ = config2
	_ = config3
}

func tlsVersionDirect() {
	version1 := tls.VersionSSL30

	version2 := tls.VersionTLS10

	version3 := tls.VersionTLS11

	_, _, _ = version1, version2, version3
}

func tlsVersionInConfig() {
	config := &tls.Config{
		MinVersion: tls.VersionTLS10,
		MaxVersion: tls.VersionTLS11,
	}

	_ = config
}
