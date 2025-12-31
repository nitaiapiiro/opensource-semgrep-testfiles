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

	// Good: MinVersion set to TLS 1.3 (not 1.2)
	config3 := &tls.Config{
		MinVersion: tls.VersionTLS13,
		MaxVersion: tls.VersionTLS13,
	}

	// Good: No version restrictions (uses defaults)
	config4 := &tls.Config{
		ServerName: "example.com",
	}

	// Good: MinVersion 1.2 with MaxVersion 1.2 (blocks TLS 1.3, caught by max-version rule, not min-version-only)
	// This should NOT match min-version-only rule because MaxVersion is set to TLS 1.2
	config5 := &tls.Config{
		MinVersion: tls.VersionTLS12,
		MaxVersion: tls.VersionTLS12,
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

