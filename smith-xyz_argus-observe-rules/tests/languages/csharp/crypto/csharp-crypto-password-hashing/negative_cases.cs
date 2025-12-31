using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoPasswordHashing
{
    class Program
    {
        static void NonPasswordHashingUsage()
        {
            SHA256 hasher = SHA256.Create();
            byte[] hash = hasher.ComputeHash(Encoding.UTF8.GetBytes("data"));

            Aes cipher = Aes.Create();
            cipher.Key = new byte[32];
        }
    }
}
