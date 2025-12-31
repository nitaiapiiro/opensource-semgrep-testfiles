package gocryptosaltgeneration

import (
	"fmt"
)

func nonCryptoFunction() {
	message := "This function doesn't use salt generation"
	fmt.Println(message)
}

func confusingNames() {
	saltLooking := "not actually salt"
	saltVar := 12345

	_, _ = saltLooking, saltVar
}
