package gocryptopasswordhashing

import (
	"crypto/sha256"

	"golang.org/x/crypto/argon2"
	"golang.org/x/crypto/bcrypt"
	"golang.org/x/crypto/pbkdf2"
	"golang.org/x/crypto/scrypt"
)

func bcryptUsage() {
	password := []byte("password")
	cost := 10

	hash, _ := bcrypt.GenerateFromPassword(password, cost)

	err := bcrypt.CompareHashAndPassword(hash, password)

	_ = err
}

func bcryptPattern() {
	password := []byte("password")
	cost := 10

	hash, _ := bcrypt.GenerateFromPassword(password, cost)
	err := bcrypt.CompareHashAndPassword(hash, password)

	_ = err
}

func pbkdf2Usage() {
	password := []byte("password")
	salt := []byte("salt")
	iter := 100000
	keyLen := 32

	key := pbkdf2.Key(password, salt, iter, keyLen, sha256.New)

	_ = key
}

func scryptUsage() {
	password := []byte("password")
	salt := []byte("salt")
	n := 32768
	r := 8
	p := 1
	keyLen := 32

	key, _ := scrypt.Key(password, salt, n, r, p, keyLen)

	_ = key
}

func argon2Usage() {
	password := []byte("password")
	salt := []byte("salt")
	time := uint32(3)
	memory := uint32(65536)
	threads := uint8(4)
	keyLen := uint32(32)

	key1 := argon2.Key(password, salt, time, memory, threads, keyLen)

	key2 := argon2.IDKey(password, salt, time, memory, threads, keyLen)

	_, _ = key1, key2
}
