using System;
using System.Net;
using System.Net.Http;

namespace CSharpCryptoCertificateValidation
{
    class Program
    {
        static void BasicCertificateValidationUsage()
        {
            ServicePointManager.ServerCertificateValidationCallback = null;

            ServicePointManager.ServerCertificateValidationCallback =
                delegate { return true; };

            HttpClientHandler handler = new HttpClientHandler();
            handler.ServerCertificateCustomValidationCallback =
                (request, cert, chain, errors) => true;

            HttpClientHandler handler2 = new HttpClientHandler();
            handler2.ServerCertificateCustomValidationCallback =
                (request, cert, chain, errors) => { return true; };
        }
    }
}
