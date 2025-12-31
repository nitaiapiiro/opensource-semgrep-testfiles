package gocryptosha512

import (
	"crypto/sha256"
	"crypto/sha3"
	"encoding/hex"
	"fmt"
	"hash"

	"golang.org/x/crypto/blake2b"
)

func goodHashingPractices() {
	data := []byte("test data")

	hasher1 := sha256.New()
	hash1 := sha256.Sum256(data)

	hasher2 := sha3.New512()
	hash2 := sha3.Sum512(data)

	hasher3, _ := blake2b.New512(nil)

	safeHex := hex.EncodeToString(hash1[:])
	safeFormatted := fmt.Sprintf("%x", hash2)

	_, _, _, _, _, _ = hasher1, hasher2, hasher3, hash1, safeHex, safeFormatted
}

func interfaceUsageGood() {
	var hasher hash.Hash = sha256.New()
	goodHash := hash.Hash(sha3.New512())

	_, _ = hasher, goodHash
}

func nonCryptoFunction() {
	message := "This function doesn't use any hashing"
	fmt.Println(message)
}

func confusingNames() {
	sha512Looking := "not actually sha512"
	sha512Var := 12345

	_, _ = sha512Looking, sha512Var
}
