package gocryptoaesecbmode

import (
	"crypto/aes"
	"crypto/cipher"
	"fmt"
)

func secureCipherModes() {
	key := make([]byte, 32)
	block, _ := aes.NewCipher(key)
	iv := make([]byte, aes.BlockSize)

	cbcEncrypter := cipher.NewCBCEncrypter(block, iv)

	gcm, _ := cipher.NewGCM(block)

	_, _ = cbcEncrypter, gcm
}

func nonCryptoFunction() {
	message := "This function doesn't use ECB mode"
	fmt.Println(message)
}

func confusingNames() {
	ecbLooking := "not actually ecb"
	ecbVar := 12345

	_, _ = ecbLooking, ecbVar
}
