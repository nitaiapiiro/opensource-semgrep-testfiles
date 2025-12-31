package gocryptociphersuites

import (
	"crypto/tls"
	"fmt"
)

func secureCipherSuites() {
	suite1 := tls.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384

	suite2 := tls.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384

	suite3 := tls.TLS_AES_256_GCM_SHA384

	_, _, _ = suite1, suite2, suite3
}

func nonCryptoFunction() {
	message := "This function doesn't use cipher suites"
	fmt.Println(message)
}

func confusingNames() {
	cipherSuitesLooking := "not actually cipher suites"
	cipherSuitesVar := 12345

	_, _ = cipherSuitesLooking, cipherSuitesVar
}
