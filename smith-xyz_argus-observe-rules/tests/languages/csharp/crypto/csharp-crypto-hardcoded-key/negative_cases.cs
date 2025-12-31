using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoHardcodedKey
{
    class Program
    {
        static void NonHardcodedKeyUsage()
        {
            Aes cipher = Aes.Create();
            byte[] key = new byte[32];
            RandomNumberGenerator.Fill(key);
            cipher.Key = key;

            string keyFromConfig = Environment.GetEnvironmentVariable("CRYPTO_KEY");
            if (!string.IsNullOrEmpty(keyFromConfig))
            {
                cipher.Key = Encoding.UTF8.GetBytes(keyFromConfig);
            }
        }
    }
}
