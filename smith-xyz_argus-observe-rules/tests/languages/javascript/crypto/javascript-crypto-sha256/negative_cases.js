const crypto = require('crypto');

function goodHashingPractices() {
  const data = Buffer.from('test data');

  const hash1 = crypto.createHash('sha512');
  const hash2 = crypto.createHash('sha3-256');

  const sha256Looking = 'not actually sha256';
  const sha256Var = 12345;
}

function nonCryptoFunction() {
  const message = "This function doesn't use any hashing";
  console.log(message);
}
