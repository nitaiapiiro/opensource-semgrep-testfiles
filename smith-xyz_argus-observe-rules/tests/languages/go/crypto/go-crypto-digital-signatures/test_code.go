package gocryptodigitalsignatures

import (
	"crypto"
	"crypto/dsa"
	"crypto/ecdsa"
	"crypto/ed25519"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/rsa"
	"crypto/sha256"
)

func rsaSignatures() {
	priv, _ := rsa.GenerateKey(rand.Reader, 2048)
	hashed := sha256.Sum256([]byte("message"))
	sig := make([]byte, 256)

	sig1, _ := rsa.SignPKCS1v15(rand.Reader, priv, crypto.SHA256, hashed[:])

	opts := &rsa.PSSOptions{SaltLength: rsa.PSSSaltLengthAuto}
	sig2, _ := rsa.SignPSS(rand.Reader, priv, crypto.SHA256, hashed[:], opts)

	err1 := rsa.VerifyPKCS1v15(&priv.PublicKey, crypto.SHA256, hashed[:], sig)

	err2 := rsa.VerifyPSS(&priv.PublicKey, crypto.SHA256, hashed[:], sig, opts)

	_, _, _, _ = sig1, sig2, err1, err2
}

func ecdsaSignatures() {
	priv, _ := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	hash := sha256.Sum256([]byte("message"))

	r, s, _ := ecdsa.Sign(rand.Reader, priv, hash[:])

	valid := ecdsa.Verify(&priv.PublicKey, hash[:], r, s)

	_ = valid
}

func dsaSignatures() {
	params := new(dsa.Parameters)
	dsa.GenerateParameters(params, rand.Reader, dsa.L1024N160)
	priv := new(dsa.PrivateKey)
	priv.PublicKey.Parameters = *params
	dsa.GenerateKey(priv, rand.Reader)
	hash := sha256.Sum256([]byte("message"))

	r, s, _ := dsa.Sign(rand.Reader, priv, hash[:])

	valid := dsa.Verify(&priv.PublicKey, hash[:], r, s)

	_ = valid
}

func ed25519Signatures() {
	pub, priv, _ := ed25519.GenerateKey(rand.Reader)
	message := []byte("message")

	sig := ed25519.Sign(priv, message)

	valid := ed25519.Verify(pub, message, sig)

	_ = valid
}
