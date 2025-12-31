using System;
using System.Security.Cryptography;

namespace CSharpCryptoRC2
{
    class Program
    {
        static void NonRC2Usage()
        {
            Aes aes = Aes.Create();
            aes.Key = new byte[32];
            aes.IV = new byte[16];

            DES des = DES.Create();
            des.Key = new byte[8];
            des.IV = new byte[8];
        }
    }
}
