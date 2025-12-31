using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoPasswordHashing
{
    class Program
    {
        static void BasicPasswordHashingUsage()
        {
            string password = "user_password";
            int workFactor = 10;

            string hash1 = BCrypt.Net.BCrypt.HashPassword(password, workFactor);

            bool verified1 = BCrypt.Net.BCrypt.Verify(password, hash1);

            byte[] salt = Encoding.UTF8.GetBytes("salt");
            int iterations = 10000;
            Rfc2898DeriveBytes kdf = new Rfc2898DeriveBytes(Encoding.UTF8.GetBytes(password), salt, iterations);

            string hash2 = BCrypt.Net.BCrypt.HashPassword(password);
            bool verified2 = BCrypt.Net.BCrypt.Verify(password, hash2);
        }
    }
}
