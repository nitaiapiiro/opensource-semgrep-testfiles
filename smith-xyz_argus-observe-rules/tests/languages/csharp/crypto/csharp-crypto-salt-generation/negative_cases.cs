using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoSaltGeneration
{
    class Program
    {
        static void NonSaltGeneration()
        {
            byte[] data = Encoding.UTF8.GetBytes("data");
            SHA256 hasher = SHA256.Create();
            byte[] hash = hasher.ComputeHash(data);

            Aes cipher = Aes.Create();
            byte[] key = new byte[32];
            cipher.Key = key;
        }
    }
}
