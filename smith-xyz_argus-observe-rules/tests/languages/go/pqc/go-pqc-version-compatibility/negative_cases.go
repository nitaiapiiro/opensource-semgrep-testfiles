//go:build linux

package main

import (
	"fmt"
	"os"
)

// Negative test cases - these should NOT be flagged

// Normal Go code without version checks
func regularFunction() {
	fmt.Println("Regular function")
}

// Environment variable checks (not version related)
func checkEnvironment() {
	env := os.Getenv("APP_ENV")
	if env == "production" {
		fmt.Println("Running in production")
	}
}

// String operations that might contain "version"
func printVersion() {
	fmt.Println("Application version: 1.0.0")
}

// Modern build constraints that don't involve specific Go versions

func linuxSpecificFunction() {
	fmt.Println("Linux-specific functionality")
}

// Function that doesn't use runtime package
func normalBusinessLogic() {
	data := []string{"item1", "item2"}
	for _, item := range data {
		fmt.Println(item)
	}
}
