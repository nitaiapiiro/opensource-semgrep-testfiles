package gocryptociphermodes

import (
	"crypto/aes"
	"crypto/cipher"
)

func ofbMode() {
	key := make([]byte, 32)
	block, _ := aes.NewCipher(key)
	iv := make([]byte, aes.BlockSize)

	stream := cipher.NewOFB(block, iv)

	_ = stream
}

func cfbMode() {
	key := make([]byte, 32)
	block, _ := aes.NewCipher(key)
	iv := make([]byte, aes.BlockSize)

	stream := cipher.NewCFB(block, iv)

	_ = stream
}

func ctrModePattern() {
	key := make([]byte, 32)
	block, _ := aes.NewCipher(key)
	iv := make([]byte, aes.BlockSize)

	stream := cipher.NewCTR(block, iv)
	dst := make([]byte, 16)
	src := make([]byte, 16)
	stream.XORKeyStream(dst, src)

	_ = dst
}

func cbcModePattern() {
	key := make([]byte, 32)
	block, _ := aes.NewCipher(key)
	iv := make([]byte, aes.BlockSize)

	mode := cipher.NewCBCEncrypter(block, iv)
	dst := make([]byte, 16)
	src := make([]byte, 16)
	mode.CryptBlocks(dst, src)

	_ = dst
}
