import * as crypto from 'crypto';

function basicBLAKE2() {
  const data = Buffer.from('test data');

  const hasher1: crypto.Hash = crypto.createHash('blake2b512');
  hasher1.update(data);
  const hash1 = hasher1.digest();

  const hasher2: crypto.Hash = crypto.createHash('blake2s256');
  hasher2.update(data);
  const hash2 = hasher2.digest();

  const hash3 = crypto.createHash('blake2b512').update(data).digest();
  const hash4 = crypto.createHash('blake2s256').update(data).digest();
}
