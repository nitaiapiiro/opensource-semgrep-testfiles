using System;
using System.Security.Cryptography;

namespace CSharpCryptoDiffieHellman
{
    class Program
    {
        static void NonDiffieHellmanUsage()
        {
            RSA rsa = RSA.Create(2048);
            ECDsa ecdsa = ECDsa.Create(ECCurve.NamedCurves.nistP256);
        }
    }
}
