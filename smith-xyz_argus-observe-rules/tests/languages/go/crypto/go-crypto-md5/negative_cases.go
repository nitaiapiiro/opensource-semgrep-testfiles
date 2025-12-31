package goweakcryptomd5

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

	// Good: Using SHA256 instead of MD5
	hasher1 := sha256.New()
	hash1 := sha256.Sum256(data)

	// Good: Using SHA512
	hasher2 := sha512.New()
	hash2 := sha512.Sum512(data)

	// Good: Using Blake2b
	hasher3, _ := blake2b.New256(nil)

	// Good: Encoding safe hashes
	safeHex := hex.EncodeToString(hash1[:])
	safeFormatted := fmt.Sprintf("%x", hash2)

	// Use variables
	_, _, _, _, _, _ = hasher1, hasher2, hasher3, hash1, safeHex, safeFormatted
}

func interfaceUsageGood() {
	// Good: Interface usage with safe hash
	var hasher hash.Hash = sha256.New()
	goodHash := hash.Hash(sha256.New())

	// Use variables
	_, _ = hasher, goodHash
}

// Good: Functions that don't use crypto at all
func nonCryptoFunction() {
	message := "This function doesn't use any hashing"
	fmt.Println(message)
}

// Good: Variable names that might contain 'md5' but don't use MD5
func confusingNames() {
	md5Looking := "not actually md5"
	md5Var := 12345

	// Use variables
	_, _ = md5Looking, md5Var
}
