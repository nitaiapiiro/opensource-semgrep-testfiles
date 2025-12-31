package main

import (
	"crypto/tls"
	"database/sql"
	"net/http"
	"os"
	"reflect"
)

type Config struct {
	TLS             TLSConfig
	Net             NetConfig
	SSL             SSLConfig
	TLSConfig       string
	SSLMode         string
	TLSClientConfig TLSClientConfig
	Insecure        bool
}

type TLSConfig struct {
	Enable bool
}

type NetConfig struct {
	TLS TLSConfig
}

type SSLConfig struct {
	Enable bool
}

type TLSClientConfig struct {
	Insecure bool
}

func main() {
	// Direct TLS validation bypass - line 35
	config := &tls.Config{InsecureSkipVerify: true}

	// Variable assignment - line 38
	insecureVar := true
	config.InsecureSkipVerify = insecureVar

	// Function assignment - line 41
	config.InsecureSkipVerify = getInsecureFlag()

	// TLS Config struct initialization - line 44
	tlsConfig := tls.Config{InsecureSkipVerify: true}

	// TLS Config pointer initialization - line 47
	tlsConfigPtr := &tls.Config{InsecureSkipVerify: true}

	// Kubernetes REST client bypass - line 50
	var restConfig Config
	restConfig.TLSClientConfig.Insecure = true

	// Generic Insecure field - line 53
	restConfig.Insecure = true

	// Conditional bypass - line 56
	if isDevelopment() {
		config.InsecureSkipVerify = true
	}

	// Environment variable bypass - line 60
	if os.Getenv("SKIP_TLS_VERIFY") == "true" {
		config.InsecureSkipVerify = true
	}

	// Reflection-based bypass - line 64
	field := reflect.ValueOf(config).Elem().FieldByName("InsecureSkipVerify")
	field.SetBool(true)

	// Empty ServerName bypass - line 67
	config.ServerName = ""

	// HTTP fallback pattern - line 70
	hostname := "example.com"
	url := "http://" + hostname
	http.Get(url)

	// Certificate validation bypass - line 74
	config.VerifyPeerCertificate = nil

	// Boolean TLS disable patterns - line 77
	var kafkaConfig Config
	kafkaConfig.TLS.Enable = false

	// Net TLS disable - line 81
	var networkConfig Config
	networkConfig.Net.TLS.Enable = false

	// SSL disable - line 85
	var sslConfig Config
	sslConfig.SSL.Enable = false

	// String-based TLS disable - line 89
	var mysqlConfig Config
	mysqlConfig.TLSConfig = "false"

	// SSL mode disable - line 93
	var postgresConfig Config
	postgresConfig.SSLMode = "disable"

	// DSN connection string bypasses - line 97
	connectionString := "host=localhost"
	sql.Open("postgres", connectionString+"?sslmode=disable")

	// DSN with tls=false - line 101
	dsn := "user:pass@tcp(localhost:3306)/db"
	sql.Open("mysql", dsn+"&tls=false")

	// DSN with ssl=false - line 105
	connStr := "server=localhost"
	sql.Open("driver", connStr+"?ssl=false")
}

// Helper functions
func getInsecureFlag() bool {
	return true
}

func isDevelopment() bool {
	return true
}
