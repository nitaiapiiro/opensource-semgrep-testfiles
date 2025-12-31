using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoPBKDF2
{
    class Program
    {
        static void BasicPBKDF2Usage()
        {
            byte[] password = Encoding.UTF8.GetBytes("password");
            byte[] salt = Encoding.UTF8.GetBytes("salt");
            int iterations = 10000;

            Rfc2898DeriveBytes kdf1 = new Rfc2898DeriveBytes(password, salt, iterations);

            Rfc2898DeriveBytes kdf2 = new Rfc2898DeriveBytes(password, salt, iterations, HashAlgorithmName.SHA256);

            using (Rfc2898DeriveBytes kdf3 = new Rfc2898DeriveBytes(password, salt, iterations))
            {
                byte[] key = kdf3.GetBytes(32);
            }

            Rfc2898DeriveBytes kdf4 = new Rfc2898DeriveBytes(password, salt, iterations);
            byte[] key2 = kdf4.GetBytes(32);
        }
    }
}
