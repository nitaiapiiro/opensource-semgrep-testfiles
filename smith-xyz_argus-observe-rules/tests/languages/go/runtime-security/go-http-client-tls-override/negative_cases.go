package main

import (
	"crypto/tls"
	"net/http"
)

func goodHTTPClientConfigurations() {
	// Good: HTTP client without custom TLS config
	client1 := &http.Client{}

	// Good: HTTP client with default transport
	client2 := &http.Client{
		Transport: http.DefaultTransport,
	}

	// Good: HTTP client with custom transport but no TLS config
	client3 := &http.Client{
		Transport: &http.Transport{
			MaxIdleConns: 100,
		},
	}

	// Good: TLS config not used in HTTP client context
	tlsConfig := &tls.Config{
		MinVersion: tls.VersionTLS12,
	}
	_ = tlsConfig

	// Good: HTTP client with transport that doesn't set TLSClientConfig
	transport1 := &http.Transport{
		MaxIdleConns:       100,
		MaxIdleConnsPerHost: 10,
	}
	client4 := &http.Client{
		Transport: transport1,
	}

	// Good: Direct TLS connection (not HTTP client)
	conn, _ := tls.Dial("tcp", "example.com:443", &tls.Config{
		MinVersion: tls.VersionTLS12,
	})
	_ = conn

	// Use variables
	_, _, _, _, _ = client1, client2, client3, client4, conn
}

