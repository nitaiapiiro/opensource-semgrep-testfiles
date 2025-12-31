using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoSHA384
{
    class Program
    {
        static void BasicSHA384Usage()
        {
            byte[] data = Encoding.UTF8.GetBytes("test data");

            SHA384 hasher1 = SHA384.Create();

            SHA384CryptoServiceProvider hasher2 = new SHA384CryptoServiceProvider();

            SHA384Managed hasher3 = new SHA384Managed();

            using (SHA384 hasher4 = SHA384.Create())
            {
                byte[] bytes = hasher4.ComputeHash(data);
            }

            SHA384 hasher5 = SHA384.Create();
            byte[] hash5 = hasher5.ComputeHash(data);
        }
    }
}
