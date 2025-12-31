package gocryptoecdsa

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
)

func keyGeneration() {
	priv1, _ := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)

	priv2, _ := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)

	priv3, _ := ecdsa.GenerateKey(elliptic.P521(), rand.Reader)

	priv4, _ := ecdsa.GenerateKey(elliptic.Secp256k1(), rand.Reader)

	_, _, _, _ = priv1, priv2, priv3, priv4
}

func signing() {
	priv, _ := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	hash := sha256.Sum256([]byte("message"))

	r, s, _ := ecdsa.Sign(rand.Reader, priv, hash[:])

	_, _ = r, s
}

func verification() {
	priv, _ := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	pub := &priv.PublicKey
	hash := sha256.Sum256([]byte("message"))
	r := new(int)
	s := new(int)

	valid := ecdsa.Verify(pub, hash[:], r, s)

	_ = valid
}

func keyUsagePattern() {
	priv, _ := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	pubKey := priv.PublicKey

	_ = pubKey
}

func signVerifyPattern() {
	priv, _ := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	hash := sha256.Sum256([]byte("message"))

	r, s, _ := ecdsa.Sign(rand.Reader, priv, hash[:])
	valid := ecdsa.Verify(&priv.PublicKey, hash[:], r, s)

	_ = valid
}

func ellipticCurves() {
	curve1 := elliptic.P256()

	curve2 := elliptic.P384()

	curve3 := elliptic.P521()

	curve4 := elliptic.Secp256k1()

	_, _, _, _ = curve1, curve2, curve3, curve4
}
