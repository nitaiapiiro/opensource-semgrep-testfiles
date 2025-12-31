using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoRIPEMD160
{
    class Program
    {
        static void NonRIPEMD160Usage()
        {
            SHA256 hasher1 = SHA256.Create();
            byte[] hash1 = hasher1.ComputeHash(Encoding.UTF8.GetBytes("data"));

            SHA512 hasher2 = SHA512.Create();
            byte[] hash2 = hasher2.ComputeHash(Encoding.UTF8.GetBytes("data"));

            MD5 hasher3 = MD5.Create();
            byte[] hash3 = hasher3.ComputeHash(Encoding.UTF8.GetBytes("data"));
        }
    }
}
