package gocryptoivreuse

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"fmt"
)

func secureIVGeneration() {
	key := make([]byte, 32)
	block, _ := aes.NewCipher(key)

	iv := make([]byte, aes.BlockSize)
	rand.Read(iv)
	gcm, _ := cipher.NewGCM(block)

	_ = gcm
}

func nonCryptoFunction() {
	message := "This function doesn't use IVs"
	fmt.Println(message)
}

func confusingNames() {
	ivLooking := "not actually an IV"
	ivVar := 12345

	_, _ = ivLooking, ivVar
}
