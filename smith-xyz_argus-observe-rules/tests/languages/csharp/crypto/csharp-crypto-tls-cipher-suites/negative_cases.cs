using System;
using System.Security.Cryptography;

namespace CSharpCryptoTlsCipherSuites
{
    class Program
    {
        static void NonTlsCipherSuitesUsage()
        {
            SHA256 hasher = SHA256.Create();
            Aes cipher = Aes.Create();
        }
    }
}
