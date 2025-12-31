const crypto = require('crypto');
const { blake2b, blake2s } = require('@noble/hashes');

function blake2Usage() {
  const data = Buffer.from('message');

  const hash1 = crypto.createHash("blake2b512");
  hash1.update(data);
  const result1 = hash1.digest();

  const hash2 = crypto.createHash("blake2s256");
  hash2.update(data);
  const result2 = hash2.digest();

  const hash3 = crypto.createHash("blake2b512").update(data).digest();
  const hash4 = crypto.createHash("blake2s256").update(data).digest();

  const hash5 = blake2b(data);
  const hash6 = blake2s(data);

  const { blake2b: blake2bFunc } = require('@noble/hashes');
  const hash7 = blake2bFunc(data);
}
