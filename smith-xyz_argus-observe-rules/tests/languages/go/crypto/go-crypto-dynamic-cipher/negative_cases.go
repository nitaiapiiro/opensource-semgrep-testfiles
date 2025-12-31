package gocryptodynamiccipher

import (
	"crypto/aes"
	"crypto/cipher"
	"fmt"
)

func staticCipher() {
	key := make([]byte, 32)
	cipher, _ := aes.NewCipher(key)

	_ = cipher
}

func nonCryptoFunction() {
	message := "This function doesn't use dynamic cipher selection"
	fmt.Println(message)
}

func confusingNames() {
	dynamicLooking := "not actually dynamic"
	dynamicVar := 12345

	_, _ = dynamicLooking, dynamicVar
}
