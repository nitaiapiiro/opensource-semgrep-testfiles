package gocryptocertificatevalidation

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
	insecureSkipVerifyLooking := "not actually insecure skip verify"
	insecureSkipVerifyVar := 12345

	_, _ = insecureSkipVerifyLooking, insecureSkipVerifyVar
}
