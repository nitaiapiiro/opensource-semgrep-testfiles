const https = require('https');
const tls = require('tls');

function tlsCipherSuites() {
  const options1 = {
    ciphers: 'ECDHE-RSA-AES128-GCM-SHA256',
    hostname: 'example.com',
    port: 443
  };

  const options2 = {
    ciphers: 'ECDHE-RSA-AES256-GCM-SHA384:ECDHE-RSA-AES128-GCM-SHA256',
    hostname: 'example.com',
    port: 443
  };

  const options3 = {
    honorCipherOrder: true,
    ciphers: 'ECDHE-RSA-AES128-GCM-SHA256',
    hostname: 'example.com',
    port: 443
  };

  const options4 = {
    secureOptions: 0x00000004,
    ciphers: 'ECDHE-RSA-AES128-GCM-SHA256',
    hostname: 'example.com',
    port: 443
  };

  https.request(options1);
  https.request(options2);
  https.request(options3);
  https.request(options4);
}
