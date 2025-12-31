const https = require('https');

function nonCryptoFunction() {
  const message = "This function doesn't use certificate validation";
  console.log(message);
}

function regularRequest() {
  const options = {
    hostname: 'example.com',
    port: 443
  };
  https.request(options);
}

function tlsCipherSuites() {
  const options = {
    ciphers: 'ECDHE-RSA-AES128-GCM-SHA256',
    hostname: 'example.com',
    port: 443
  };
  https.request(options);
}
