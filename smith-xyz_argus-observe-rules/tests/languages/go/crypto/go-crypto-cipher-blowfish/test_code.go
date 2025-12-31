package gocryptocipherblowfish

import (
	"crypto/cipher"

	"golang.org/x/crypto/blowfish"
)

func blowfishCipher() {
	key := make([]byte, 32)

	cipher1, _ := blowfish.NewCipher(key)

	cipher2, _ := blowfish.NewSaltedCipher(key, []byte("salt"))

	_, _ = cipher1, cipher2
}

func blowfishModes() {
	key := make([]byte, 32)
	iv := make([]byte, blowfish.BlockSize)

	cbcEncrypter := cipher.NewCBCEncrypter(blowfish.NewCipher(key), iv)

	cbcDecrypter := cipher.NewCBCDecrypter(blowfish.NewCipher(key), iv)

	ofb := cipher.NewOFB(blowfish.NewCipher(key), iv)

	cfbEncrypter := cipher.NewCFBEncrypter(blowfish.NewCipher(key), iv)

	cfbDecrypter := cipher.NewCFBDecrypter(blowfish.NewCipher(key), iv)

	ctr := cipher.NewCTR(blowfish.NewCipher(key), iv)

	_, _, _, _, _, _ = cbcEncrypter, cbcDecrypter, ofb, cfbEncrypter, cfbDecrypter, ctr
}

func blowfishPatterns() {
	key := make([]byte, 32)
	iv := make([]byte, blowfish.BlockSize)

	block := blowfish.NewCipher(key)
	cbcEncrypter := cipher.NewCBCEncrypter(block, iv)

	block2, _ := blowfish.NewSaltedCipher(key, []byte("salt"))
	cbcDecrypter := cipher.NewCBCDecrypter(block2, iv)

	_, _ = cbcEncrypter, cbcDecrypter
}

func blowfishInterface() {
	key := make([]byte, 32)

	var cipherBlock cipher.Block = blowfish.NewCipher(key)

	cipherBlock2 := cipher.Block(blowfish.NewCipher(key))

	_, _ = cipherBlock, cipherBlock2
}

func blowfishFunctionParameter() {
	key := make([]byte, 32)

	useCipher(blowfish.NewCipher(key))
}

func useCipher(c cipher.Block) {
	_ = c
}
