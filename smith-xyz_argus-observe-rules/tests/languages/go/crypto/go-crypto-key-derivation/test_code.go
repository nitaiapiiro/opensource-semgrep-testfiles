package gocryptokeyderivation

import (
	"crypto/md5"
	"crypto/sha1"

	"golang.org/x/crypto/pbkdf2"
)

func passwordToKey() {
	password := "password"

	key := []byte(password)

	_ = key
}

func md5PasswordToKey() {
	password := "password"

	key := md5.Sum([]byte(password))

	_ = key
}

func sha1PasswordToKey() {
	password := "password"

	key := sha1.Sum([]byte(password))

	_ = key
}

func md5HasherPattern() {
	password := "password"

	hasher := md5.New()
	hasher.Write([]byte(password))
	key := hasher.Sum(nil)

	_ = key
}

func sha1HasherPattern() {
	password := "password"

	hasher := sha1.New()
	hasher.Write([]byte(password))
	key := hasher.Sum(nil)

	_ = key
}

func pbkdf2NoSalt() {
	password := []byte("password")
	iter := 1000
	keyLen := 32

	key := pbkdf2.Key(password, nil, iter, keyLen, sha1.New)

	_ = key
}

func pbkdf2StaticSalt() {
	password := []byte("password")
	iter := 1000
	keyLen := 32

	key := pbkdf2.Key(password, []byte("static_salt"), iter, keyLen, sha1.New)

	_ = key
}
