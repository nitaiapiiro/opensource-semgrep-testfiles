using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoCipherDES
{
    class Program
    {
        static void BasicDESUsage()
        {
            byte[] key = Encoding.UTF8.GetBytes("secret key");
            byte[] iv = Encoding.UTF8.GetBytes("initialization vector");
            byte[] plaintext = Encoding.UTF8.GetBytes("test data");

            DES des1 = DES.Create();

            TripleDES tripleDes1 = TripleDES.Create();

            DESCryptoServiceProvider des2 = new DESCryptoServiceProvider();

            TripleDESCryptoServiceProvider tripleDes2 = new TripleDESCryptoServiceProvider();

            using (DES des3 = DES.Create())
            {
                des3.Key = key;
                des3.IV = iv;
                ICryptoTransform encryptor = des3.CreateEncryptor();
                byte[] ciphertext = encryptor.TransformFinalBlock(plaintext, 0, plaintext.Length);
            }
        }
    }
}
