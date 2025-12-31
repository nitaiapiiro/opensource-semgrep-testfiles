using System;
using System.Security.Cryptography;

namespace CSharpCryptoKeyLength
{
    class Program
    {
        static void BasicKeyLengthUsage()
        {
            Aes cipher = Aes.Create();
            byte[] key = new byte[16];
            cipher.Key = key;

            Aes cipher2 = Aes.Create();
            byte[] key2 = new byte[32];
            cipher2.Key = key2;

            Aes cipher3 = Aes.Create();
            byte[] key3 = new byte[24];
            cipher3.Key = key3;

            Aes cipher4 = Aes.Create();
            cipher4.KeySize = 256;

            Aes cipher5 = Aes.Create();
            cipher5.Key = new byte[16];
        }
    }
}
