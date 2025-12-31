package gocryptociphersuites

import (
	"crypto/tls"
)

func insecureCipherSuites() {
	suite1 := tls.TLS_RSA_WITH_NULL_MD5

	suite2 := tls.TLS_RSA_WITH_NULL_SHA

	suite3 := tls.TLS_NULL_WITH_NULL_NULL

	suite4 := tls.TLS_RSA_EXPORT_WITH_RC4_40_MD5

	suite5 := tls.TLS_RSA_EXPORT_WITH_RC2_CBC_40_MD5

	suite6 := tls.TLS_DH_anon_EXPORT_WITH_RC4_40_MD5

	suite7 := tls.TLS_DH_anon_WITH_RC4_128_MD5

	suite8 := tls.TLS_DH_anon_WITH_3DES_EDE_CBC_SHA

	suite9 := tls.TLS_DH_anon_WITH_AES_128_CBC_SHA

	suite10 := tls.TLS_DH_anon_WITH_AES_256_CBC_SHA

	suite11 := tls.TLS_RSA_WITH_RC4_128_MD5

	suite12 := tls.TLS_RSA_WITH_RC4_128_SHA

	suite13 := tls.TLS_ECDHE_RSA_WITH_RC4_128_SHA

	suite14 := tls.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA

	suite15 := tls.TLS_RSA_WITH_DES_CBC_SHA

	suite16 := tls.TLS_DHE_RSA_WITH_DES_CBC_SHA

	suite17 := tls.TLS_RSA_WITH_3DES_EDE_CBC_SHA

	suite18 := tls.TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA

	_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _ = suite1, suite2, suite3, suite4, suite5, suite6, suite7, suite8, suite9, suite10, suite11, suite12, suite13, suite14, suite15, suite16, suite17, suite18
}
