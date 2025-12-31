using System;
using System.Security.Cryptography;

namespace CSharpCryptoDiffieHellman
{
    class Program
    {
        static void BasicDiffieHellmanUsage()
        {
            ECDiffieHellman alice = ECDiffieHellman.Create(ECCurve.NamedCurves.nistP256);

            ECDiffieHellmanCng bob = new ECDiffieHellmanCng(ECCurve.NamedCurves.nistP384);

            using (ECDiffieHellman alice2 = ECDiffieHellman.Create(ECCurve.NamedCurves.nistP521))
            {
                ECDiffieHellmanPublicKey publicKey = alice2.PublicKey;
            }

            ECDiffieHellman alice3 = ECDiffieHellman.Create(ECCurve.NamedCurves.nistP256);
            ECDiffieHellman bob3 = ECDiffieHellman.Create(ECCurve.NamedCurves.nistP256);
            byte[] sharedSecret = alice3.DeriveKeyMaterial(bob3.PublicKey);

            ECDiffieHellman ecdh = ECDiffieHellman.Create();
            byte[] secret = ecdh.DeriveKeyMaterial(ecdh.PublicKey);
        }
    }
}
