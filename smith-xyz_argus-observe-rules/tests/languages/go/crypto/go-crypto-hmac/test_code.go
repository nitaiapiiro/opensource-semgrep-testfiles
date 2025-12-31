package gocryptohmac

import (
	"crypto"
	"crypto/hmac"
	"crypto/md5"
	"crypto/sha1"
	"crypto/sha256"
	"crypto/sha512"
	"encoding/base64"
	"encoding/hex"
)

func basicHMAC() {
	key := []byte("secret key")
	data := []byte("message")
	h1 := hmac.New(md5.New, key)
	h2 := hmac.New(sha1.New, key)
	h3 := hmac.New(sha256.New, key)
	h4 := hmac.New(sha512.New, key)
	h5 := hmac.New(sha512.New384, key)

	_, _, _, _, _ = h1, h2, h3, h4, h5
}

func cryptoPackageHMAC() {
	key := []byte("secret key")
	h1 := hmac.New(crypto.MD5.New, key)
	h2 := hmac.New(crypto.SHA1.New, key)
	h3 := hmac.New(crypto.SHA256.New, key)
	h4 := hmac.New(crypto.SHA512.New, key)
	h5 := hmac.New(crypto.SHA512_384.New, key)

	_, _, _, _, _ = h1, h2, h3, h4, h5
}

func hmacWithWrite() {
	key := []byte("secret key")
	data := []byte("message")
	h := hmac.New(sha256.New, key)
	h.Write(data)
	result := h.Sum(nil)

	_ = result
}

func hmacWithSuffix() {
	key := []byte("secret key")
	data := []byte("message")
	h := hmac.New(sha256.New, key)
	h.Write(data)
	result := h.Sum([]byte("suffix"))

	_ = result
}

func hmacWithHashVariable() {
	key := []byte("secret key")
	hashType := crypto.SHA256
	h := hmac.New(hashType.New, key)

	_ = h
}

func hmacWithHashVariableMD5() {
	key := []byte("secret key")
	hashType := crypto.MD5
	h := hmac.New(hashType.New, key)

	_ = h
}

func hmacWithHashVariableSHA512() {
	key := []byte("secret key")
	hashType := crypto.SHA512
	h := hmac.New(hashType.New, key)

	_ = h
}

func hmacHexEncoding() {
	key := []byte("secret key")
	data := []byte("message")
	h := hmac.New(sha256.New, key)
	h.Write(data)
	result := hex.EncodeToString(h.Sum(nil))

	_ = result
}

func hmacBase64Encoding() {
	key := []byte("secret key")
	data := []byte("message")
	h := hmac.New(sha256.New, key)
	h.Write(data)
	result := base64.StdEncoding.EncodeToString(h.Sum(nil))

	_ = result
}

func hmacEqual() {
	key := []byte("secret key")
	data := []byte("message")
	h := hmac.New(sha256.New, key)
	h.Write(data)
	expected := []byte("expected")
	result := hmac.Equal(h.Sum(nil), expected)

	_ = result
}
