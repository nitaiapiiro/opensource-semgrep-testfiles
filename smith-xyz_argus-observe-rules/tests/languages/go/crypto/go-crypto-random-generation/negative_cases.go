package gocryptorandomgeneration

import (
	"fmt"
)

func secureRandom() {
	key := make([]byte, 32)
	_ = key
}

func nonCryptoFunction() {
	message := "This function doesn't use random generation"
	fmt.Println(message)
}

func confusingNames() {
	randLooking := "not actually rand"
	randVar := 12345

	_, _ = randLooking, randVar
}
