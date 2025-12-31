package gocryptosha512

import (
	"crypto"
	"crypto/ecdsa"
	"crypto/hmac"
	"crypto/rand"
	"crypto/rsa"
	"crypto/sha512"
	"encoding/base64"
	"encoding/hex"
	"fmt"
	"hash"
	"io"
	"strings"

	"golang.org/x/crypto/pbkdf2"
)

func basicSHA512Usage() {
	data := []byte("test data")

	hasher1 := sha512.New()

	hash1 := sha512.Sum512(data)

	hasher2 := sha512.New()
	hasher2.Write(data)
	hash2 := hasher2.Sum(nil)

	checksum := sha512.Sum512(data)

	_, _, _, _ = hasher1, hash1, hash2, checksum
}

func cryptoPackageUsage() {
	hasher1 := crypto.SHA512.New()

	hashType := crypto.SHA512
	hasher2 := hashType.New()

	_, _ = hasher1, hasher2
}

func interfaceUsage() {
	var hasher1 hash.Hash = sha512.New()

	hasher2 := hash.Hash(sha512.New())

	_, _ = hasher1, hasher2
}

func encodingPatterns() {
	data := []byte("test data")

	hexString := hex.EncodeToString(sha512.Sum512(data))

	base64String := base64.StdEncoding.EncodeToString(sha512.Sum512(data))

	formatted1 := fmt.Sprintf("%x", sha512.Sum512(data))

	formatted2 := fmt.Sprintf("%X", sha512.Sum512(data))

	_, _, _, _ = hexString, base64String, formatted1, formatted2
}

func ioPatterns() {
	data := "test string"
	reader := strings.NewReader("test data")

	io.WriteString(sha512.New(), data)

	hasher := sha512.New()
	io.Copy(hasher, reader)
}

func hmacPatterns() {
	key := []byte("secret key")

	hmac1 := hmac.New(sha512.New, key)

	hmac2 := hmac.New(crypto.SHA512.New, key)

	_, _ = hmac1, hmac2
}

func pbkdf2Patterns() {
	password := []byte("password")
	salt := []byte("salt")

	key1 := pbkdf2.Key(password, salt, 1000, 32, sha512.New)

	key2 := pbkdf2.Key(password, salt, 1000, 32, crypto.SHA512.New)

	_, _ = key1, key2
}

func rsaPatterns() {
	priv, _ := rsa.GenerateKey(rand.Reader, 2048)
	pub := &priv.PublicKey
	hashed := sha512.Sum512([]byte("message"))
	sig := make([]byte, 512)

	rsa.SignPKCS1v15(rand.Reader, priv, crypto.SHA512, hashed[:])

	rsa.VerifyPKCS1v15(pub, crypto.SHA512, hashed[:], sig)
}

func ecdsaPatterns() {
	priv, _ := ecdsa.GenerateKey(nil, rand.Reader)
	data := []byte("message")

	ecdsa.Sign(rand.Reader, priv, sha512.Sum512(data))
}

func complexPatterns() {
	data := []byte("test")

	hasher := sha512.New()
	hasher.Write(data)
	result := hasher.Sum(nil)

	_ = result
}
