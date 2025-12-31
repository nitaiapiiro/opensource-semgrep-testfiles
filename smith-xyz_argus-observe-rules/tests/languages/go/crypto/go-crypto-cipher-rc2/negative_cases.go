package gocryptocipherrc2

import (
	"crypto/aes"
	"crypto/cipher"
	"fmt"
)

func secureCiphers() {
	key := make([]byte, 32)
	cipher, _ := aes.NewCipher(key)

	_ = cipher
}

func nonCryptoFunction() {
	message := "This function doesn't use RC2"
	fmt.Println(message)
}

func confusingNames() {
	rc2Looking := "not actually rc2"
	rc2Var := 12345

	_, _ = rc2Looking, rc2Var
}
