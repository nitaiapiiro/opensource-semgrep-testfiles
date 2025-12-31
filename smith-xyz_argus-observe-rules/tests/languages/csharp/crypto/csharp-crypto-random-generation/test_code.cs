using System;
using System.Security.Cryptography;

namespace CSharpCryptoRandomGeneration
{
    class Program
    {
        static void BasicRandomGenerationUsage()
        {
            RandomNumberGenerator rng1 = RandomNumberGenerator.Create();

            RNGCryptoServiceProvider rng2 = new RNGCryptoServiceProvider();

            using (RandomNumberGenerator rng3 = RandomNumberGenerator.Create())
            {
                byte[] bytes = new byte[16];
                rng3.GetBytes(bytes);
            }

            byte[] randomBytes = new byte[32];
            RandomNumberGenerator.Fill(randomBytes);
        }
    }
}
