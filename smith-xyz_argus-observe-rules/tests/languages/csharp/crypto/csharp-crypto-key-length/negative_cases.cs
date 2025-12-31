using System;
using System.Security.Cryptography;

namespace CSharpCryptoKeyLength
{
    class Program
    {
        static void NonKeyLengthUsage()
        {
            SHA256 hasher = SHA256.Create();
            byte[] hash = hasher.ComputeHash(new byte[10]);

            HMACSHA256 hmac = new HMACSHA256(new byte[32]);
        }
    }
}
