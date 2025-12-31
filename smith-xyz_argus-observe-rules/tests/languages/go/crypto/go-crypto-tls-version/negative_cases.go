package gocryptotlsversion

import (
	"fmt"
)

func secureTLSVersions() {
	config1 := struct {
		MinVersion int
	}{
		MinVersion: 0x0303,
	}

	config2 := struct {
		MinVersion int
	}{
		MinVersion: 0x0304,
	}

	_ = config1
	_ = config2
}

func nonCryptoFunction() {
	message := "This function doesn't use TLS"
	fmt.Println(message)
}

func confusingNames() {
	tlsLooking := "not actually tls"
	tlsVar := 12345

	_, _ = tlsLooking, tlsVar
}
