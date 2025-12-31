package gocryptosha1

import (
	"crypto/sha256"
	"crypto/sha512"
	"encoding/hex"
	"fmt"
	"hash"

	"golang.org/x/crypto/blake2b"
)

func goodHashingPractices() {
	data := []byte("test data")

	hasher1 := sha256.New()
	hash1 := sha256.Sum256(data)

	hasher2 := sha512.New()
	hash2 := sha512.Sum512(data)

	hasher3, _ := blake2b.New256(nil)

	safeHex := hex.EncodeToString(hash1[:])
	safeFormatted := fmt.Sprintf("%x", hash2)

	_, _, _, _, _, _ = hasher1, hasher2, hasher3, hash1, safeHex, safeFormatted
}

func interfaceUsageGood() {
	var hasher hash.Hash = sha256.New()
	goodHash := hash.Hash(sha512.New())

	_, _ = hasher, goodHash
}

func nonCryptoFunction() {
	message := "This function doesn't use any hashing"
	fmt.Println(message)
}

func confusingNames() {
	sha1Looking := "not actually sha1"
	sha1Var := 12345

	_, _ = sha1Looking, sha1Var
}
