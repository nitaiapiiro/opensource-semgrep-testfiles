const crypto = require('crypto');

function nonCryptoFunction() {
  const message = "This function doesn't use Web Crypto API";
  console.log(message);
}

function nodeCrypto() {
  const data = Buffer.from('message');
  const hash = crypto.createHash('sha256');
  hash.update(data);
  const result = hash.digest();
}

function regularCrypto() {
  const key = Buffer.from('12345678901234567890123456789012');
  const iv = Buffer.from('1234567890123456');
  const cipher = crypto.createCipheriv('aes-256-cbc', key, iv);
}
