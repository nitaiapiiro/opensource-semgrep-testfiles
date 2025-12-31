using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoKeyDerivation
{
    class Program
    {
        static void BasicKeyDerivationUsage()
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
        }
    }
}
