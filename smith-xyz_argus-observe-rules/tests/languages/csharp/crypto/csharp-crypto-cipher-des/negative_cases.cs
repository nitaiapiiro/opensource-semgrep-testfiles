using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoCipherDES
{
    class Program
    {
        static void NonDESUsage()
        {
            Aes aes = Aes.Create();
            SHA256 hasher = SHA256.Create();
        }
    }
}
