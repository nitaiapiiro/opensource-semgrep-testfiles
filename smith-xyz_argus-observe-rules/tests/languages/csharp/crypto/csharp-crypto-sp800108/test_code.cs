using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoSP800108
{
    class Program
    {
        static void BasicSP800108Usage()
        {
            byte[] key = Encoding.UTF8.GetBytes("master_key");
            byte[] label = Encoding.UTF8.GetBytes("label");
            byte[] context = Encoding.UTF8.GetBytes("context");
            HashAlgorithmName hashAlgorithm = HashAlgorithmName.SHA256;

            SP800108DeriveBytes kdf1 = new SP800108DeriveBytes(key, label, context, hashAlgorithm);

            using (SP800108DeriveBytes kdf2 = new SP800108DeriveBytes(key, label, context, hashAlgorithm))
            {
                byte[] derivedKey = kdf2.GetBytes(32);
            }

            SP800108DeriveBytes kdf3 = new SP800108DeriveBytes(key, label, context, hashAlgorithm);
            byte[] derivedKey3 = kdf3.GetBytes(32);
        }
    }
}
