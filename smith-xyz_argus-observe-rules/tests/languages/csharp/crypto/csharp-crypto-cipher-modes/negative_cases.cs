using System;
using System.Security.Cryptography;

namespace CSharpCryptoCipherModes
{
    class Program
    {
        static void NonCipherModeUsage()
        {
            Aes cipher = Aes.Create();
            cipher.Key = new byte[32];
            ICryptoTransform encryptor = cipher.CreateEncryptor();

            SHA256 hasher = SHA256.Create();
        }
    }
}
