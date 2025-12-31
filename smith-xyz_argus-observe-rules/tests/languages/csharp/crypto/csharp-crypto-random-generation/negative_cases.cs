using System;
using System.Security.Cryptography;

namespace CSharpCryptoRandomGeneration
{
    class Program
    {
        static void NonRandomGenerationUsage()
        {
            SHA256 hasher = SHA256.Create();
            byte[] hash = hasher.ComputeHash(new byte[10]);

            Aes cipher = Aes.Create();
            cipher.Key = new byte[32];
        }
    }
}
