using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoCipherRC4
{
    class Program
    {
        static void NonRC4Usage()
        {
            byte[] key = Encoding.UTF8.GetBytes("secret key");
            byte[] plaintext = Encoding.UTF8.GetBytes("test data");

            Aes cipher = Aes.Create();
            cipher.Key = key;
            ICryptoTransform encryptor = cipher.CreateEncryptor();
            byte[] ciphertext = encryptor.TransformFinalBlock(plaintext, 0, plaintext.Length);
        }
    }
}
