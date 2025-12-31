package gocryptocipherrc2

import (
	"crypto/cipher"

	"golang.org/x/crypto/rc2"
)

func rc2Cipher() {
	key := make([]byte, 16)

	cipher1, _ := rc2.NewCipher(key, 8)

	cipher2 := rc2.NewCipher(key, 16)

	_, _ = cipher1, cipher2
}

func rc2Modes() {
	key := make([]byte, 16)
	iv := make([]byte, 8)

	cbcEncrypter := cipher.NewCBCEncrypter(rc2.NewCipher(key, 8), iv)

	cbcDecrypter := cipher.NewCBCDecrypter(rc2.NewCipher(key, 8), iv)

	_, _ = cbcEncrypter, cbcDecrypter
}

func rc2Pattern() {
	key := make([]byte, 16)
	iv := make([]byte, 8)

	block := rc2.NewCipher(key, 8)
	cbcEncrypter := cipher.NewCBCEncrypter(block, iv)

	_ = cbcEncrypter
}
