const crypto = require('crypto');

function nonCryptoFunction() {
  const message = "This function doesn't use ECDSA";
  console.log(message);
}

function rsaUsage() {
  const sign = crypto.createSign('RSA-SHA256');
  sign.update(Buffer.from('message'));
  const signature = sign.sign('private key');
}

function regularHash() {
  const data = Buffer.from('message');
  const hash = crypto.createHash('sha256');
  hash.update(data);
  const result = hash.digest();
}
