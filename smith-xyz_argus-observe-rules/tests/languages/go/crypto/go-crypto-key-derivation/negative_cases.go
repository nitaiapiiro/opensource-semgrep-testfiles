package gocryptokeyderivation

import (
	"fmt"
)

func secureKeyDerivation() {
	passwordStr := "password"
	passwordBytes := make([]byte, len(passwordStr))
	copy(passwordBytes, []byte(passwordStr))

	_ = passwordBytes
}

func nonCryptoFunction() {
	message := "This function doesn't use key derivation"
	_ = message
}

func confusingNames() {
	keyLooking := "not actually a key"
	keyVar := 12345

	_, _ = keyLooking, keyVar
}
