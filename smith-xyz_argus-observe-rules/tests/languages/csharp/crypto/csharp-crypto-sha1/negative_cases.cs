using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoSHA1
{
    class Program
    {
        static void GoodHashingPractices()
        {
            byte[] data = Encoding.UTF8.GetBytes("test data");

            SHA256 hasher1 = SHA256.Create();
            byte[] hash1 = hasher1.ComputeHash(data);

            SHA512 hasher2 = SHA512.Create();
            byte[] hash2 = hasher2.ComputeHash(data);
        }

        static void NonCryptoFunction()
        {
            string message = "This function doesn't use SHA1";
            Console.WriteLine(message);
        }
    }
}
