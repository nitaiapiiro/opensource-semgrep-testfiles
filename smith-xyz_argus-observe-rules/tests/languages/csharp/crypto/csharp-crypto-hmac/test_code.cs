using System;
using System.Security.Cryptography;
using System.Text;

namespace CSharpCryptoHMAC
{
    class Program
    {
        static void BasicHMACUsage()
        {
            byte[] key = Encoding.UTF8.GetBytes("secret key");
            byte[] data = Encoding.UTF8.GetBytes("test data");

            HMAC hmac1 = HMAC.Create("HMACSHA256");

            HMACSHA1 hmac2 = new HMACSHA1(key);

            HMACSHA256 hmac3 = new HMACSHA256(key);

            HMACSHA384 hmac4 = new HMACSHA384(key);

            HMACSHA512 hmac5 = new HMACSHA512(key);

            HMACMD5 hmac6 = new HMACMD5(key);

            using (HMACSHA256 hmac7 = new HMACSHA256(key))
            {
                byte[] bytes = hmac7.ComputeHash(data);
            }

            HMACSHA256 hmac8 = new HMACSHA256(key);
            hmac8.Key = key;
            byte[] result = hmac8.ComputeHash(data);

            HMAC hmac9 = HMAC.Create("HMACSHA512");
            hmac9.Key = key;
            byte[] result2 = hmac9.ComputeHash(data);
        }
    }
}
