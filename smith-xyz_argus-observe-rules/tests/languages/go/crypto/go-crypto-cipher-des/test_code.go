package gocryptocipherdes

import (
	"crypto/cipher"
	"crypto/des"
)

func desCipher() {
	key := make([]byte, 8)

	cipher1, _ := des.NewCipher(key)

	cipher2, _ := des.NewTripleDESCipher(key)

	_, _ = cipher1, cipher2
}

func desCipherModes() {
	key := make([]byte, 8)
	iv := make([]byte, des.BlockSize)

	block, _ := des.NewCipher(key)

	cbcEncrypter := cipher.NewCBCEncrypter(block, iv)

	cbcDecrypter := cipher.NewCBCDecrypter(des.NewCipher(key), iv)

	ofb := cipher.NewOFB(des.NewCipher(key), iv)

	cfbEncrypter := cipher.NewCFBEncrypter(des.NewCipher(key), iv)

	cfbDecrypter := cipher.NewCFBDecrypter(des.NewCipher(key), iv)

	ctr := cipher.NewCTR(des.NewCipher(key), iv)

	_, _, _, _, _, _, _ = cbcEncrypter, cbcDecrypter, ofb, cfbEncrypter, cfbDecrypter, ctr, block
}

func desBlockPatterns() {
	key := make([]byte, 8)
	iv := make([]byte, des.BlockSize)

	block := des.NewCipher(key)
	cbcEncrypter := cipher.NewCBCEncrypter(block, iv)

	block2 := des.NewCipher(key)
	cbcDecrypter := cipher.NewCBCDecrypter(block2, iv)

	_, _ = cbcEncrypter, cbcDecrypter
}

func tripleDESPattern() {
	key := make([]byte, 24)
	iv := make([]byte, des.BlockSize)

	block := des.NewTripleDESCipher(key)
	cbcEncrypter := cipher.NewCBCEncrypter(block, iv)

	_ = cbcEncrypter
}

func desInterfaceUsage() {
	key := make([]byte, 8)

	var cipherBlock cipher.Block = des.NewCipher(key)

	cipherBlock2 := cipher.Block(des.NewCipher(key))

	_, _ = cipherBlock, cipherBlock2
}

func desFunctionParameter() {
	key := make([]byte, 8)

	useCipher(des.NewCipher(key))
}

func useCipher(c cipher.Block) {
	_ = c
}

func desOFBPattern() {
	key := make([]byte, 8)
	iv := make([]byte, des.BlockSize)

	stream := cipher.NewOFB(des.NewCipher(key), iv)
	dst := make([]byte, 16)
	src := make([]byte, 16)
	stream.XORKeyStream(dst, src)

	_ = dst
}

func tripleDESKeyPattern() {
	key8 := make([]byte, 8)
	key24 := append(key8, key8...)
	key24 = append(key24, key8...)

	cipher, _ := des.NewTripleDESCipher(key24)

	_ = cipher
}
