package gocryptohashcomparison

import (
	"bytes"
	"crypto/md5"
	"crypto/sha1"
	"encoding/hex"
)

func md5Comparison() {
	data1 := []byte("data1")
	data2 := []byte("data2")

	expected := md5.Sum(data1)
	actual := md5.Sum(data2)
	equal := bytes.Equal(expected[:], actual[:])

	_ = equal
}

func sha1Comparison() {
	data1 := []byte("data1")
	data2 := []byte("data2")

	expected := sha1.Sum(data1)
	actual := sha1.Sum(data2)
	equal := bytes.Equal(expected[:], actual[:])

	_ = equal
}

func md5StringComparison() {
	data1 := []byte("data1")
	data2 := []byte("data2")

	hash1 := hex.EncodeToString(md5.Sum(data1))
	hash2 := hex.EncodeToString(md5.Sum(data2))
	equal := hash1 == hash2

	_ = equal
}

func sha1StringComparison() {
	data1 := []byte("data1")
	data2 := []byte("data2")

	hash1 := hex.EncodeToString(sha1.Sum(data1))
	hash2 := hex.EncodeToString(sha1.Sum(data2))
	equal := hash1 == hash2

	_ = equal
}

func md5IfComparison() {
	data := []byte("data")
	expected := "expected_hash"

	if hex.EncodeToString(md5.Sum(data)) == expected {
		_ = true
	}
}

func sha1IfComparison() {
	data := []byte("data")
	expected := "expected_hash"

	if hex.EncodeToString(sha1.Sum(data)) == expected {
		_ = true
	}
}
