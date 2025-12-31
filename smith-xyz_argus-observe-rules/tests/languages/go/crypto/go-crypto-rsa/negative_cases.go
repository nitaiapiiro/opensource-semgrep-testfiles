package gocryptorsa

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"fmt"
)

func nonRSACrypto() {
	priv, _ := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)

	_ = priv
}

func nonCryptoFunction() {
	message := "This function doesn't use any cryptography"
	fmt.Println(message)
}

func confusingNames() {
	rsaLooking := "not actually rsa"
	rsaVar := 12345

	_, _ = rsaLooking, rsaVar
}
