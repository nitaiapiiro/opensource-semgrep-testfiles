using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoMD5
{
    class Program
    {
        static void BasicMD5Usage()
        {
            byte[] data = Encoding.UTF8.GetBytes("test data");

            MD5 hasher1 = MD5.Create();

            MD5CryptoServiceProvider hasher2 = new MD5CryptoServiceProvider();

            using (MD5 hasher3 = MD5.Create())
            {
                byte[] bytes = hasher3.ComputeHash(data);
            }

            MD5 hasher4 = MD5.Create();
            byte[] hash = hasher4.ComputeHash(data);
        }
    }
}
