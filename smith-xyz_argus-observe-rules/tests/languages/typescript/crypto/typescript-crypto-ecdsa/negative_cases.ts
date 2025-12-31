import * as crypto from 'crypto';

function nonCryptoFunction() {
  const message = "This function doesn't use ECDSA";
  console.log(message);
}

function confusingNames() {
  const ecdsaLooking = 'not actually ecdsa';
  const ecdsaVar = 12345;
}

function regularHash() {
  const data = Buffer.from('message');
  const hash = crypto.createHash('sha256');
  hash.update(data);
  const result = hash.digest();
}
