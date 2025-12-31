using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoDigitalSignatures
{
    class Program
    {
        static void BasicDigitalSignatureUsage()
        {
            byte[] data = Encoding.UTF8.GetBytes("test data");

            RSA rsa = RSA.Create(2048);
            byte[] signature = rsa.SignData(data, HashAlgorithmName.SHA256, RSASignaturePadding.Pkcs1);
            bool verified = rsa.VerifyData(data, signature, HashAlgorithmName.SHA256, RSASignaturePadding.Pkcs1);

            using (RSA rsa2 = RSA.Create(2048))
            {
                byte[] signature2 = rsa2.SignData(data, HashAlgorithmName.SHA256, RSASignaturePadding.Pkcs1);
            }

            ECDsa ecdsa = ECDsa.Create(ECCurve.NamedCurves.nistP256);
            byte[] signature3 = ecdsa.SignData(data, HashAlgorithmName.SHA256);
            bool verified2 = ecdsa.VerifyData(data, signature3, HashAlgorithmName.SHA256);

            using (ECDsa ecdsa2 = ECDsa.Create(ECCurve.NamedCurves.nistP384))
            {
                byte[] signature4 = ecdsa2.SignData(data, HashAlgorithmName.SHA384);
            }
        }
    }
}
