package gocryptohashinterface

import (
	"crypto/sha256"
	"hash"
)

func secureHashInterface() {
	useHasher(sha256.New())
}

func useHasher(h hash.Hash) {
	h.Write([]byte("data"))
	_ = h.Sum(nil)
}

func secureHashReturn() hash.Hash {
	return sha256.New()
}

func nonCryptoFunction() {
	message := "This function doesn't use hash interface"
	_ = message
}
