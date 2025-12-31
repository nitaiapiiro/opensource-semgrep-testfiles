const https = require('https');

function certificateValidation() {
  const options1 = {
    rejectUnauthorized: true,
    hostname: 'example.com',
    port: 443
  };

  const options2 = {
    rejectUnauthorized: false,
    hostname: 'example.com',
    port: 443
  };

  const options3 = {
    ca: '-----BEGIN CERTIFICATE-----...',
    hostname: 'example.com',
    port: 443
  };

  const options4 = {
    cert: '-----BEGIN CERTIFICATE-----...',
    key: '-----BEGIN PRIVATE KEY-----...',
    hostname: 'example.com',
    port: 443
  };

  const options5 = {
    checkServerIdentity: function(hostname, cert) {
      return undefined;
    },
    hostname: 'example.com',
    port: 443
  };

  const agent1 = new https.Agent({
    rejectUnauthorized: true
  });

  const agent2 = new https.Agent({
    rejectUnauthorized: false
  });

  const agent3 = new https.Agent({
    ca: '-----BEGIN CERTIFICATE-----...'
  });

  https.request(options1);
  https.request(options2);
  https.request(options3);
  https.request(options4);
  https.request(options5);
}
