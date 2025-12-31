using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoSHA512
{
    class Program
    {
        static void GoodHashingPractices()
        {
            byte[] data = Encoding.UTF8.GetBytes("test data");

            SHA256 hasher1 = SHA256.Create();
            byte[] hash1 = hasher1.ComputeHash(data);

            SHA384 hasher2 = SHA384.Create();
            byte[] hash2 = hasher2.ComputeHash(data);
        }

        static void NonCryptoFunction()
        {
            string message = "This function doesn't use any hashing";
            Console.WriteLine(message);
        }
    }
}
