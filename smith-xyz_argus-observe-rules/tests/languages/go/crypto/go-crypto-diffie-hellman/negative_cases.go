package gocryptodiffiehellman

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"fmt"
)

func otherKeyExchange() {
	priv, _ := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)

	_ = priv
}

func nonCryptoFunction() {
	message := "This function doesn't use key exchange"
	fmt.Println(message)
}

func confusingNames() {
	dhLooking := "not actually diffie-hellman"
	dhVar := 12345

	_, _ = dhLooking, dhVar
}
