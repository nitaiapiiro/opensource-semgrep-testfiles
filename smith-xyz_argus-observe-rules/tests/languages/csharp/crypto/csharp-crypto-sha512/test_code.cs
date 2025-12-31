using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoSHA512
{
    class Program
    {
        static void BasicSHA512Usage()
        {
            byte[] data = Encoding.UTF8.GetBytes("test data");

            SHA512 hasher1 = SHA512.Create();

            SHA512 hasher2 = new SHA512CryptoServiceProvider();

            using (SHA512 hasher3 = SHA512.Create())
            {
                byte[] bytes = hasher3.ComputeHash(data);
            }

            SHA512 hasher4 = SHA512.Create();
            byte[] hash = hasher4.ComputeHash(data);

            SHA512Managed hasher5 = new SHA512Managed();
        }
    }
}
