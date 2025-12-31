package gocryptodigitalsignatures

import (
	"crypto/sha256"
	"fmt"
)

func nonSignatureOperations() {
	data := []byte("data")
	hash := sha256.Sum256(data)

	_ = hash
}

func nonCryptoFunction() {
	message := "This function doesn't use digital signatures"
	fmt.Println(message)
}

func confusingNames() {
	signLooking := "not actually sign"
	signVar := 12345

	_, _ = signLooking, signVar
}
