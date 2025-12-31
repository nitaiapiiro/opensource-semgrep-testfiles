package main

import (
	"crypto/tls"
	"net/http"
	"os"

	clientv3 "go.etcd.io/etcd/client/v3"
	"google.golang.org/grpc"
	"google.golang.org/grpc/credentials"
)

// Test cases for go-pqc-config-profile-dependencies rule

// SHOULD BE FLAGGED: FIPS environment variable checks
func checkFIPSMode() {
	fipsMode := os.Getenv("GOFIPS")
	if fipsMode == "1" {
		// FIPS mode configuration
	}
}

func checkFIPS140() {
	fips140 := os.Getenv("GOFIPS140")
	_ = fips140
}

func checkGolangFIPS() {
	golangFips := os.Getenv("GOLANG_FIPS")
	_ = golangFips
}

// SHOULD BE FLAGGED: Service-to-service communication
func connectToGRPCService() {
	creds := credentials.NewTLS(&tls.Config{})
	conn, err := grpc.Dial("localhost:8080", grpc.WithTransportCredentials(creds))
	if err != nil {
		panic(err)
	}
	defer conn.Close()
}

func createHTTPClientWithTLS() {
	config := &tls.Config{
		MinVersion: tls.VersionTLS12,
	}

	client := &http.Client{
		Transport: &http.Transport{
			TLSClientConfig: config,
		},
	}
	_ = client
}

func dialTLSConnection() {
	config := &tls.Config{
		ServerName: "example.com",
	}
	conn, err := tls.Dial("tcp", "example.com:443", config)
	if err != nil {
		panic(err)
	}
	defer conn.Close()
}

// SHOULD BE FLAGGED: Kubernetes/etcd communication patterns
func connectToEtcd() {
	config := clientv3.Config{
		Endpoints: []string{"localhost:2379"},
		TLS:       &tls.Config{},
	}
	client, err := clientv3.New(config)
	if err != nil {
		panic(err)
	}
	defer client.Close()
}

func createEtcdTLSConfig() {
	tlsConfig := &tls.Config{
		InsecureSkipVerify: false,
	}

	config := clientv3.Config{
		TLS: tlsConfig,
	}
	_ = config
}
