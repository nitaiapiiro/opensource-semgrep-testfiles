package gocryptoaes

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
)

func basicAESUsage() {
	key := make([]byte, 32)
	rand.Read(key)

	cipher1 := aes.NewCipher(key)

	cipher2, _ := aes.NewCipher(key)

	var cipher3 cipher.Block = aes.NewCipher(key)

	cipher4 := cipher.Block(aes.NewCipher(key))

	_, _, _, _ = cipher1, cipher2, cipher3, cipher4
}

func cipherModes() {
	key := make([]byte, 32)
	rand.Read(key)
	iv := make([]byte, aes.BlockSize)
	rand.Read(iv)

	block := aes.NewCipher(key)

	cbcEncrypter := cipher.NewCBCEncrypter(block, iv)

	cbcDecrypter := cipher.NewCBCDecrypter(aes.NewCipher(key), iv)

	gcm, _ := cipher.NewGCM(block)

	gcmDirect := cipher.NewGCM(aes.NewCipher(key))

	cfbEncrypter := cipher.NewCFBEncrypter(aes.NewCipher(key), iv)

	cfbDecrypter := cipher.NewCFBDecrypter(aes.NewCipher(key), iv)

	ctr := cipher.NewCTR(aes.NewCipher(key), iv)

	_, _, _, _, _, _, _, _ = cbcEncrypter, cbcDecrypter, gcm, gcmDirect, cfbEncrypter, cfbDecrypter, ctr, block
}

func blockPatterns() {
	key := make([]byte, 32)
	rand.Read(key)
	iv := make([]byte, aes.BlockSize)
	rand.Read(iv)

	block := aes.NewCipher(key)
	cbcEncrypter := cipher.NewCBCEncrypter(block, iv)

	block2 := aes.NewCipher(key)
	gcm, _ := cipher.NewGCM(block2)

	_, _ = cbcEncrypter, gcm
}

func keySizeValidation() {
	key16 := make([]byte, 16)
	rand.Read(key16)
	cipher16 := aes.NewCipher(key16)

	key24 := make([]byte, 24)
	rand.Read(key24)
	cipher24 := aes.NewCipher(key24)

	key32 := make([]byte, 32)
	rand.Read(key32)
	cipher32 := aes.NewCipher(key32)

	_, _, _ = cipher16, cipher24, cipher32
}
