using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoIVNonce
{
    class Program
    {
        static void BasicIVUsage()
        {
            Aes cipher = Aes.Create();
            byte[] iv = new byte[16];
            cipher.IV = iv;
            ICryptoTransform encryptor = cipher.CreateEncryptor();

            Aes cipher2 = Aes.Create();
            cipher2.IV = Encoding.UTF8.GetBytes("static_iv_value");
            ICryptoTransform encryptor2 = cipher2.CreateEncryptor();

            using (Aes cipher3 = Aes.Create())
            {
                cipher3.IV = new byte[16];
                ICryptoTransform encryptor3 = cipher3.CreateEncryptor();
            }

            Aes cipher4 = Aes.Create();
            cipher4.IV = new byte[16];
            ICryptoTransform encryptor4 = cipher4.CreateEncryptor();
        }
    }
}
