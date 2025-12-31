package gocryptokeylength

import (
	"crypto/aes"
)

func keyLength8() {
	key := make([]byte, 8)
	cipher, _ := aes.NewCipher(key)

	_ = cipher
}

func keyLength16() {
	key := make([]byte, 16)
	cipher, _ := aes.NewCipher(key)

	_ = cipher
}

func keyStringLiteral() {
	cipher, _ := aes.NewCipher([]byte("1234567890123456"))

	_ = cipher
}

func keyLengthValidation() {
	key16 := make([]byte, 16)
	cipher16, _ := aes.NewCipher(key16)

	key24 := make([]byte, 24)
	cipher24, _ := aes.NewCipher(key24)

	key32 := make([]byte, 32)
	cipher32, _ := aes.NewCipher(key32)

	_, _, _ = cipher16, cipher24, cipher32
}
