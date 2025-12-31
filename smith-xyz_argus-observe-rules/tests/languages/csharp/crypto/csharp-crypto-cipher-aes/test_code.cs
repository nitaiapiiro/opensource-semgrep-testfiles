using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoCipherAES
{
    class Program
    {
        static void BasicAESUsage()
        {
            byte[] key = Encoding.UTF8.GetBytes("secret key");
            byte[] plaintext = Encoding.UTF8.GetBytes("test data");

            Aes cipher1 = Aes.Create();

            AesCryptoServiceProvider cipher2 = new AesCryptoServiceProvider();

            using (Aes cipher3 = Aes.Create())
            {
                cipher3.Key = key;
                cipher3.Mode = CipherMode.CBC;
                ICryptoTransform encryptor = cipher3.CreateEncryptor();
                byte[] ciphertext = encryptor.TransformFinalBlock(plaintext, 0, plaintext.Length);
            }
        }
    }
}
