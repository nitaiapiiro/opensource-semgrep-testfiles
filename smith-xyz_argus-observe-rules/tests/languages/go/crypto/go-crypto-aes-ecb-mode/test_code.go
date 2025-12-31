package gocryptoaesecbmode

import (
	"crypto/aes"
	"crypto/cipher"
)

func ecbDecrypter() {
	key := make([]byte, 32)
	block, _ := aes.NewCipher(key)

	decrypter := cipher.NewECBDecrypter(block)

	_ = decrypter
}

func ecbEncrypter() {
	key := make([]byte, 32)
	block, _ := aes.NewCipher(key)

	encrypter := cipher.NewECBEncrypter(block)

	_ = encrypter
}

func ecbDecrypterPattern() {
	key := make([]byte, 32)
	block, _ := aes.NewCipher(key)
	decrypter := cipher.NewECBDecrypter(block)

	_ = decrypter
}

func ecbEncrypterPattern() {
	key := make([]byte, 32)
	block, _ := aes.NewCipher(key)
	encrypter := cipher.NewECBEncrypter(block)

	_ = encrypter
}
