using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoSaltGeneration
{
    class Program
    {
        static void BasicSaltGeneration()
        {
            byte[] password = Encoding.UTF8.GetBytes("password");
            int iterations = 10000;

            byte[] salt = new byte[16];
            RandomNumberGenerator.Fill(salt);
            Rfc2898DeriveBytes kdf = new Rfc2898DeriveBytes(password, salt, iterations);

            RandomNumberGenerator rng = RandomNumberGenerator.Create();
            byte[] salt2 = new byte[16];
            rng.GetBytes(salt2);
            Rfc2898DeriveBytes kdf2 = new Rfc2898DeriveBytes(password, salt2, iterations);

            byte[] salt3 = Encoding.UTF8.GetBytes("static_salt_value");
            Rfc2898DeriveBytes kdf3 = new Rfc2898DeriveBytes(password, salt3, iterations);

            using (RandomNumberGenerator rng2 = RandomNumberGenerator.Create())
            {
                byte[] salt4 = new byte[16];
                rng2.GetBytes(salt4);
            }
            Rfc2898DeriveBytes kdf4 = new Rfc2898DeriveBytes(password, new byte[16], iterations);
        }
    }
}
