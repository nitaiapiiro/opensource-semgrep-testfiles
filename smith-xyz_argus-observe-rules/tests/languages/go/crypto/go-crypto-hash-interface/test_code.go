package gocryptohashinterface

import (
	"crypto/md5"
	"crypto/sha1"
	"hash"
)

func hashInterfaceParameter() {
	useHasher(md5.New())
}

func useHasher(h hash.Hash) {
	h.Write([]byte("data"))
	_ = h.Sum(nil)
}

func sha1InterfaceParameter() {
	useHasher(sha1.New())
}

func hashInterfaceReturn() {
	hasher := getMD5Hasher()
	hasher.Write([]byte("data"))
	_ = hasher.Sum(nil)
}

func getMD5Hasher() hash.Hash {
	return md5.New()
}

func getSHA1Hasher() hash.Hash {
	return sha1.New()
}

func hashMap() {
	hashers := map[string]hash.Hash{
		"md5":  md5.New(),
		"sha1": sha1.New(),
	}

	_ = hashers
}

func hashSlice() {
	hashers := []hash.Hash{
		md5.New(),
		sha1.New(),
	}

	_ = hashers
}
