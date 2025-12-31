using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoSHA1
{
    class Program
    {
        static void BasicSHA1Usage()
        {
            byte[] data = Encoding.UTF8.GetBytes("test data");

            SHA1 hasher1 = SHA1.Create();

            SHA1CryptoServiceProvider hasher2 = new SHA1CryptoServiceProvider();

            using (SHA1 hasher3 = SHA1.Create())
            {
                byte[] bytes = hasher3.ComputeHash(data);
            }
        }
    }
}
