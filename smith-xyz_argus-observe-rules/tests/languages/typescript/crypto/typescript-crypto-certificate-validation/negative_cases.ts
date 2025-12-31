import * as https from 'https';

function nonCryptoFunction() {
  const message = "This function doesn't use certificate validation";
  console.log(message);
}

function confusingNames() {
  const certLooking = 'not actually a cert';
  const certVar = 12345;
}

function regularRequest() {
  const options = {
    hostname: 'example.com',
    port: 443
  };
  https.request(options);
}
