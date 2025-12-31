using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoHardcodedKey
{
    class Program
    {
        static void BasicHardcodedKeyUsage()
        {
            Aes cipher = Aes.Create();
            string key = "my_secret_key_12345";
            cipher.Key = Encoding.UTF8.GetBytes(key);

            Aes cipher2 = Aes.Create();
            cipher2.Key = Encoding.UTF8.GetBytes("hardcoded_key_value");
        }
    }
}
