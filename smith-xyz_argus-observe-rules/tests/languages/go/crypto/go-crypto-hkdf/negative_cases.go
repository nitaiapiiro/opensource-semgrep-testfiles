package gocryptohkdf

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
	hkdfLooking := "not actually hkdf"
	hkdfVar := 12345

	_, _ = hkdfLooking, hkdfVar
}
