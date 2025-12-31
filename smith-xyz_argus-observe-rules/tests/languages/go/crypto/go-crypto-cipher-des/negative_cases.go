package gocryptocipherdes

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
	desLooking := "not actually des"
	desVar := 12345

	_, _ = desLooking, desVar
}

func secureCipherModes() {
	key := make([]byte, 32)
	block, _ := aes.NewCipher(key)
	iv := make([]byte, aes.BlockSize)

	gcm, _ := cipher.NewGCM(block)

	_ = gcm
}
