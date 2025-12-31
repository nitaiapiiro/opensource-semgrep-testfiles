package gocryptorsa

import (
	"crypto"
	"crypto/rand"
	"crypto/rsa"
	"crypto/sha256"
)

func keyGeneration() {
	priv1, _ := rsa.GenerateKey(rand.Reader, 2048)

	priv2, _ := rsa.GenerateKey(rand.Reader, 4096)

	priv3, _ := rsa.GenerateMultiPrimeKey(rand.Reader, 3, 2048)

	_, _, _ = priv1, priv2, priv3
}

func encryption() {
	priv, _ := rsa.GenerateKey(rand.Reader, 2048)
	pub := &priv.PublicKey
	message := []byte("secret message")
	label := []byte("label")

	ciphertext1, _ := rsa.EncryptPKCS1v15(rand.Reader, pub, message)

	ciphertext2, _ := rsa.EncryptOAEP(sha256.New(), rand.Reader, pub, message, label)

	_, _ = ciphertext1, ciphertext2
}

func decryption() {
	priv, _ := rsa.GenerateKey(rand.Reader, 2048)
	ciphertext := make([]byte, 256)
	label := []byte("label")

	plaintext1, _ := rsa.DecryptPKCS1v15(rand.Reader, priv, ciphertext)

	plaintext2, _ := rsa.DecryptOAEP(sha256.New(), rand.Reader, priv, ciphertext, label)

	_, _ = plaintext1, plaintext2
}

func signing() {
	priv, _ := rsa.GenerateKey(rand.Reader, 2048)
	hashed := sha256.Sum256([]byte("message"))

	sig1, _ := rsa.SignPKCS1v15(rand.Reader, priv, crypto.SHA256, hashed[:])

	opts := &rsa.PSSOptions{SaltLength: rsa.PSSSaltLengthAuto}
	sig2, _ := rsa.SignPSS(rand.Reader, priv, crypto.SHA256, hashed[:], opts)

	_, _ = sig1, sig2
}

func verification() {
	priv, _ := rsa.GenerateKey(rand.Reader, 2048)
	pub := &priv.PublicKey
	hashed := sha256.Sum256([]byte("message"))
	sig := make([]byte, 256)

	err1 := rsa.VerifyPKCS1v15(pub, crypto.SHA256, hashed[:], sig)

	opts := &rsa.PSSOptions{SaltLength: rsa.PSSSaltLengthAuto}
	err2 := rsa.VerifyPSS(pub, crypto.SHA256, hashed[:], sig, opts)

	_, _ = err1, err2
}

func keyUsagePattern() {
	priv, _ := rsa.GenerateKey(rand.Reader, 2048)
	pubKey := priv.PublicKey

	_ = pubKey
}

func keySizeValidation() {
	priv2048, _ := rsa.GenerateKey(rand.Reader, 2048)

	priv4096, _ := rsa.GenerateKey(rand.Reader, 4096)

	_, _ = priv2048, priv4096
}
