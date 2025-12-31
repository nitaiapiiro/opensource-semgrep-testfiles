package main

import (
	"crypto/tls"
	"net/http"
)

func main() {
	// Test 1: HTTP Client with TLS config in Transport - line 12
	customTLS1 := &tls.Config{
		MinVersion: tls.VersionTLS12,
	}
	client1 := &http.Client{
		Transport: &http.Transport{
			TLSClientConfig: customTLS1,
		},
	}

	// Test 2: HTTP Transport with TLS config - line 22
	transport1 := &http.Transport{
		TLSClientConfig: &tls.Config{
			MaxVersion: tls.VersionTLS12,
		},
	}

	// Test 3: Direct Transport TLS config assignment - line 30
	transport2 := &http.Transport{}
	transport2.TLSClientConfig = &tls.Config{
		MinVersion: tls.VersionTLS12,
	}

	// Test 4: HTTP Client creation with Transport, then TLS config assignment - line 37
	transport3 := &http.Transport{}
	client2 := &http.Client{
		Transport: transport3,
	}
	transport3.TLSClientConfig = &tls.Config{
		MaxVersion: tls.VersionTLS12,
	}

	// Test 5: HTTP Client with Transport and other fields - line 47
	client3 := &http.Client{
		Timeout:   30,
		Transport: &http.Transport{
			TLSClientConfig: &tls.Config{
				ServerName: "example.com",
			},
		},
	}

	// Test 6: Transport with TLS config and other fields - line 57
	transport4 := &http.Transport{
		MaxIdleConns: 100,
		TLSClientConfig: &tls.Config{
			InsecureSkipVerify: false,
		},
	}

	// Test 7: Multiple transports with TLS configs - line 66
	transport5 := &http.Transport{
		TLSClientConfig: customTLS1,
	}
	transport6 := &http.Transport{
		TLSClientConfig: &tls.Config{
			MinVersion: tls.VersionTLS13,
		},
	}

	// Use variables to avoid unused variable errors
	_, _, _, _, _, _, _, _ = client1, transport1, transport2, client2, client3, transport4, transport5, transport6
}

