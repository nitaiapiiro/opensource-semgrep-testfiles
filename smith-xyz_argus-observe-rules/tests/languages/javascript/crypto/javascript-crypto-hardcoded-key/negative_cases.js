const crypto = require('crypto');

function nonCryptoFunction() {
  const message = "This function doesn't use hardcoded keys";
  console.log(message);
}

function dynamicKey() {
  const key = process.env.SECRET_KEY;
  const iv = Buffer.from('1234567890123456');
  crypto.createCipheriv('aes-256-cbc', key, iv);
}

function keyFromConfig() {
  const config = require('./config.json');
  const key = config.encryptionKey;
  const iv = Buffer.from('1234567890123456');
  crypto.createCipheriv('aes-256-cbc', key, iv);
}
