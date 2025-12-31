//go:build go1.21
// +build go1.21

package main

import (
	"fmt"
	"runtime"
)

// Test cases for go-pqc-version-compatibility rule

// SHOULD BE FLAGGED: Runtime version detection
func checkGoVersion() {
	version := runtime.Version() // This should be flagged
	fmt.Printf("Go version: %s\n", version)
}

// SHOULD BE FLAGGED: Build constraint patterns
func legacyGoFunction() {
	// This function only builds on Go 1.21
}

// SHOULD BE FLAGGED: Legacy build constraint
func anotherLegacyFunction() {
	// This function uses legacy build constraint
}

// Additional runtime version usage
func detectPQCSupport() {
	goVersion := runtime.Version() // Should be flagged
	if goVersion < "go1.24" {
		fmt.Println("PQC support not available")
	}
}
