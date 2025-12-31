using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoECDSA
{
    class Program
    {
        static void NonECDSAUsage()
        {
            byte[] data = Encoding.UTF8.GetBytes("test data");

            RSA rsa = RSA.Create(2048);
            byte[] signature = rsa.SignData(data, HashAlgorithmName.SHA256, RSASignaturePadding.Pkcs1);

            SHA256 hasher = SHA256.Create();
            byte[] hash = hasher.ComputeHash(data);
        }
    }
}
