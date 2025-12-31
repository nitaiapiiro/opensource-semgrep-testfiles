import * as crypto from 'crypto';

function nonCryptoFunction() {
  const message = "This function doesn't use RC4";
  console.log(message);
}

function confusingNames() {
  const rc4Looking = 'not actually rc4';
  const rc4Var = 12345;
}

function regularHash() {
  const data = Buffer.from('message');
  const hash = crypto.createHash('sha256');
  hash.update(data);
  const result = hash.digest();
}
