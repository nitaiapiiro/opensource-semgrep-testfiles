import * as crypto from 'crypto';

function nonCryptoFunction() {
  const message = "This function doesn't use SHA1";
  console.log(message);
}

function confusingNames() {
  const sha1Looking = 'not actually sha1';
  const sha1Var = 12345;
}

function regularHash() {
  const data = Buffer.from('message');
  const hash = crypto.createHash('sha256');
  hash.update(data);
  const result = hash.digest();
}
