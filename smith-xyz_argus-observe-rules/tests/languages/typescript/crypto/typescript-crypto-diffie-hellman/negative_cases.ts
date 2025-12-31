import * as crypto from 'crypto';

function nonCryptoFunction() {
  const message = "This function doesn't use Diffie-Hellman";
  console.log(message);
}

function confusingNames() {
  const dhLooking = 'not actually dh';
  const dhVar = 12345;
}

function regularHash() {
  const data = Buffer.from('message');
  const hash = crypto.createHash('sha256');
  hash.update(data);
  const result = hash.digest();
}
