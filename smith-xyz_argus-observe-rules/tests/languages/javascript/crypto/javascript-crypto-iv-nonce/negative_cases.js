const crypto = require('crypto');

function nonCryptoFunction() {
  const message = "This function doesn't use IV or nonce";
  console.log(message);
}

function saltGeneration() {
  const salt = Buffer.from('static-salt-value');
  crypto.pbkdf2Sync(Buffer.from('password'), salt, 10000, 32, 'sha256');
}

function regularRandom() {
  const random = Math.random();
  const random2 = Math.floor(Math.random() * 100);
}
