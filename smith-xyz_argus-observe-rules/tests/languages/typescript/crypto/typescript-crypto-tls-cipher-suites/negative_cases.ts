import * as https from 'https';

function nonCryptoFunction() {
  const message = "This function doesn't use TLS cipher suites";
  console.log(message);
}

function confusingNames() {
  const cipherLooking = 'not actually a cipher';
  const cipherVar = 12345;
}

function regularRequest() {
  const options = {
    hostname: 'example.com',
    port: 443
  };
  https.request(options);
}
