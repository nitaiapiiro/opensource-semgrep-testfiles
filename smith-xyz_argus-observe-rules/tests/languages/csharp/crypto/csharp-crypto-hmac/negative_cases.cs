using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoHMAC
{
    class Program
    {
        static void NonHMACUsage()
        {
            byte[] data = Encoding.UTF8.GetBytes("test data");

            SHA256 hasher = SHA256.Create();
            byte[] hash = hasher.ComputeHash(data);

            Aes cipher = Aes.Create();
            cipher.Key = new byte[32];
        }

        static void NonCryptoFunction()
        {
            string message = "This function doesn't use HMAC";
            Console.WriteLine(message);
        }
    }
}
