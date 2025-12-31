using System;
using System.Security.Cryptography;

namespace CSharpCryptoRSA
{
    class Program
    {
        static void NonRSAUsage()
        {
            ECDsa ecdsa = ECDsa.Create(ECCurve.NamedCurves.nistP256);
            SHA256 hasher = SHA256.Create();
        }
    }
}
