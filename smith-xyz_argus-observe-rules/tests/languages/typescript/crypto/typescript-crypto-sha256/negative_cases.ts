import * as crypto from 'crypto';

function nonCryptoFunction() {
  const message = "This function doesn't use SHA256";
  console.log(message);
}

function confusingNames() {
  const sha256Looking = 'not actually sha256';
  const sha256Var = 12345;
}

function regularHash() {
  const data = Buffer.from('message');
  const hash = crypto.createHash('sha1');
  hash.update(data);
  const result = hash.digest();
}
