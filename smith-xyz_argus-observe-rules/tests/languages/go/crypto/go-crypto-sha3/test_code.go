package gocryptosha3

import (
	"crypto"
	"crypto/hmac"
	"crypto/sha3"
	"encoding/hex"
	"fmt"
	"hash"

	"golang.org/x/crypto/pbkdf2"
)

func basicSHA3Usage() {
	data := []byte("test data")

	hasher1 := sha3.New224()

	hasher2 := sha3.New256()

	hasher3 := sha3.New384()

	hasher4 := sha3.New512()

	hasher5 := sha3.New224()
	hasher5.Write(data)
	hash1 := hasher5.Sum(nil)

	hasher6 := sha3.New256()
	hasher6.Write(data)
	hash2 := hasher6.Sum(nil)

	hasher7 := sha3.New384()
	hasher7.Write(data)
	hash3 := hasher7.Sum(nil)

	hasher8 := sha3.New512()
	hasher8.Write(data)
	hash4 := hasher8.Sum(nil)

	_, _, _, _, _, _, _, _ = hasher1, hasher2, hasher3, hasher4, hash1, hash2, hash3, hash4
}

func cryptoPackageUsage() {
	hasher1 := crypto.SHA3_224.New()

	hasher2 := crypto.SHA3_256.New()

	hasher3 := crypto.SHA3_384.New()

	hasher4 := crypto.SHA3_512.New()

	_, _, _, _ = hasher1, hasher2, hasher3, hasher4
}

func interfaceUsage() {
	var hasher1 hash.Hash = sha3.New256()

	hasher2 := hash.Hash(sha3.New512())

	_, _ = hasher1, hasher2
}

func encodingPatterns() {
	hasher := sha3.New256()
	hasher.Write([]byte("test"))
	hexString := hex.EncodeToString(hasher.Sum(nil))

	_ = hexString
}

func hmacPatterns() {
	key := []byte("secret key")

	hmac1 := hmac.New(sha3.New256, key)

	hmac2 := hmac.New(crypto.SHA3_256.New, key)

	_, _ = hmac1, hmac2
}

func pbkdf2Patterns() {
	password := []byte("password")
	salt := []byte("salt")

	key1 := pbkdf2.Key(password, salt, 1000, 32, sha3.New256)

	key2 := pbkdf2.Key(password, salt, 1000, 32, crypto.SHA3_256.New)

	_, _ = key1, key2
}
