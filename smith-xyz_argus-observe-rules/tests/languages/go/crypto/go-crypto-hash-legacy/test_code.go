package gocryptohashlegacy

import (
	"golang.org/x/crypto/md4"
	"golang.org/x/crypto/ripemd160"
)

func md4Usage() {
	data := []byte("data")

	hasher1 := md4.New()

	hash1 := md4.Sum(data)

	hasher2 := md4.New()
	hasher2.Write(data)
	hash2 := hasher2.Sum(nil)

	_, _, _, _ = hasher1, hash1, hash2, hasher2
}

func ripemd160Usage() {
	data := []byte("data")

	hasher1 := ripemd160.New()

	hasher2 := ripemd160.New()
	hasher2.Write(data)
	hash := hasher2.Sum(nil)

	_, _, _ = hasher1, hasher2, hash
}

func md4Pattern() {
	data := []byte("data")

	hasher := md4.New()
	hasher.Write(data)
	hash := hasher.Sum(nil)

	_ = hash
}
