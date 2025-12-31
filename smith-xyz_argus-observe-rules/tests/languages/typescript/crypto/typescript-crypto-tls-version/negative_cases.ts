import * as https from 'https';

function nonCryptoFunction() {
  const message = "This function doesn't use TLS";
  console.log(message);
}

function confusingNames() {
  const tlsLooking = 'not actually tls';
  const tlsVar = 12345;
}

function regularRequest() {
  const options = {
    hostname: 'example.com',
    port: 443
  };
  https.request(options);
}
