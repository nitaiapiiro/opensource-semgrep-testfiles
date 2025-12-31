const crypto = require('crypto');

function nonCryptoFunction() {
  const message = "This function doesn't use key derivation";
  console.log(message);
}

function regularHash() {
  const data = Buffer.from('message');
  const hash = crypto.createHash('sha256');
  hash.update(data);
  const result = hash.digest();
}

function keyGeneration() {
  const key = crypto.randomBytes(32);
  const cipher = crypto.createCipheriv('aes-256-cbc', key, Buffer.from('1234567890123456'));
}
