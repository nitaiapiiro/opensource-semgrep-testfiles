import * as crypto from 'crypto';

function nonCryptoFunction() {
  const message = "This function doesn't use SHA3";
  console.log(message);
}

function confusingNames() {
  const sha3Looking = 'not actually sha3';
  const sha3Var = 12345;
}

function regularHash() {
  const data = Buffer.from('message');
  const hash = crypto.createHash('sha256');
  hash.update(data);
  const result = hash.digest();
}
