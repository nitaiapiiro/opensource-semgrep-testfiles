using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoECDSA
{
    class Program
    {
        static void BasicECDSAUsage()
        {
            byte[] data = Encoding.UTF8.GetBytes("test data");

            ECDsa ecdsa1 = ECDsa.Create(ECCurve.NamedCurves.nistP256);

            ECDsaCng ecdsa2 = new ECDsaCng(ECCurve.NamedCurves.nistP384);

            using (ECDsa ecdsa3 = ECDsa.Create(ECCurve.NamedCurves.nistP521))
            {
                byte[] signature = ecdsa3.SignData(data, HashAlgorithmName.SHA256);
            }

            ECDsa ecdsa4 = ECDsa.Create(ECCurve.NamedCurves.nistP256);
            byte[] signature2 = ecdsa4.SignData(data, HashAlgorithmName.SHA256);
            bool verified = ecdsa4.VerifyData(data, signature2, HashAlgorithmName.SHA256);

            ECDsa ecdsa5 = ECDsa.Create();
            byte[] signature3 = ecdsa5.SignData(data, HashAlgorithmName.SHA384);
            bool verified2 = ecdsa5.VerifyData(data, signature3, HashAlgorithmName.SHA384);
        }
    }
}
