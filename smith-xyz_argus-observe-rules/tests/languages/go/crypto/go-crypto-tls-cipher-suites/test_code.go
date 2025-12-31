package gocryptotlsciphersuites

import (
	"crypto/tls"
)

func cipherSuitesFunctions() {
	suites1 := tls.CipherSuites()

	insecureSuites := tls.InsecureCipherSuites()

	_, _ = suites1, insecureSuites
}

func cipherSuitesConfig() {
	suites := []uint16{
		tls.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384,
		tls.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384,
	}

	config1 := &tls.Config{
		CipherSuites: suites,
	}

	config2 := &tls.Config{
		CipherSuites: []uint16{
			tls.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305,
		},
	}

	_, _ = config1, config2
}

func cipherSuitesPattern() {
	config := &tls.Config{
		CipherSuites: []uint16{
			tls.TLS_AES_256_GCM_SHA384,
			tls.TLS_AES_128_GCM_SHA256,
			tls.TLS_CHACHA20_POLY1305_SHA256,
		},
	}

	_ = config
}

func cipherSuiteConstants() {
	suite1 := tls.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384

	suite2 := tls.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305

	suite3 := tls.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384

	suite4 := tls.TLS_AES_256_GCM_SHA384

	suite5 := tls.TLS_AES_128_GCM_SHA256

	suite6 := tls.TLS_CHACHA20_POLY1305_SHA256

	_, _, _, _, _, _ = suite1, suite2, suite3, suite4, suite5, suite6
}
