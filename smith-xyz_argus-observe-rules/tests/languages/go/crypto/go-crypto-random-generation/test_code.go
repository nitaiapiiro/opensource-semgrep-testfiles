package gocryptorandomgeneration

import (
	"crypto/rand"
	"io"
	"math/big"
	mathrand "math/rand"
	"time"
)

func keyGeneration() {
	key := make([]byte, 32)
	rand.Read(key)

	_ = key
}

func ivGeneration() {
	iv := make([]byte, 16)
	rand.Read(iv)

	_ = iv
}

func cryptoRandInt() {
	max := big.NewInt(100)
	n, _ := rand.Int(rand.Reader, max)

	_ = n
}

func cryptoRandPrime() {
	bits := 2048
	p, _ := rand.Prime(rand.Reader, bits)

	_ = p
}

func ioReadFullRandReader() {
	buffer := make([]byte, 32)
	io.ReadFull(rand.Reader, buffer)

	_ = buffer
}

func ioReadFullRandReaderWithMake() {
	buffer := make([]byte, 32)
	io.ReadFull(rand.Reader, buffer)

	_ = buffer
}

func mathRandRead() {
	bytes := make([]byte, 16)
	mathrand.Read(bytes)

	_ = bytes
}

func mathRandReadDirect() {
	bytes := make([]byte, 16)
	math/rand.Read(bytes)

	_ = bytes
}

func mathRandSeed() {
	seed := time.Now().UnixNano()
	mathrand.Seed(seed)
	value := mathrand.Intn(100)

	_ = value
}

func mathRandSeedPattern() {
	seed := int64(12345)
	mathrand.Seed(seed)
	value := mathrand.Intn(100)

	_ = value
}

func mathRandNew() {
	source := mathrand.NewSource(time.Now().UnixNano())
	rng := mathrand.New(source)
	value := rng.Intn(100)

	_ = value
}

func mathRandNewDirect() {
	source := math/rand.NewSource(time.Now().UnixNano())
	rng := math/rand.New(source)
	value := rng.Intn(100)

	_ = value
}

func mathRandNewSource() {
	source := mathrand.NewSource(time.Now().UnixNano())
	rng := mathrand.New(source)

	_ = rng
}

func mathRandNewSourceDirect() {
	source := math/rand.NewSource(time.Now().UnixNano())
	rng := math/rand.New(source)

	_ = rng
}
