package gocryptotlsciphersuites

import (
	"crypto/tls"
	"fmt"
)

func secureTLSConfig() {
	config := &tls.Config{
		MinVersion: tls.VersionTLS12,
	}

	_ = config
}

func nonCryptoFunction() {
	message := "This function doesn't use TLS"
	fmt.Println(message)
}

func confusingNames() {
	cipherSuitesLooking := "not actually cipher suites"
	cipherSuitesVar := 12345

	_, _ = cipherSuitesLooking, cipherSuitesVar
}
