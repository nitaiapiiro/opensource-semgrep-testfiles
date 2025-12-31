package gocryptochacha20

import (
	"crypto/rand"

	"golang.org/x/crypto/chacha20"
	"golang.org/x/crypto/chacha20poly1305"
)

func chacha20Unauthenticated() {
	key := make([]byte, 32)
	rand.Read(key)
	nonce := make([]byte, chacha20.NonceSize)
	rand.Read(nonce)

	cipher, _ := chacha20.NewUnauthenticatedCipher(key, nonce)

	dst := make([]byte, 16)
	src := make([]byte, 16)
	cipher.XORKeyStream(dst, src)

	_ = dst
}

func chacha20Pattern() {
	key := make([]byte, 32)
	rand.Read(key)
	nonce := make([]byte, chacha20.NonceSize)
	rand.Read(nonce)

	cipher, _ := chacha20.NewUnauthenticatedCipher(key, nonce)
	dst := make([]byte, 16)
	src := make([]byte, 16)
	cipher.XORKeyStream(dst, src)

	_ = dst
}

func chacha20poly1305Seal() {
	key := make([]byte, 32)
	rand.Read(key)

	aead, _ := chacha20poly1305.New(key)

	nonce := make([]byte, 12)
	rand.Read(nonce)
	plaintext := []byte("message")
	dst := make([]byte, len(plaintext)+16)
	additionalData := []byte("aad")

	ciphertext := aead.Seal(dst, nonce, plaintext, additionalData)

	_ = ciphertext
}

func chacha20poly1305Open() {
	key := make([]byte, 32)
	rand.Read(key)

	aead, _ := chacha20poly1305.New(key)

	nonce := make([]byte, 12)
	rand.Read(nonce)
	ciphertext := make([]byte, 32)
	dst := make([]byte, 16)
	additionalData := []byte("aad")

	plaintext, _ := aead.Open(dst, nonce, ciphertext, additionalData)

	_ = plaintext
}

func chacha20poly1305Pattern() {
	key := make([]byte, 32)
	rand.Read(key)

	aead, _ := chacha20poly1305.New(key)
	nonce := make([]byte, 12)
	rand.Read(nonce)
	plaintext := []byte("message")
	dst := make([]byte, len(plaintext)+16)
	additionalData := []byte("aad")
	ciphertext := aead.Seal(dst, nonce, plaintext, additionalData)

	_ = ciphertext
}
