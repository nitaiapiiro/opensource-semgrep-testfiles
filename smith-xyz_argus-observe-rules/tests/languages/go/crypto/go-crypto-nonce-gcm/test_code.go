package gocryptononcegcm

import (
	"crypto/aes"
	"crypto/cipher"
)

func staticNonce() {
	key := make([]byte, 32)
	block, _ := aes.NewCipher(key)
	gcm, _ := cipher.NewGCM(block)

	nonce := []byte("123456789012")
	dst := make([]byte, 32)
	plaintext := []byte("message")
	additionalData := []byte("aad")
	ciphertext := gcm.Seal(dst, nonce, plaintext, additionalData)

	_ = ciphertext
}

func makeNonce() {
	key := make([]byte, 32)
	block, _ := aes.NewCipher(key)
	gcm, _ := cipher.NewGCM(block)

	nonce := make([]byte, 12)
	dst := make([]byte, 32)
	plaintext := []byte("message")
	additionalData := []byte("aad")
	ciphertext := gcm.Seal(dst, nonce, plaintext, additionalData)

	_ = ciphertext
}

func directStaticNonce() {
	key := make([]byte, 32)
	block, _ := aes.NewCipher(key)
	gcm, _ := cipher.NewGCM(block)

	dst := make([]byte, 32)
	plaintext := []byte("message")
	additionalData := []byte("aad")
	ciphertext := gcm.Seal(dst, []byte("123456789012"), plaintext, additionalData)

	_ = ciphertext
}

func nonceLoopPattern() {
	key := make([]byte, 32)
	block, _ := aes.NewCipher(key)
	gcm, _ := cipher.NewGCM(block)

	nonce := make([]byte, 12)
	dst := make([]byte, 32)
	plaintext := []byte("message")
	additionalData := []byte("aad")

	for i := 0; i < 10; i++ {
		ciphertext := gcm.Seal(dst, nonce, plaintext, additionalData)
		_ = ciphertext
	}
}
