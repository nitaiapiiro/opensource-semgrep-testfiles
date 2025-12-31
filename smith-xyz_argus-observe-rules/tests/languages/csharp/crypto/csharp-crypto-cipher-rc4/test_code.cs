using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoCipherRC4
{
    class Program
    {
        static void BasicRC4Usage()
        {
            byte[] key = Encoding.UTF8.GetBytes("secret key");
            byte[] plaintext = Encoding.UTF8.GetBytes("test data");

            RC4CryptoServiceProvider rc4 = new RC4CryptoServiceProvider();

            RC4CryptoServiceProvider rc42 = new RC4CryptoServiceProvider();

            using (RC4CryptoServiceProvider rc43 = new RC4CryptoServiceProvider())
            {
                rc43.Key = key;
                ICryptoTransform encryptor = rc43.CreateEncryptor();
            }

            RC4CryptoServiceProvider rc44 = new RC4CryptoServiceProvider();
            rc44.Key = key;
            byte[] ciphertext = rc44.TransformFinalBlock(plaintext, 0, plaintext.Length);
        }
    }
}
