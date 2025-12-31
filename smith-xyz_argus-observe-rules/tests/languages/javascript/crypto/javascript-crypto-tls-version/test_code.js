const https = require('https');

function tlsVersions() {
  const options1 = {
    secureProtocol: 'TLSv1_method',
    hostname: 'example.com',
    port: 443
  };

  const options2 = {
    secureProtocol: 'TLSv1_1_method',
    hostname: 'example.com',
    port: 443
  };

  const options3 = {
    secureProtocol: 'TLSv1_2_method',
    hostname: 'example.com',
    port: 443
  };

  const options4 = {
    minVersion: 'TLSv1.2',
    maxVersion: 'TLSv1.3',
    hostname: 'example.com',
    port: 443
  };

  https.request(options1);
  https.request(options2);
  https.request(options3);
  https.request(options4);
}
