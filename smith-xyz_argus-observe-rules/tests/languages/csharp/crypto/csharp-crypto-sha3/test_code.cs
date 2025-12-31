using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoSHA3
{
    class Program
    {
        static void BasicSHA3Usage()
        {
            byte[] data = Encoding.UTF8.GetBytes("test data");

            SHA3_256 hasher1 = SHA3_256.Create();

            SHA3_384 hasher2 = SHA3_384.Create();

            SHA3_512 hasher3 = SHA3_512.Create();

            SHA3_256 hasher4 = new SHA3_256();

            SHA3_384 hasher5 = new SHA3_384();

            SHA3_512 hasher6 = new SHA3_512();

            using (SHA3_256 hasher7 = SHA3_256.Create())
            {
                byte[] bytes = hasher7.ComputeHash(data);
            }

            SHA3_256 hasher8 = SHA3_256.Create();
            byte[] hash = hasher8.ComputeHash(data);
        }
    }
}
