using System;
using System.Net;

namespace CSharpCryptoTlsVersion
{
    class Program
    {
        static void BasicTlsVersionUsage()
        {
            SecurityProtocolType ssl3 = SecurityProtocolType.Ssl3;

            SecurityProtocolType tls = SecurityProtocolType.Tls;

            SecurityProtocolType tls11 = SecurityProtocolType.Tls11;

            SecurityProtocolType tls12 = SecurityProtocolType.Tls12;

            SecurityProtocolType tls13 = SecurityProtocolType.Tls13;

            ServicePointManager.SecurityProtocol = SecurityProtocolType.Tls12;

            ServicePointManager.SecurityProtocol = SecurityProtocolType.Tls12 | SecurityProtocolType.Tls13;
        }
    }
}
