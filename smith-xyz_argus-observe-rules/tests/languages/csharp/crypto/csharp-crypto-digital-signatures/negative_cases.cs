using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoDigitalSignatures
{
    class Program
    {
        static void NonSignatureUsage()
        {
            byte[] data = Encoding.UTF8.GetBytes("test data");

            SHA256 hasher = SHA256.Create();
            byte[] hash = hasher.ComputeHash(data);

            Aes cipher = Aes.Create();
            cipher.Key = new byte[32];
        }
    }
}
