package gocryptoargon2

import (
	"golang.org/x/crypto/argon2"
)

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

func argon2Pattern() {
	password := []byte("password")
	salt := []byte("salt")
	time := uint32(3)
	memory := uint32(65536)
	threads := uint8(4)
	keyLen := uint32(32)

	key := argon2.Key(password, salt, time, memory, threads, keyLen)
	_ = key
}

func argon2TimeValidation() {
	password := []byte("password")
	salt := []byte("salt")
	memory := uint32(65536)
	threads := uint8(4)
	keyLen := uint32(32)

	key1 := argon2.Key(password, salt, 3, memory, threads, keyLen)

	key2 := argon2.Key(password, salt, 5, memory, threads, keyLen)

	key3 := argon2.Key(password, salt, 10, memory, threads, keyLen)

	_, _, _ = key1, key2, key3
}
