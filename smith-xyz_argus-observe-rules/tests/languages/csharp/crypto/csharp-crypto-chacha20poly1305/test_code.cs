using System;
using System.Security.Cryptography;

namespace CSharpCryptoChaCha20Poly1305
{
    class Program
    {
        static void BasicChaCha20Poly1305Usage()
        {
            byte[] key = new byte[32];
            byte[] nonce = new byte[12];
            byte[] plaintext = new byte[100];
            byte[] ciphertext = new byte[100];
            byte[] tag = new byte[16];
            byte[] additionalData = new byte[50];

            ChaCha20Poly1305 chacha1 = new ChaCha20Poly1305(key);

            using (ChaCha20Poly1305 chacha2 = new ChaCha20Poly1305(key))
            {
                chacha2.Encrypt(nonce, plaintext, ciphertext, tag, additionalData);
            }

            ChaCha20Poly1305 chacha3 = new ChaCha20Poly1305(key);
            chacha3.Encrypt(nonce, plaintext, ciphertext, tag, additionalData);

            using (ChaCha20Poly1305 chacha4 = new ChaCha20Poly1305(key))
            {
                chacha4.Decrypt(nonce, ciphertext, tag, plaintext, additionalData);
            }

            ChaCha20Poly1305 chacha5 = new ChaCha20Poly1305(key);
            chacha5.Decrypt(nonce, ciphertext, tag, plaintext, additionalData);
        }
    }
}
