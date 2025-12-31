const crypto = require('crypto');

function nonCryptoFunction() {
  const message = "This function doesn't use PBKDF2";
  console.log(message);
}

function regularHash() {
  const data = Buffer.from('message');
  const hash = crypto.createHash('sha256');
  hash.update(data);
  const result = hash.digest();
}

function scryptUsage() {
  const password = Buffer.from('password');
  const salt = Buffer.from('salt');
  const keylen = 32;
  const key = crypto.scryptSync(password, salt, keylen);
}
