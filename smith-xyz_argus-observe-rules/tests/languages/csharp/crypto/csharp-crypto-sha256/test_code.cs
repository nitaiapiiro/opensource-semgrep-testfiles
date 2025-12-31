using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoSHA256
{
    class Program
    {
        static void BasicSHA256Usage()
        {
            byte[] data = Encoding.UTF8.GetBytes("test data");

            SHA256 hasher1 = SHA256.Create();

            SHA256CryptoServiceProvider hasher2 = new SHA256CryptoServiceProvider();

            using (SHA256 hasher3 = SHA256.Create())
            {
                byte[] bytes = hasher3.ComputeHash(data);
            }
        }
    }
}
