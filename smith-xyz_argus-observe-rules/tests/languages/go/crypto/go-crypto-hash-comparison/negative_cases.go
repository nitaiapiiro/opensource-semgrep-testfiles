package gocryptohashcomparison

import (
	"crypto/sha256"
	"fmt"
)

func secureHashComparison() {
	data1 := []byte("data1")
	data2 := []byte("data2")

	hash1 := sha256.Sum256(data1)
	hash2 := sha256.Sum256(data2)

	_ = hash1
	_ = hash2
}

func nonCryptoFunction() {
	message := "This function doesn't use hash comparison"
	fmt.Println(message)
}

func confusingNames() {
	hashLooking := "not actually a hash"
	hashVar := 12345

	_, _ = hashLooking, hashVar
}
