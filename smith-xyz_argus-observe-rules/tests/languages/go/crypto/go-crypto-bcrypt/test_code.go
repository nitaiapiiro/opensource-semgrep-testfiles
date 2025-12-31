package gocryptobcrypt

import (
	"golang.org/x/crypto/bcrypt"
)

func bcryptUsage() {
	password := []byte("password")
	cost := 10

	hash1, _ := bcrypt.GenerateFromPassword(password, cost)

	err := bcrypt.CompareHashAndPassword(hash1, password)

	_ = err
}

func bcryptPattern() {
	password := []byte("password")
	cost := 10

	hash, _ := bcrypt.GenerateFromPassword(password, cost)
	_ = hash
}

func bcryptComparePattern() {
	hash := []byte("$2a$10$...")
	password := []byte("password")

	err := bcrypt.CompareHashAndPassword(hash, password)
	_ = err
}

func bcryptCostValidation() {
	password := []byte("password")

	hash1, _ := bcrypt.GenerateFromPassword(password, 10)

	hash2, _ := bcrypt.GenerateFromPassword(password, 12)

	hash3, _ := bcrypt.GenerateFromPassword(password, 14)

	_, _, _ = hash1, hash2, hash3
}
