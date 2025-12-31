using System;
using System.Security.Cryptography;

namespace CSharpCryptoTlsVersion
{
    class Program
    {
        static void NonTlsVersionUsage()
        {
            SHA256 hasher = SHA256.Create();
            Aes cipher = Aes.Create();
        }
    }
}
