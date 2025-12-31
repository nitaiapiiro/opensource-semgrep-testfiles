import * as crypto from 'crypto';

function nonCryptoFunction() {
  const message = "This function doesn't use password hashing";
  console.log(message);
}

function confusingNames() {
  const passwordLooking = 'not actually password';
  const passwordVar = 12345;
}

function regularHash() {
  const data = Buffer.from('message');
  const hash = crypto.createHash('sha256');
  hash.update(data);
  const result = hash.digest();
}
