const https = require('https');

function nonCryptoFunction() {
  const message = "This function doesn't use TLS version configuration";
  console.log(message);
}

function tlsCipherSuites() {
  const options = {
    ciphers: 'ECDHE-RSA-AES128-GCM-SHA256',
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
