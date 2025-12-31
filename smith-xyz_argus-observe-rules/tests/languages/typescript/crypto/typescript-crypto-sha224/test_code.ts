import * as crypto from 'crypto';

function basicSHA224() {
  const data = Buffer.from('test data');

  const hasher1: crypto.Hash = crypto.createHash('sha224');
  hasher1.update(data);
  const hash1 = hasher1.digest();

  const hash2 = crypto.createHash('sha224').update(data).digest();

  const hash3 = crypto.createHash("sha224");
}
