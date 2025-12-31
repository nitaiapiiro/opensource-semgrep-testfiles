import * as https from 'https';
import * as tls from 'tls';

function certificateValidation() {
  const options1: tls.TlsOptions = {
    rejectUnauthorized: true,
    hostname: 'example.com',
    port: 443
  };

  const options2: tls.TlsOptions = {
    rejectUnauthorized: false,
    hostname: 'example.com',
    port: 443
  };

  const options3: tls.TlsOptions = {
    ca: '-----BEGIN CERTIFICATE-----...',
    hostname: 'example.com',
    port: 443
  };

  const options4: tls.TlsOptions = {
    cert: '-----BEGIN CERTIFICATE-----...',
    key: '-----BEGIN PRIVATE KEY-----...',
    hostname: 'example.com',
    port: 443
  };

  const options5: tls.TlsOptions = {
    checkServerIdentity: function(hostname, cert) {
      return undefined;
    },
    hostname: 'example.com',
    port: 443
  };

  const agent1: https.Agent = new https.Agent({
    rejectUnauthorized: true
  });

  const agent2: https.Agent = new https.Agent({
    rejectUnauthorized: false
  });

  const agent3: https.Agent = new https.Agent({
    ca: '-----BEGIN CERTIFICATE-----...'
  });

  const options6 = {
    rejectUnauthorized: true,
    hostname: 'example.com',
    port: 443
  };

  const agent4 = new https.Agent({
    rejectUnauthorized: true
  });

  https.request(options1);
  https.request(options2);
  https.request(options3);
  https.request(options4);
  https.request(options5);
  https.request(options6);
}
