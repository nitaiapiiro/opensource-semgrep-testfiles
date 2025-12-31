using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoSP800108
{
    class Program
    {
        static void NonSP800108Usage()
        {
            byte[] password = Encoding.UTF8.GetBytes("password");
            byte[] salt = new byte[16];
            int iterations = 10000;

            Rfc2898DeriveBytes pbkdf2 = new Rfc2898DeriveBytes(password, salt, iterations);
            byte[] key = pbkdf2.GetBytes(32);
        }
    }
}
