using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoCipherAES
{
    class Program
    {
        static void NonAESUsage()
        {
            SHA256 hasher = SHA256.Create();
            byte[] hash = hasher.ComputeHash(Encoding.UTF8.GetBytes("data"));

            DES des = DES.Create();
            TripleDES tripleDes = TripleDES.Create();
        }
    }
}
