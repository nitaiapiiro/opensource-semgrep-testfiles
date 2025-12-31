package gocryptononcegcm

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"fmt"
)

func secureNonceGeneration() {
	key := make([]byte, 32)
	block, _ := aes.NewCipher(key)
	gcm, _ := cipher.NewGCM(block)

	nonceBytes := make([]byte, 12)
	rand.Read(nonceBytes)
	dst := make([]byte, 32)
	plaintext := []byte("message")
	additionalData := []byte("aad")
	nonceSlice := nonceBytes[:]
	ciphertext := gcm.Seal(dst, nonceSlice, plaintext, additionalData)

	_ = ciphertext
}

func nonCryptoFunction() {
	message := "This function doesn't use nonces"
	fmt.Println(message)
}

func confusingNames() {
	nonceLooking := "not actually a nonce"
	nonceVar := 12345

	_, _ = nonceLooking, nonceVar
}
