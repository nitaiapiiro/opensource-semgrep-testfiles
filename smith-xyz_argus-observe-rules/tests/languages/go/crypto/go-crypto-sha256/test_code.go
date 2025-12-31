package gocryptosha256

import (
	"crypto"
	"crypto/ecdsa"
	"crypto/hmac"
	"crypto/rand"
	"crypto/rsa"
	"crypto/sha256"
	"encoding/base64"
	"encoding/hex"
	"fmt"
	"hash"
	"io"
	"strings"

	"golang.org/x/crypto/pbkdf2"
)

func basicSHA256Usage() {
	data := []byte("test data")

	hasher1 := sha256.New()

	hash1 := sha256.Sum256(data)

	hasher2 := sha256.New()
	hasher2.Write(data)
	hash2 := hasher2.Sum(nil)

	checksum := sha256.Sum256(data)

	_, _, _, _ = hasher1, hash1, hash2, checksum
}

func cryptoPackageUsage() {
	hasher1 := crypto.SHA256.New()

	hashType := crypto.SHA256
	hasher2 := hashType.New()

	_, _ = hasher1, hasher2
}

func interfaceUsage() {
	var hasher1 hash.Hash = sha256.New()

	hasher2 := hash.Hash(sha256.New())

	_, _ = hasher1, hasher2
}

func encodingPatterns() {
	data := []byte("test data")

	hexString := hex.EncodeToString(sha256.Sum256(data))

	base64String := base64.StdEncoding.EncodeToString(sha256.Sum256(data))

	formatted1 := fmt.Sprintf("%x", sha256.Sum256(data))

	formatted2 := fmt.Sprintf("%X", sha256.Sum256(data))

	_, _, _, _ = hexString, base64String, formatted1, formatted2
}

func ioPatterns() {
	data := "test string"
	reader := strings.NewReader("test data")

	io.WriteString(sha256.New(), data)

	hasher := sha256.New()
	io.Copy(hasher, reader)
}

func hmacPatterns() {
	key := []byte("secret key")

	hmac1 := hmac.New(sha256.New, key)

	hmac2 := hmac.New(crypto.SHA256.New, key)

	_, _ = hmac1, hmac2
}

func pbkdf2Patterns() {
	password := []byte("password")
	salt := []byte("salt")

	key1 := pbkdf2.Key(password, salt, 1000, 32, sha256.New)

	key2 := pbkdf2.Key(password, salt, 1000, 32, crypto.SHA256.New)

	_, _ = key1, key2
}

func rsaPatterns() {
	priv, _ := rsa.GenerateKey(rand.Reader, 2048)
	pub := &priv.PublicKey
	hashed := sha256.Sum256([]byte("message"))
	sig := make([]byte, 256)

	rsa.SignPKCS1v15(rand.Reader, priv, crypto.SHA256, hashed[:])

	rsa.VerifyPKCS1v15(pub, crypto.SHA256, hashed[:], sig)
}

func ecdsaPatterns() {
	priv, _ := ecdsa.GenerateKey(nil, rand.Reader)
	data := []byte("message")

	ecdsa.Sign(rand.Reader, priv, sha256.Sum256(data))
}

func complexPatterns() {
	data := []byte("test")

	hasher := sha256.New()
	hasher.Write(data)
	result := hasher.Sum(nil)

	_ = result
}
