package gocryptosha256

import (
	"crypto/sha512"
	"crypto/sha3"
	"encoding/hex"
	"fmt"
	"hash"

	"golang.org/x/crypto/blake2b"
)

func goodHashingPractices() {
	data := []byte("test data")

	hasher1 := sha512.New()
	hash1 := sha512.Sum512(data)

	hasher2 := sha3.New256()
	hash2 := sha3.Sum256(data)

	hasher3, _ := blake2b.New256(nil)

	safeHex := hex.EncodeToString(hash1[:])
	safeFormatted := fmt.Sprintf("%x", hash2)

	_, _, _, _, _, _ = hasher1, hasher2, hasher3, hash1, safeHex, safeFormatted
}

func interfaceUsageGood() {
	var hasher hash.Hash = sha512.New()
	goodHash := hash.Hash(sha3.New256())

	_, _ = hasher, goodHash
}

func nonCryptoFunction() {
	message := "This function doesn't use any hashing"
	fmt.Println(message)
}

func confusingNames() {
	sha256Looking := "not actually sha256"
	sha256Var := 12345

	_, _ = sha256Looking, sha256Var
}
