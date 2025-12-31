package gocryptodiffiehellman

import (
	"golang.org/x/crypto/dh"
)

func diffieHellmanGenerateKey() {
	group := dh.Group14

	priv, pub, _ := dh.GenerateKey(group)

	_, _ = priv, pub
}

func diffieHellmanComputeSecret() {
	group := dh.Group14
	priv, pub, _ := dh.GenerateKey(group)
	peerPub := pub

	secret := dh.ComputeSecret(priv, peerPub)

	_ = secret
}

func diffieHellmanPattern() {
	group := dh.Group14
	priv, pub, _ := dh.GenerateKey(group)
	peerPub := pub
	secret := dh.ComputeSecret(priv, peerPub)

	_ = secret
}
