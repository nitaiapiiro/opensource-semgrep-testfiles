const https = require('https');

function nonCryptoFunction() {
  const message = "This function doesn't use TLS cipher suites";
  console.log(message);
}

function tlsVersionOnly() {
  const options = {
    minVersion: 'TLSv1.2',
    maxVersion: 'TLSv1.3',
    hostname: 'example.com',
    port: 443
  };
  https.request(options);
}

function regularRequest() {
  const options = {
    hostname: 'example.com',
    port: 443
  };
  https.request(options);
}
