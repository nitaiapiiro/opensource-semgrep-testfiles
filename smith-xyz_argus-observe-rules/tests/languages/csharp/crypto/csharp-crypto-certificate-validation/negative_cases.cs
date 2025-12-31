using System;
using System.Net.Http;

namespace CSharpCryptoCertificateValidation
{
    class Program
    {
        static void NonCertificateValidationUsage()
        {
            HttpClient client = new HttpClient();
            HttpResponseMessage response = client.GetAsync("https://example.com").Result;

            SHA256 hasher = System.Security.Cryptography.SHA256.Create();
        }
    }
}
