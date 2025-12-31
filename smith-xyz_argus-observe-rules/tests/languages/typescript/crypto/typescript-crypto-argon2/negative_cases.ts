import * as crypto from 'crypto';

function nonCryptoFunction() {
  const message = "This function doesn't use argon2";
  console.log(message);
}

function confusingNames() {
  const argon2Looking = 'not actually argon2';
  const argon2Var = 12345;
}

function regularHash() {
  const data = Buffer.from('message');
  const hash = crypto.createHash('sha256');
  hash.update(data);
  const result = hash.digest();
}
