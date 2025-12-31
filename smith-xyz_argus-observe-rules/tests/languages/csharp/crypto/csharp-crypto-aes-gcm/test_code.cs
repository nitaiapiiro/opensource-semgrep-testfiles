using System;
using System.Security.Cryptography;

namespace CSharpCryptoAesGcm
{
    class Program
    {
        static void BasicAesGcmUsage()
        {
            byte[] key = new byte[32];
            byte[] nonce = new byte[12];
            byte[] plaintext = new byte[100];
            byte[] ciphertext = new byte[100];
            byte[] tag = new byte[16];
            byte[] additionalData = new byte[50];

            AesGcm aesGcm1 = new AesGcm(key);

            using (AesGcm aesGcm2 = new AesGcm(key))
            {
                aesGcm2.Encrypt(nonce, plaintext, ciphertext, tag, additionalData);
            }

            AesGcm aesGcm3 = new AesGcm(key);
            aesGcm3.Encrypt(nonce, plaintext, ciphertext, tag, additionalData);

            using (AesGcm aesGcm4 = new AesGcm(key))
            {
                aesGcm4.Decrypt(nonce, ciphertext, tag, plaintext, additionalData);
            }

            AesGcm aesGcm5 = new AesGcm(key);
            aesGcm5.Decrypt(nonce, ciphertext, tag, plaintext, additionalData);
        }
    }
}
