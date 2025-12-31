using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoSHA256
{
    class Program
    {
        static void NonSHA256Usage()
        {
            SHA512 hasher1 = SHA512.Create();
            byte[] hash1 = hasher1.ComputeHash(Encoding.UTF8.GetBytes("data"));

            MD5 hasher2 = MD5.Create();
            byte[] hash2 = hasher2.ComputeHash(Encoding.UTF8.GetBytes("data"));
        }
    }
}
