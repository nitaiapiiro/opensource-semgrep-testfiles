using System;
using System.Net.Security;
using System.Security.Authentication;

namespace CSharpCryptoTlsCipherSuites
{
    class Program
    {
        static void BasicTlsCipherSuitesUsage()
        {
            CipherSuitesPolicy policy = new CipherSuitesPolicy(
                new[] { TlsCipherSuite.TLS_AES_256_GCM_SHA384 });

            CipherSuitesPolicy policy2 = new CipherSuitesPolicy(
                new[] { TlsCipherSuite.TLS_AES_128_GCM_SHA256 });

            TlsCipherSuite suite1 = TlsCipherSuite.TLS_AES_256_GCM_SHA384;
            TlsCipherSuite suite2 = TlsCipherSuite.TLS_AES_128_GCM_SHA256;
            TlsCipherSuite suite3 = TlsCipherSuite.TLS_CHACHA20_POLY1305_SHA256;
            TlsCipherSuite suite4 = TlsCipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384;
            TlsCipherSuite suite5 = TlsCipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384;
        }
    }
}
