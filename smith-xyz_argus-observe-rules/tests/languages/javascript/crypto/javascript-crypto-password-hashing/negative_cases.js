const crypto = require('crypto');

function nonCryptoFunction() {
  const message = "This function doesn't use password hashing";
  console.log(message);
}

function regularHash() {
  const data = Buffer.from('message');
  const hash = crypto.createHash('sha256');
  hash.update(data);
  const result = hash.digest();
}
