package gocryptodynamiccipher

import (
	"crypto/cipher"
	"crypto/des"
	"crypto/rc4"
)

func switchCipher() {
	algo := "des"
	key := make([]byte, 8)
	var cipherBlock cipher.Block

	switch algo {
	case "des":
		cipherBlock, _ = des.NewCipher(key)
	}

	_ = cipherBlock
}

func ifRc4Cipher() {
	algo := "rc4"
	key := []byte("key")
	var stream cipher.Stream

	if algo == "rc4" {
		stream, _ = rc4.NewCipher(key)
	}

	_ = stream
}

func ifDesCipher() {
	algo := "des"
	key := make([]byte, 8)
	var cipherBlock cipher.Block

	if algo == "des" {
		cipherBlock, _ = des.NewCipher(key)
	}

	_ = cipherBlock
}

func cipherMapBlock() {
	ciphers := map[string]func([]byte) (cipher.Block, error){
		"des": des.NewCipher,
	}

	_ = ciphers
}

func cipherMapStream() {
	ciphers := map[string]func([]byte) (cipher.Stream, error){
		"rc4": rc4.NewCipher,
	}

	_ = ciphers
}

func getCipherFunction() {
	key := make([]byte, 8)
	cipherBlock := getCipher("des", key)

	_ = cipherBlock
}

func getCipher(cipherType string, key []byte) cipher.Block {
	if cipherType == "des" {
		cipherBlock, _ := des.NewCipher(key)
		return cipherBlock
	}
	return nil
}

func getCipherByName() {
	config := struct {
		Algorithm string
	}{Algorithm: "des"}
	key := make([]byte, 8)

	cipherBlock := getCipherByName(config.Algorithm, key)

	_ = cipherBlock
}

func getCipherByName(algorithm string, key []byte) cipher.Block {
	return getCipher(algorithm, key)
}
