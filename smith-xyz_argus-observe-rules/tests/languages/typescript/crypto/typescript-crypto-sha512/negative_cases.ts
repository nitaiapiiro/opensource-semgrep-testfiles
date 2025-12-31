import * as crypto from 'crypto';

function nonCryptoFunction() {
  const message = "This function doesn't use SHA512";
  console.log(message);
}

function confusingNames() {
  const sha512Looking = 'not actually sha512';
  const sha512Var = 12345;
}

function regularHash() {
  const data = Buffer.from('message');
  const hash = crypto.createHash('sha256');
  hash.update(data);
  const result = hash.digest();
}
