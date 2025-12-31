package gocryptohkdf

import (
	"crypto/sha256"
	"io"

	"golang.org/x/crypto/hkdf"
)

func hkdfNew() {
	secret := []byte("secret")
	salt := []byte("salt")
	info := []byte("info")

	hkdf1 := hkdf.New(sha256.New, secret, salt, info)

	key := make([]byte, 32)
	hkdf1.Read(key)

	_ = key
}

func hkdfExtract() {
	secret := []byte("secret")
	salt := []byte("salt")

	prk := hkdf.Extract(sha256.New, secret, salt)

	_ = prk
}

func hkdfExpand() {
	prk := []byte("pseudorandom key")
	info := []byte("info")

	hkdf := hkdf.Expand(sha256.New, prk, info)

	key := make([]byte, 32)
	hkdf.Read(key)

	_ = key
}

func hkdfPattern() {
	secret := []byte("secret")
	salt := []byte("salt")
	info := []byte("info")

	hkdf := hkdf.New(sha256.New, secret, salt, info)
	key := make([]byte, 32)
	hkdf.Read(key)

	_ = key
}

func hkdfExtractExpandPattern() {
	secret := []byte("secret")
	salt := []byte("salt")
	info := []byte("info")

	prk := hkdf.Extract(sha256.New, secret, salt)
	hkdf := hkdf.Expand(sha256.New, prk, info)

	key := make([]byte, 32)
	io.ReadFull(hkdf, key)

	_ = key
}
