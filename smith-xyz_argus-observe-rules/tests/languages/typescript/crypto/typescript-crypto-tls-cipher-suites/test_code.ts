import * as https from 'https';
import * as tls from 'tls';

function tlsCipherSuites() {
  const options1: tls.TlsOptions = {
    ciphers: 'ECDHE-RSA-AES128-GCM-SHA256',
    hostname: 'example.com',
    port: 443
  };

  const options2: tls.TlsOptions = {
    ciphers: 'ECDHE-RSA-AES256-GCM-SHA384:ECDHE-RSA-AES128-GCM-SHA256',
    hostname: 'example.com',
    port: 443
  };

  const options3: tls.TlsOptions = {
    honorCipherOrder: true,
    ciphers: 'ECDHE-RSA-AES128-GCM-SHA256',
    hostname: 'example.com',
    port: 443
  };

  const options4: tls.TlsOptions = {
    secureOptions: 0x00000004,
    ciphers: 'ECDHE-RSA-AES128-GCM-SHA256',
    hostname: 'example.com',
    port: 443
  };

  const options5 = {
    ciphers: 'ECDHE-RSA-AES128-GCM-SHA256',
    hostname: 'example.com',
    port: 443
  };

  https.request(options1);
  https.request(options2);
  https.request(options3);
  https.request(options4);
  https.request(options5);
}
