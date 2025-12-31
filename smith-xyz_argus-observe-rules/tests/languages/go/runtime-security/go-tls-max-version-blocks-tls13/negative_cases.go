package main

import (
	"crypto/tls"
	"net/http"
)

func goodTLSConfigurations() {
	// Good: MaxVersion allows TLS 1.3
	config1 := &tls.Config{
		MaxVersion: tls.VersionTLS13,
	}

	// Good: MinVersion set to TLS 1.3
	config2 := &tls.Config{
		MinVersion: tls.VersionTLS13,
	}

	// Good: Both MinVersion and MaxVersion allow TLS 1.3
	config3 := &tls.Config{
		MinVersion: tls.VersionTLS12,
		MaxVersion: tls.VersionTLS13,
	}

	// Good: No version restrictions (uses defaults)
	config4 := &tls.Config{
		ServerName: "example.com",
	}

	// Good: MinVersion 1.2 with MaxVersion 1.3 (allows TLS 1.3)
	config5 := &tls.Config{
		MinVersion: tls.VersionTLS12,
		MaxVersion: tls.VersionTLS13,
	}

	// Good: HTTP client without custom TLS config
	client1 := &http.Client{}

	// Good: HTTP client with default transport
	client2 := &http.Client{
		Transport: http.DefaultTransport,
	}

	// Use variables
	_, _, _, _, _, _, _ = config1, config2, config3, config4, config5, client1, client2
}

