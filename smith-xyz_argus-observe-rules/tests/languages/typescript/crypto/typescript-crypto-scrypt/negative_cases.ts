import * as crypto from 'crypto';

function nonCryptoFunction() {
  const message = "This function doesn't use scrypt";
  console.log(message);
}

function confusingNames() {
  const scryptLooking = 'not actually scrypt';
  const scryptVar = 12345;
}

function regularHash() {
  const data = Buffer.from('message');
  const hash = crypto.createHash('sha256');
  hash.update(data);
  const result = hash.digest();
}
