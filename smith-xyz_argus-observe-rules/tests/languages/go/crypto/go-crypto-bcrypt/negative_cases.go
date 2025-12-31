package gocryptobcrypt

import (
	"crypto/sha256"
	"fmt"

	"golang.org/x/crypto/pbkdf2"
)

func otherPasswordHashing() {
	password := []byte("password")
	salt := []byte("salt")

	hash := pbkdf2.Key(password, salt, 100000, 32, sha256.New)

	_ = hash
}

func nonCryptoFunction() {
	message := "This function doesn't use password hashing"
	fmt.Println(message)
}

func confusingNames() {
	bcryptLooking := "not actually bcrypt"
	bcryptVar := 12345

	_, _ = bcryptLooking, bcryptVar
}
