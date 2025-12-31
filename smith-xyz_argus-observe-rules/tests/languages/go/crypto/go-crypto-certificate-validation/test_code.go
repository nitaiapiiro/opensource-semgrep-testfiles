package gocryptocertificatevalidation

import (
	"crypto/tls"
	"net"
	"net/http"
	"time"
)

func insecureSkipVerifyConfig() {
	config1 := &tls.Config{
		InsecureSkipVerify: true,
	}

	config2 := &tls.Config{
		InsecureSkipVerify: false,
	}

	_, _ = config1, config2
}

func insecureSkipVerifyPattern() {
	config := &tls.Config{
		InsecureSkipVerify: true,
		MinVersion:         tls.VersionTLS12,
	}

	_ = config
}

func tlsDial() {
	config := &tls.Config{
		InsecureSkipVerify: true,
	}

	conn, _ := tls.Dial("tcp", "example.com:443", config)

	_ = conn
}

func tlsDialWithDialer() {
	dialer := &net.Dialer{
		Timeout: 5 * time.Second,
	}
	config := &tls.Config{
		InsecureSkipVerify: true,
	}

	conn, _ := tls.DialWithDialer(dialer, "tcp", "example.com:443", config)

	_ = conn
}

func httpTransport() {
	transport := &http.Transport{
		TLSClientConfig: &tls.Config{
			InsecureSkipVerify: true,
		},
	}

	_ = transport
}

func httpTransportPattern() {
	transport := &http.Transport{
		TLSClientConfig: &tls.Config{
			InsecureSkipVerify: true,
		},
		DisableKeepAlives: true,
	}

	_ = transport
}
