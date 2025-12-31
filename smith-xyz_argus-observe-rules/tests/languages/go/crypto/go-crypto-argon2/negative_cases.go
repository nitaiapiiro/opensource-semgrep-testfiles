package gocryptoargon2

import (
	"crypto/sha256"
	"fmt"

	"golang.org/x/crypto/pbkdf2"
)

func otherKeyDerivation() {
	password := []byte("password")
	salt := []byte("salt")

	key := pbkdf2.Key(password, salt, 100000, 32, sha256.New)

	_ = key
}

func nonCryptoFunction() {
	message := "This function doesn't use key derivation"
	fmt.Println(message)
}

func confusingNames() {
	argon2Looking := "not actually argon2"
	argon2Var := 12345

	_, _ = argon2Looking, argon2Var
}
