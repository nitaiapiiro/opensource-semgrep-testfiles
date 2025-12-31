package gocryptohashlegacy

import (
	"crypto/sha256"
	"crypto/sha512"
	"fmt"
)

func secureHashFunctions() {
	data := []byte("data")

	hash1 := sha256.Sum256(data)

	hash2 := sha512.Sum512(data)

	_, _ = hash1, hash2
}

func nonCryptoFunction() {
	message := "This function doesn't use legacy hashes"
	fmt.Println(message)
}

func confusingNames() {
	md4Looking := "not actually md4"
	ripemd160Looking := "not actually ripemd160"

	_, _ = md4Looking, ripemd160Looking
}
