using System;
using System.Security.Cryptography;

namespace CSharpCryptoChaCha20Poly1305
{
    class Program
    {
        static void NonChaCha20Poly1305Usage()
        {
            Aes aes = Aes.Create();
            aes.Key = new byte[32];
            aes.IV = new byte[16];

            AesGcm aesGcm = new AesGcm(new byte[32]);
        }
    }
}
