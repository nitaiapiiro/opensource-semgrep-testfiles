using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoRC2
{
    class Program
    {
        static void BasicRC2Usage()
        {
            byte[] key = new byte[16];
            byte[] iv = new byte[8];
            byte[] plaintext = Encoding.UTF8.GetBytes("test data");

            RC2 cipher1 = RC2.Create();

            RC2CryptoServiceProvider cipher2 = new RC2CryptoServiceProvider();

            RC2Managed cipher3 = new RC2Managed();

            using (RC2 cipher4 = RC2.Create())
            {
                cipher4.Key = key;
                cipher4.IV = iv;
                ICryptoTransform encryptor = cipher4.CreateEncryptor();
                byte[] ciphertext = encryptor.TransformFinalBlock(plaintext, 0, plaintext.Length);
            }
        }
    }
}
