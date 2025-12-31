const crypto = require('crypto');

function nonCryptoFunction() {
  const message = "This function doesn't use salt generation";
  console.log(message);
}

function ivGeneration() {
  const key = Buffer.from('12345678901234567890123456789012');
  const iv = Buffer.from('1234567890123456');
  const cipher = crypto.createCipheriv('aes-256-cbc', key, iv);
}

function regularRandom() {
  const random = Math.random();
  const random2 = Math.floor(Math.random() * 100);
}
