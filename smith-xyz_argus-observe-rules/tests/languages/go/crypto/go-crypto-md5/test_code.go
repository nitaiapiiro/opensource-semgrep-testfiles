package goweakcryptomd5

import (
	"crypto"
	"crypto/hmac"
	"crypto/md5"
	"encoding/base64"
	"encoding/hex"
	"fmt"
	"hash"
	"io"
	"strings"

	"golang.org/x/crypto/pbkdf2"
)

func basicMD5Usage() {
	// Basic patterns that should trigger
	data := []byte("test data")

	// Pattern: md5.New() - Line 22
	hasher1 := md5.New()

	// Pattern: md5.Sum($DATA) - Line 25
	hash1 := md5.Sum(data)

	// Pattern: $HASHER := md5.New(); $HASHER.Write($DATA); $HASH := $HASHER.Sum($SUFFIX) - Line 28
	hasher2 := md5.New()
	hasher2.Write(data)
	hash2 := hasher2.Sum(nil)

	// Pattern: $HASH := md5.Sum($DATA) - Line 33
	checksum := md5.Sum(data)

	// Use variables to avoid unused variable errors
	_, _, _, _ = hasher1, hash1, hash2, checksum
}

func cryptoPackageUsage() {
	// Pattern: crypto.MD5.New() - Line 41
	hasher1 := crypto.MD5.New()

	// Pattern: $HASH := crypto.MD5; $HASHER := $HASH.New() - Line 44
	hashType := crypto.MD5
	hasher2 := hashType.New()

	// Use variables
	_, _ = hasher1, hasher2
}

func interfaceUsage() {
	// Pattern: var $HASHER hash.Hash = md5.New() - Line 53
	var hasher1 hash.Hash = md5.New()

	// Pattern: $HASHER := hash.Hash(md5.New()) - Line 56
	hasher2 := hash.Hash(md5.New())

	// Use variables
	_, _ = hasher1, hasher2
}

func encodingPatterns() {
	data := []byte("test data")

	// Pattern: hex.EncodeToString(md5.Sum($DATA)) - Line 66
	hexString := hex.EncodeToString(md5.Sum(data))

	// Pattern: base64.StdEncoding.EncodeToString(md5.Sum($DATA)) - Line 69
	base64String := base64.StdEncoding.EncodeToString(md5.Sum(data))

	// Pattern: fmt.Sprintf("%x", md5.Sum($DATA)) - Line 72
	formatted1 := fmt.Sprintf("%x", md5.Sum(data))

	// Pattern: fmt.Sprintf("%X", md5.Sum($DATA)) - Line 75
	formatted2 := fmt.Sprintf("%X", md5.Sum(data))

	// Use variables
	_, _, _, _ = hexString, base64String, formatted1, formatted2
}

func ioPatterns() {
	data := "test string"
	reader := strings.NewReader("test data")

	// Pattern: io.WriteString(md5.New(), $DATA) - Line 86
	io.WriteString(md5.New(), data)

	// Pattern: $HASHER := md5.New(); io.Copy($HASHER, $READER) - Line 89
	hasher := md5.New()
	io.Copy(hasher, reader)
}

func hmacPatterns() {
	key := []byte("secret key")

	// Pattern: hmac.New(md5.New, $KEY) - Line 97
	hmac1 := hmac.New(md5.New, key)

	// Pattern: hmac.New(crypto.MD5.New, $KEY) - Line 100
	hmac2 := hmac.New(crypto.MD5.New, key)

	// Use variables
	_, _ = hmac1, hmac2
}

func pbkdf2Patterns() {
	password := []byte("password")
	salt := []byte("salt")

	// Pattern: pbkdf2.Key($PASSWORD, $SALT, $ITER, $KEYLEN, md5.New) - Line 111
	key1 := pbkdf2.Key(password, salt, 1000, 32, md5.New)

	// Pattern: pbkdf2.Key($PASSWORD, $SALT, $ITER, $KEYLEN, crypto.MD5.New) - Line 114
	key2 := pbkdf2.Key(password, salt, 1000, 32, crypto.MD5.New)

	// Use variables
	_, _ = key1, key2
}

func complexPatterns() {
	data := []byte("test")

	// Pattern: $HASHER := md5.New(); ...; $HASHER.Sum($SUFFIX) - Line 124
	hasher := md5.New()
	hasher.Write(data)
	// Some other code could go here
	result := hasher.Sum(nil)

	// Use variable
	_ = result
}
