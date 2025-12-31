import * as https from 'https';
import * as tls from 'tls';

function tlsVersions() {
  const options1: tls.TlsOptions = {
    secureProtocol: 'TLSv1_method',
    hostname: 'example.com',
    port: 443
  };

  const options2: tls.TlsOptions = {
    secureProtocol: 'TLSv1_1_method',
    hostname: 'example.com',
    port: 443
  };

  const options3: tls.TlsOptions = {
    secureProtocol: 'TLSv1_2_method',
    hostname: 'example.com',
    port: 443
  };

  const options4: tls.TlsOptions = {
    minVersion: 'TLSv1.2',
    maxVersion: 'TLSv1.3',
    hostname: 'example.com',
    port: 443
  };

  const options5 = {
    minVersion: 'TLSv1.2',
    hostname: 'example.com',
    port: 443
  };

  https.request(options1);
  https.request(options2);
  https.request(options3);
  https.request(options4);
  https.request(options5);
}
