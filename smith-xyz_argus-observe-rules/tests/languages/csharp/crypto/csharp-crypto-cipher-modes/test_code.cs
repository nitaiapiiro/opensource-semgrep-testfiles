using System;
using System.Security.Cryptography;

namespace CSharpCryptoCipherModes
{
    class Program
    {
        static void BasicCipherModeUsage()
        {
            Aes cipher = Aes.Create();
            cipher.Mode = CipherMode.CBC;
            ICryptoTransform encryptor = cipher.CreateEncryptor();

            Aes cipher2 = Aes.Create();
            cipher2.Mode = CipherMode.CFB;
            ICryptoTransform encryptor2 = cipher2.CreateEncryptor();

            Aes cipher3 = Aes.Create();
            cipher3.Mode = CipherMode.ECB;
            ICryptoTransform encryptor3 = cipher3.CreateEncryptor();

            Aes cipher4 = Aes.Create();
            cipher4.Mode = CipherMode.OFB;
            ICryptoTransform encryptor4 = cipher4.CreateEncryptor();
        }
    }
}
