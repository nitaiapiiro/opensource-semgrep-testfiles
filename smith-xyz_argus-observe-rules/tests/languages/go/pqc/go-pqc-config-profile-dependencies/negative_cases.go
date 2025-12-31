package main

import (
	"fmt"
	"net"
	"net/http"
	"os"
)

// Negative test cases - these should NOT be flagged

// Regular environment variable checks (non-crypto related)
func checkRegularEnvVars() {
	appEnv := os.Getenv("APP_ENV")
	if appEnv == "production" {
		fmt.Println("Running in production")
	}

	debug := os.Getenv("DEBUG")
	logLevel := os.Getenv("LOG_LEVEL")
	port := os.Getenv("PORT")

	_, _, _ = debug, logLevel, port
}

// Non-TLS network operations
func regularNetworkCall() {
	// HTTP without TLS
	resp, err := http.Get("http://example.com/api")
	if err != nil {
		return
	}
	defer resp.Body.Close()
}

// Non-gRPC dial operations
func regularDial() {
	conn, err := net.Dial("tcp", "localhost:8080")
	if err != nil {
		return
	}
	defer conn.Close()
}

// Regular configuration structures
func regularConfig() {
	type AppConfig struct {
		Host string
		Port int
		TLS  bool // Just a boolean flag, not TLS config
	}

	config := AppConfig{
		Host: "localhost",
		Port: 8080,
		TLS:  true,
	}
	_ = config
}

// String operations with environment variable names
func printEnvInfo() {
	fmt.Println("Set GOFIPS environment variable for FIPS mode")
	fmt.Println("Available env vars: GOFIPS, GOLANG_FIPS")
}
