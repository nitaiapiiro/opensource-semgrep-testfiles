package gocryptokeylength

import (
	"crypto/aes"
	"crypto/rand"
	"fmt"
)

func secureKeyLength() {
	key := make([]byte, 32)
	rand.Read(key)
	cipher, _ := aes.NewCipher(key)

	_ = cipher
}

func nonCryptoFunction() {
	message := "This function doesn't use encryption"
	fmt.Println(message)
}

func confusingNames() {
	keyLooking := "not actually a key"
	keyVar := 12345

	_, _ = keyLooking, keyVar
}
