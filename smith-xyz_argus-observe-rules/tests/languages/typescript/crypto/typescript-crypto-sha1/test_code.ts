import * as crypto from 'crypto';
import * as CryptoJS from 'crypto-js';

function basicSHA1() {
  const data = Buffer.from('test data');

  const hasher1: crypto.Hash = crypto.createHash('sha1');
  hasher1.update(data);
  const hash1 = hasher1.digest();

  const hash2 = crypto.createHash('sha1').update(data).digest();

  const hash3 = crypto.createHash("sha1");
}

function cryptoJSUsage() {
  const data = 'message';

  const hash1 = CryptoJS.SHA1(data);
  const hash2 = require('crypto-js').SHA1(data);
}
