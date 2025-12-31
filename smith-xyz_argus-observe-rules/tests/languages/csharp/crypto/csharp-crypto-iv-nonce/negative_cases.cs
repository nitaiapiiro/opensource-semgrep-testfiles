using System;
using System.Security.Cryptography;

namespace CSharpCryptoIVNonce
{
    class Program
    {
        static void NonIVUsage()
        {
            Aes cipher = Aes.Create();
            cipher.Key = new byte[32];
            ICryptoTransform encryptor = cipher.CreateEncryptor();

            SHA256 hasher = SHA256.Create();
        }
    }
}
