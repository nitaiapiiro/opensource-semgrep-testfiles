const crypto = require('crypto');

function goodHashingPractices() {
  const data = Buffer.from('test data');

  const hash1 = crypto.createHash('sha256');
  const hash2 = crypto.createHash('sha3-256');

  const sha512Looking = 'not actually sha512';
  const sha384Var = 12345;
}

function nonCryptoFunction() {
  const message = "This function doesn't use any hashing";
  console.log(message);
}
