package main

import (
	"context"
	"crypto/tls"
	"fmt"
	"net/http"
	"os"
)

// Kubernetes logging pattern - should NOT trigger
func kubernetesLogging() {
	klogV := klog.V(5)
	if klogV.Enabled() {
		klogV.InfoS("CSIInlineVolumeSecurity evaluation", "policy", fmt.Sprintf("%v", nsPolicy), "op", attrs.GetOperation(), "resource", attrs.GetResource(), "namespace", attrs.GetNamespace(), "name", attrs.GetName())
	}
}

// Generic logging patterns - should NOT trigger
func genericLogging() {
	logger := getLogger()
	if logger.IsDebugEnabled() {
		logger.Debug("Processing request")
	}

	client := newClient()
	if client.IsConnected() {
		client.SendMessage("hello")
	}
}

// Legitimate HTTP client configuration - should NOT trigger
func legitimateHTTPClient() {
	client := &http.Client{
		Transport: &http.Transport{
			TLSClientConfig: &tls.Config{
				ServerName:         "example.com",
				InsecureSkipVerify: false,
			},
		},
	}
	_ = client
}

// Secure HTTPS URLs - should NOT trigger
func secureURLs() {
	serviceIP := "10.0.0.1"
	port := "8443"
	url := "https://" + serviceIP + ":" + port
	http.Get(url)

	// HTTPS with service discovery
	endpoints := getSecureEndpoints("user-service")
	for _, endpoint := range endpoints {
		url := "https://" + endpoint
		http.Get(url)
	}
}

// Environment checks for secure configuration - should NOT trigger
func secureEnvironmentChecks() {
	if os.Getenv("USE_TLS") == "true" {
		return &http.Client{
			Transport: &http.Transport{
				TLSClientConfig: &tls.Config{
					MinVersion: tls.VersionTLS12,
				},
			},
		}
	}
}

// Conditional secure configuration - should NOT trigger
func conditionalSecureConfig() {
	var config *tls.Config
	bypass := getSecureFlag()
	if bypass {
		config.ServerName = "example.com"
	}
}

// Database connection patterns - should NOT trigger
func databasePatterns() {
	db := getDatabase()
	if db.IsConnected() {
		db.Query("SELECT * FROM users")
	}

	conn := func() Connection {
		if isProduction() {
			return &SecureConnection{Host: "prod-db"}
		}
		return &TestConnection{Host: "test-db"}
	}()
	_ = conn
}

// Context and cancellation patterns - should NOT trigger
func contextPatterns() {
	ctx := context.Background()
	if ctx.Err() != nil {
		return
	}

	client := newAPIClient()
	if client.Ready() {
		client.Call(ctx)
	}
}

// Configuration object patterns - should NOT trigger
func configPatterns() {
	config := getConfig()
	enabled := config.Feature
	if enabled {
		return processFeature()
	}

	settings := Settings{}
	if settings.Validate() {
		settings.Apply()
	}
}

// Helper functions and types
func getLogger() Logger                  { return Logger{} }
func newClient() Client                  { return Client{} }
func getSecureEndpoints(string) []string { return []string{"secure.example.com:443"} }
func getSecureFlag() bool                { return false }
func getDatabase() Database              { return Database{} }
func isProduction() bool                 { return true }
func newAPIClient() APIClient            { return APIClient{} }
func getConfig() Config                  { return Config{} }
func processFeature() interface{}        { return nil }

type Logger struct{}

func (l Logger) IsDebugEnabled() bool { return true }
func (l Logger) Debug(string)         {}

type Client struct{}

func (c Client) IsConnected() bool  { return true }
func (c Client) SendMessage(string) {}

type Connection interface{}
type SecureConnection struct{ Host string }
type TestConnection struct{ Host string }

type Database struct{}

func (d Database) IsConnected() bool { return true }
func (d Database) Query(string)      {}

type APIClient struct{}

func (a APIClient) Ready() bool          { return true }
func (a APIClient) Call(context.Context) {}

type Config struct{ Feature bool }
type Settings struct{}

func (s Settings) Validate() bool { return true }
func (s Settings) Apply()         {}

// Mock klog for the Kubernetes example
var klog = struct {
	V func(int) KlogV
}{
	V: func(int) KlogV { return KlogV{} },
}

type KlogV struct{}

func (k KlogV) Enabled() bool                { return true }
func (k KlogV) InfoS(string, ...interface{}) {}

// Mock variables for the Kubernetes example
var nsPolicy, attrs interface{}

func (a interface{}) GetOperation() string { return "CREATE" }
func (a interface{}) GetResource() string  { return "pod" }
func (a interface{}) GetNamespace() string { return "default" }
func (a interface{}) GetName() string      { return "test-pod" }
