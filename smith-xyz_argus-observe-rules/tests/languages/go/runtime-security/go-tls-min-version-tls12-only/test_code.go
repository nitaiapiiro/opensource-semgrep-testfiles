package main

import (
	"crypto/tls"
)

func main() {
	// Test 1: Direct MaxVersion assignment - line 12
	config1 := &tls.Config{}
	config1.MaxVersion = tls.VersionTLS12

	// Test 2: MaxVersion in struct initialization (pointer) - line 16
	config2 := &tls.Config{
		MaxVersion: tls.VersionTLS12,
	}

	// Test 3: MaxVersion in struct initialization (value) - line 21
	config3 := tls.Config{
		MaxVersion: tls.VersionTLS12,
	}

	// Test 4: Variable assignment pattern - line 26
	var version uint16 = tls.VersionTLS12
	config4 := &tls.Config{}
	config4.MaxVersion = version

	// Test 5: MaxVersion with other fields - line 31
	config5 := &tls.Config{
		MinVersion: tls.VersionTLS12,
		MaxVersion: tls.VersionTLS12,
		ServerName: "example.com",
	}

	// Test 6: MinVersion set to TLS 1.2 without MaxVersion - line 38
	config6 := &tls.Config{
		MinVersion: tls.VersionTLS12,
	}

	// Test 7: MinVersion assignment without MaxVersion - line 43
	config7 := &tls.Config{}
	config7.MinVersion = tls.VersionTLS12

	// Test 8: MinVersion with MaxVersion allowing TLS 1.3 (should match min-version rule) - line 48
	config8 := &tls.Config{
		MinVersion: tls.VersionTLS12,
		MaxVersion: tls.VersionTLS13,
	}

	// Test 9: MinVersion with other fields but no MaxVersion - line 54
	config9 := &tls.Config{
		MinVersion: tls.VersionTLS12,
		ServerName: "example.com",
		InsecureSkipVerify: false,
	}

	// Use variables to avoid unused variable errors
	_, _, _, _, _, _, _, _, _ = config1, config2, config3, config4, config5, config6, config7, config8, config9
}

