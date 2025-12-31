package gocryptohardcodedkey

import (
	"crypto/aes"
	"crypto/cipher"
)

func hardcodedKeyPattern() {
	key := "1234567890123456"
	cipher, _ := aes.NewCipher([]byte(key))

	_ = cipher
}

func hardcodedKeyDirect() {
	cipher, _ := aes.NewCipher([]byte("1234567890123456"))

	_ = cipher
}

func hardcodedIVPattern() {
	key := make([]byte, 32)
	block, _ := aes.NewCipher(key)
	iv := "1234567890123456"

	decrypter := cipher.NewCFBDecrypter(block, []byte(iv))

	_ = decrypter
}

func hardcodedKeyUsage() {
	key := "my-secret-key-12345"
	cipher, _ := aes.NewCipher([]byte(key))

	_ = cipher
}
