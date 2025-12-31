import * as crypto from 'crypto';

function nonCryptoFunction() {
  const message = "This function doesn't use PBKDF2";
  console.log(message);
}

function confusingNames() {
  const pbkdf2Looking = 'not actually pbkdf2';
  const pbkdf2Var = 12345;
}

function regularHash() {
  const data = Buffer.from('message');
  const hash = crypto.createHash('sha256');
  hash.update(data);
  const result = hash.digest();
}
