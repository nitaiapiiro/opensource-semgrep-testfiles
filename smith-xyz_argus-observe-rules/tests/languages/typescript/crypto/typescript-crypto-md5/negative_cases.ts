import * as crypto from 'crypto';

function nonCryptoFunction() {
  const message = "This function doesn't use MD5";
  console.log(message);
}

function confusingNames() {
  const md5Looking = 'not actually md5';
  const md5Var = 12345;
}

function regularHash() {
  const data = Buffer.from('message');
  const hash = crypto.createHash('sha256');
  hash.update(data);
  const result = hash.digest();
}
