import * as crypto from 'crypto';
import * as CryptoJS from 'crypto-js';

function basicSHA3() {
  const data = Buffer.from('test data');

  const hasher1: crypto.Hash = crypto.createHash('sha3-224');
  const hasher2: crypto.Hash = crypto.createHash('sha3-256');
  const hasher3: crypto.Hash = crypto.createHash('sha3-384');
  const hasher4: crypto.Hash = crypto.createHash('sha3-512');

  hasher1.update(data);
  const hash1 = hasher1.digest();

  const hash2 = crypto.createHash('sha3-256').update(data).digest();
}

function cryptoJSUsage() {
  const data = 'test data';

  const hash1 = CryptoJS.SHA3(data);
  const hash2 = require('crypto-js').SHA3(data);
}
