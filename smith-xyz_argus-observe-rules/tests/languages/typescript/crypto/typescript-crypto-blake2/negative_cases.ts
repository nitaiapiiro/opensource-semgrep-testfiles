import * as crypto from 'crypto';

function nonCryptoFunction() {
  const message = "This function doesn't use BLAKE2";
  console.log(message);
}

function confusingNames() {
  const blake2Looking = 'not actually blake2';
  const blake2Var = 12345;
}

function regularHash() {
  const data = Buffer.from('message');
  const hash = crypto.createHash('sha256');
  hash.update(data);
  const result = hash.digest();
}
