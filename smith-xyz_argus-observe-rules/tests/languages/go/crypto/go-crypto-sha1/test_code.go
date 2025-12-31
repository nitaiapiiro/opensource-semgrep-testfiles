package gocryptosha1

import (
	"crypto"
	"crypto/ecdsa"
	"crypto/hmac"
	"crypto/rand"
	"crypto/rsa"
	"crypto/sha1"
	"encoding/base64"
	"encoding/hex"
	"fmt"
	"hash"
	"io"
	"strings"

	"golang.org/x/crypto/pbkdf2"
)

func basicSHA1Usage() {
	data := []byte("test data")

	hasher1 := sha1.New()

	hash1 := sha1.Sum(data)

	hasher2 := sha1.New()
	hasher2.Write(data)
	hash2 := hasher2.Sum(nil)

	checksum := sha1.Sum(data)

	_, _, _, _ = hasher1, hash1, hash2, checksum
}

func cryptoPackageUsage() {
	hasher1 := crypto.SHA1.New()

	hashType := crypto.SHA1
	hasher2 := hashType.New()

	_, _ = hasher1, hasher2
}

func interfaceUsage() {
	var hasher1 hash.Hash = sha1.New()

	hasher2 := hash.Hash(sha1.New())

	_, _ = hasher1, hasher2
}

func encodingPatterns() {
	data := []byte("test data")

	hexString := hex.EncodeToString(sha1.Sum(data))

	base64String := base64.StdEncoding.EncodeToString(sha1.Sum(data))

	formatted1 := fmt.Sprintf("%x", sha1.Sum(data))

	formatted2 := fmt.Sprintf("%X", sha1.Sum(data))

	_, _, _, _ = hexString, base64String, formatted1, formatted2
}

func ioPatterns() {
	data := "test string"
	reader := strings.NewReader("test data")

	io.WriteString(sha1.New(), data)

	hasher := sha1.New()
	io.Copy(hasher, reader)
}

func hmacPatterns() {
	key := []byte("secret key")

	hmac1 := hmac.New(sha1.New, key)

	hmac2 := hmac.New(crypto.SHA1.New, key)

	_, _ = hmac1, hmac2
}

func pbkdf2Patterns() {
	password := []byte("password")
	salt := []byte("salt")

	key1 := pbkdf2.Key(password, salt, 1000, 32, sha1.New)

	key2 := pbkdf2.Key(password, salt, 1000, 32, crypto.SHA1.New)

	_, _ = key1, key2
}

func rsaPatterns() {
	priv, _ := rsa.GenerateKey(rand.Reader, 2048)
	pub := &priv.PublicKey
	hashed := sha1.Sum([]byte("message"))
	sig := make([]byte, 256)

	rsa.SignPKCS1v15(rand.Reader, priv, crypto.SHA1, hashed[:])

	rsa.VerifyPKCS1v15(pub, crypto.SHA1, hashed[:], sig)
}

func ecdsaPatterns() {
	priv, _ := ecdsa.GenerateKey(nil, rand.Reader)
	data := []byte("message")

	ecdsa.Sign(rand.Reader, priv, sha1.Sum(data))
}

func complexPatterns() {
	data := []byte("test")

	hasher := sha1.New()
	hasher.Write(data)
	result := hasher.Sum(nil)

	_ = result
}
