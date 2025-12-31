package gocryptociphermodes

import (
	"crypto/aes"
	"crypto/cipher"
	"fmt"
)

func secureCipherModes() {
	key := make([]byte, 32)
	block := make([]byte, 16)

	_ = key
	_ = block
}

func nonCryptoFunction() {
	message := "This function doesn't use cipher modes"
	fmt.Println(message)
}

func confusingNames() {
	modeLooking := "not actually a mode"
	modeVar := 12345

	_, _ = modeLooking, modeVar
}
