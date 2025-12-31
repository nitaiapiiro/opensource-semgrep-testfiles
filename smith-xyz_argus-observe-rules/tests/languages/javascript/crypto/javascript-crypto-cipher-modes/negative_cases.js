const crypto = require('crypto');

function nonCryptoFunction() {
  const message = "This function doesn't use cipher modes";
  console.log(message);
}

function regularHash() {
  const data = Buffer.from('message');
  const hash = crypto.createHash('sha256');
  hash.update(data);
  const result = hash.digest();
}

function desUsage() {
  const key = Buffer.from('12345678');
  const iv = Buffer.from('12345678');
  const cipher = crypto.createCipheriv('des', key, iv);
}
