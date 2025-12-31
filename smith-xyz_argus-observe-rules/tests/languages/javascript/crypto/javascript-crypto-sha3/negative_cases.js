const crypto = require('crypto');

function goodHashingPractices() {
  const data = Buffer.from('test data');

  const hash1 = crypto.createHash('sha256');
  const hash2 = crypto.createHash('sha512');

  const sha3Looking = 'not actually sha3';
  const sha3Var = 12345;
}

function nonCryptoFunction() {
  const message = "This function doesn't use any hashing";
  console.log(message);
}
