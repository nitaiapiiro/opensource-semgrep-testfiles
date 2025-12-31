package gocryptocipherrc4

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
	message := "This function doesn't use encryption"
	fmt.Println(message)
}

func confusingNames() {
	rc4Looking := "not actually rc4"
	rc4Var := 12345

	_, _ = rc4Looking, rc4Var
}

func secureStreamCiphers() {
	key := make([]byte, 32)
	block, _ := aes.NewCipher(key)
	iv := make([]byte, aes.BlockSize)

	ctr := cipher.NewCTR(block, iv)

	_ = ctr
}
