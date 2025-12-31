package gocryptoaes

import (
	"crypto/cipher"
	"crypto/des"
	"fmt"
)

func nonAESCiphers() {
	key := make([]byte, 8)
	desCipher, _ := des.NewCipher(key)

	_ = desCipher
}

func nonCryptoFunction() {
	message := "This function doesn't use any encryption"
	fmt.Println(message)
}

func confusingNames() {
	aesLooking := "not actually aes"
	aesVar := 12345

	_, _ = aesLooking, aesVar
}

func otherCipherModes() {
	key := make([]byte, 8)
	block, _ := des.NewCipher(key)
	iv := make([]byte, 8)

	ofb := cipher.NewOFB(block, iv)

	_ = ofb
}
