const crypto = require('crypto');

function nonCryptoFunction() {
  const message = "This function doesn't use scrypt";
  console.log(message);
}

function pbkdf2Usage() {
  const password = Buffer.from('password');
  const salt = Buffer.from('salt');
  const key = crypto.pbkdf2Sync(password, salt, 10000, 32, 'sha256');
}

function regularHash() {
  const data = Buffer.from('message');
  const hash = crypto.createHash('sha256');
  hash.update(data);
  const result = hash.digest();
}
