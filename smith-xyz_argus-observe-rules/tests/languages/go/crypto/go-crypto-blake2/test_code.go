package gocryptoblake2

import (
	"crypto/hmac"
	"encoding/hex"
	"hash"

	"golang.org/x/crypto/blake2b"
	"golang.org/x/crypto/blake2s"
)

func blake2bUsage() {
	key := []byte("key")

	hasher1, _ := blake2b.New256(key)

	hasher2, _ := blake2b.New512(key)

	hasher3, _ := blake2b.New256(key)
	hasher3.Write([]byte("data"))
	hash1 := hasher3.Sum(nil)

	hasher4, _ := blake2b.New512(key)
	hasher4.Write([]byte("data"))
	hash2 := hasher4.Sum(nil)

	_, _, _, _ = hasher1, hasher2, hash1, hash2
}

func blake2sUsage() {
	key := []byte("key")

	hasher1, _ := blake2s.New256(key)

	hasher2, _ := blake2s.New256(key)
	hasher2.Write([]byte("data"))
	hash := hasher2.Sum(nil)

	_, _ = hasher1, hash
}

func interfaceUsage() {
	key := []byte("key")
	hasher, _ := blake2b.New256(key)

	var hashHasher hash.Hash = hasher

	_ = hashHasher
}

func encodingPatterns() {
	key := []byte("key")
	hasher, _ := blake2b.New256(key)
	hasher.Write([]byte("data"))
	hexString := hex.EncodeToString(hasher.Sum(nil))

	_ = hexString
}

func hmacPatterns() {
	key := []byte("key")
	hasher, _ := blake2b.New256(nil)

	hmacHasher := hmac.New(hasher, key)

	_ = hmacHasher
}
