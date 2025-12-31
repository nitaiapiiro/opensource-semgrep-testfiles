package gocryptosaltgeneration

import (
	"crypto/rand"
	"crypto/sha256"
	"io"

	"golang.org/x/crypto/argon2"
	"golang.org/x/crypto/bcrypt"
	"golang.org/x/crypto/pbkdf2"
	"golang.org/x/crypto/scrypt"
)

func pbkdf2WithRandomSalt() {
	password := []byte("password")
	salt := make([]byte, 16)
	rand.Read(salt)
	iter := 10000
	keyLen := 32
	key := pbkdf2.Key(password, salt, iter, keyLen, sha256.New)

	_ = key
}

func bcryptWithRandomSalt() {
	password := []byte("password")
	salt := make([]byte, 16)
	rand.Read(salt)
	cost := 10
	hash, _ := bcrypt.GenerateFromPassword(password, cost)

	_ = hash
}

func scryptWithRandomSalt() {
	password := []byte("password")
	salt := make([]byte, 16)
	rand.Read(salt)
	n := 32768
	r := 8
	p := 1
	keyLen := 32
	key, _ := scrypt.Key(password, salt, n, r, p, keyLen)

	_ = key
}

func argon2IDWithRandomSalt() {
	password := []byte("password")
	salt := make([]byte, 16)
	rand.Read(salt)
	time := uint32(1)
	memory := uint32(64 * 1024)
	threads := uint8(4)
	keyLen := uint32(32)
	key := argon2.IDKey(password, salt, time, memory, threads, keyLen)

	_ = key
}

func argon2KeyWithRandomSalt() {
	password := []byte("password")
	salt := make([]byte, 16)
	rand.Read(salt)
	time := uint32(1)
	memory := uint32(64 * 1024)
	threads := uint8(4)
	keyLen := uint32(32)
	key := argon2.Key(password, salt, time, memory, threads, keyLen)

	_ = key
}

func pbkdf2WithStaticSalt() {
	password := []byte("password")
	salt := []byte("static-salt-value")
	iter := 10000
	keyLen := 32
	key := pbkdf2.Key(password, salt, iter, keyLen, sha256.New)

	_ = key
}

func pbkdf2WithConstSalt() {
	password := []byte("password")
	const salt = "static-salt-value"
	iter := 10000
	keyLen := 32
	key := pbkdf2.Key(password, []byte(salt), iter, keyLen, sha256.New)

	_ = key
}

func pbkdf2WithVarSalt() {
	password := []byte("password")
	var salt = []byte("static-salt-value")
	iter := 10000
	keyLen := 32
	key := pbkdf2.Key(password, salt, iter, keyLen, sha256.New)

	_ = key
}

func pbkdf2WithIoReadFull() {
	password := []byte("password")
	salt := make([]byte, 16)
	io.ReadFull(rand.Reader, salt)
	iter := 10000
	keyLen := 32
	key := pbkdf2.Key(password, salt, iter, keyLen, sha256.New)

	_ = key
}
