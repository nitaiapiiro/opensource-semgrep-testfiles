package gocryptohmac

import (
	"fmt"
)

func nonCryptoFunction() {
	message := "This function doesn't use HMAC"
	fmt.Println(message)
}

func confusingNames() {
	hmacLooking := "not actually hmac"
	hmacVar := 12345

	_, _ = hmacLooking, hmacVar
}
