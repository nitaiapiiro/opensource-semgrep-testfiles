using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoRIPEMD160
{
    class Program
    {
        static void BasicRIPEMD160Usage()
        {
            byte[] data = Encoding.UTF8.GetBytes("test data");

            RIPEMD160 hasher1 = RIPEMD160.Create();

            RIPEMD160Managed hasher2 = new RIPEMD160Managed();

            using (RIPEMD160 hasher3 = RIPEMD160.Create())
            {
                byte[] bytes = hasher3.ComputeHash(data);
            }

            RIPEMD160 hasher4 = RIPEMD160.Create();
            byte[] hash4 = hasher4.ComputeHash(data);
        }
    }
}
