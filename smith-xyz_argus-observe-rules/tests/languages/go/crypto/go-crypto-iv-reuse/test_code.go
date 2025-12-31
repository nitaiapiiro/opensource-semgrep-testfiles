package gocryptoivreuse

import (
	"crypto/aes"
	"crypto/cipher"
)

func staticIV() {
	key := make([]byte, 32)
	block, _ := aes.NewCipher(key)

	iv := []byte("1234567890123456")
	encrypter := cipher.NewCBCEncrypter(block, iv)

	_ = encrypter
}

func makeIV() {
	key := make([]byte, 32)
	block, _ := aes.NewCipher(key)

	iv := make([]byte, 16)
	encrypter := cipher.NewCBCEncrypter(block, iv)

	_ = encrypter
}

func varStaticIV() {
	key := make([]byte, 32)
	block, _ := aes.NewCipher(key)
	gcm, _ := cipher.NewGCM(block)

	var iv = []byte("1234567890123456")
	dst := make([]byte, 32)
	plaintext := []byte("message")
	additionalData := []byte("aad")
	ciphertext := gcm.Seal(dst, iv, plaintext, additionalData)

	_ = ciphertext
}

func directStaticIV() {
	key := make([]byte, 32)
	block, _ := aes.NewCipher(key)

	encrypter := cipher.NewCBCEncrypter(block, []byte("1234567890123456"))

	decrypter := cipher.NewCBCDecrypter(block, []byte("1234567890123456"))

	_, _ = encrypter, decrypter
}
