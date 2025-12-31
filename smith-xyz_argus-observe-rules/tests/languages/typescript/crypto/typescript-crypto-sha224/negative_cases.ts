import * as crypto from 'crypto';

function nonCryptoFunction() {
  const message = "This function doesn't use SHA224";
  console.log(message);
}

function confusingNames() {
  const sha224Looking = 'not actually sha224';
  const sha224Var = 12345;
}

function regularHash() {
  const data = Buffer.from('message');
  const hash = crypto.createHash('sha256');
  hash.update(data);
  const result = hash.digest();
}
