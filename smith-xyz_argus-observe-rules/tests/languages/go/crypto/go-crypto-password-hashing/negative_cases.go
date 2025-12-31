package gocryptopasswordhashing

import (
	"crypto/sha256"
	"fmt"
)

func nonPasswordHashing() {
	data := []byte("data")
	hash := sha256.Sum256(data)

	_ = hash
}

func nonCryptoFunction() {
	message := "This function doesn't use password hashing"
	fmt.Println(message)
}

func confusingNames() {
	passwordLooking := "not actually a password"
	passwordVar := 12345

	_, _ = passwordLooking, passwordVar
}
