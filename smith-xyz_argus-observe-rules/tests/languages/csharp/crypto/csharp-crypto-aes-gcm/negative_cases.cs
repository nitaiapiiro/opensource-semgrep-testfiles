using System;
using System.Security.Cryptography;

namespace CSharpCryptoAesGcm
{
    class Program
    {
        static void NonAesGcmUsage()
        {
            Aes aes = Aes.Create();
            aes.Key = new byte[32];
            aes.IV = new byte[16];

            ChaCha20Poly1305 chacha = new ChaCha20Poly1305(new byte[32]);
        }
    }
}
