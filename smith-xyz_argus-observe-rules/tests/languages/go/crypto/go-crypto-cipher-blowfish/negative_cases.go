package gocryptocipherblowfish

import (
	"crypto/aes"
	"crypto/cipher"
	"fmt"
)

func secureCiphers() {
	key := make([]byte, 32)
	cipher, _ := aes.NewCipher(key)

	_ = cipher
}

func nonCryptoFunction() {
	message := "This function doesn't use Blowfish"
	fmt.Println(message)
}

func confusingNames() {
	blowfishLooking := "not actually blowfish"
	blowfishVar := 12345

	_, _ = blowfishLooking, blowfishVar
}
