package gocryptoecdsa

import (
	"crypto/rand"
	"crypto/rsa"
	"fmt"
)

func nonECDSAcrypto() {
	priv, _ := rsa.GenerateKey(rand.Reader, 2048)

	_ = priv
}

func nonCryptoFunction() {
	message := "This function doesn't use cryptography"
	fmt.Println(message)
}

func confusingNames() {
	ecdsaLooking := "not actually ecdsa"
	ecdsaVar := 12345

	_, _ = ecdsaLooking, ecdsaVar
}
