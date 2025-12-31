package gocryptochacha20

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"fmt"
)

func otherCiphers() {
	key := make([]byte, 32)
	rand.Read(key)
	block, _ := aes.NewCipher(key)
	iv := make([]byte, aes.BlockSize)
	rand.Read(iv)

	gcm, _ := cipher.NewGCM(block)

	_ = gcm
}

func nonCryptoFunction() {
	message := "This function doesn't use any encryption"
	fmt.Println(message)
}

func confusingNames() {
	chacha20Looking := "not actually chacha20"
	chacha20Var := 12345

	_, _ = chacha20Looking, chacha20Var
}
