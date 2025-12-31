package gocryptoscrypt

import (
	"golang.org/x/crypto/scrypt"
)

func scryptUsage() {
	password := []byte("password")
	salt := []byte("salt")
	n := 32768
	r := 8
	p := 1
	keyLen := 32

	key1, _ := scrypt.Key(password, salt, n, r, p, keyLen)

	_ = key1
}

func scryptPattern() {
	password := []byte("password")
	salt := []byte("salt")
	n := 32768
	r := 8
	p := 1
	keyLen := 32

	key, _ := scrypt.Key(password, salt, n, r, p, keyLen)
	_ = key
}

func scryptNValidation() {
	password := []byte("password")
	salt := []byte("salt")
	r := 8
	p := 1
	keyLen := 32

	key1, _ := scrypt.Key(password, salt, 32768, r, p, keyLen)

	key2, _ := scrypt.Key(password, salt, 65536, r, p, keyLen)

	key3, _ := scrypt.Key(password, salt, 131072, r, p, keyLen)

	_, _, _ = key1, key2, key3
}
