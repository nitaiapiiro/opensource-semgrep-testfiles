using System;
using System.Security.Cryptography;

namespace CSharpCryptoRSA
{
    class Program
    {
        static void BasicRSAUsage()
        {
            RSA rsa1 = RSA.Create(2048);

            RSACryptoServiceProvider rsa2 = new RSACryptoServiceProvider(2048);

            using (RSA rsa3 = RSA.Create(2048))
            {
                RSAParameters publicKey = rsa3.ExportParameters(false);
                RSAParameters privateKey = rsa3.ExportParameters(true);
            }

            RSA rsa4 = RSA.Create(4096);
        }
    }
}
