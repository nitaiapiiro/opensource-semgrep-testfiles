package gocryptoblake2

import (
	"crypto/sha256"
	"crypto/sha512"
	"fmt"
)

func otherHashFunctions() {
	data := []byte("data")

	hash1 := sha256.Sum256(data)

	hash2 := sha512.Sum512(data)

	_, _ = hash1, hash2
}

func nonCryptoFunction() {
	message := "This function doesn't use any hashing"
	fmt.Println(message)
}

func confusingNames() {
	blake2Looking := "not actually blake2"
	blake2Var := 12345

	_, _ = blake2Looking, blake2Var
}
