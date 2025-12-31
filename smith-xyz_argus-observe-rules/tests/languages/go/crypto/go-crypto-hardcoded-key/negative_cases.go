package gocryptohardcodedkey

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"fmt"
)

func secureKeyGeneration() {
	key := make([]byte, 32)
	rand.Read(key)
	cipher, _ := aes.NewCipher(key)

	_ = cipher
}

func nonCryptoFunction() {
	message := "This function doesn't use encryption"
	fmt.Println(message)
}

func confusingNames() {
	keyLooking := "not actually a key"
	keyVar := 12345

	_, _ = keyLooking, keyVar
}

func secureIVGeneration() {
	key := make([]byte, 32)
	rand.Read(key)
	block, _ := aes.NewCipher(key)
	iv := make([]byte, aes.BlockSize)
	rand.Read(iv)

	encrypter := cipher.NewCFBEncrypter(block, iv)

	_ = encrypter
}
