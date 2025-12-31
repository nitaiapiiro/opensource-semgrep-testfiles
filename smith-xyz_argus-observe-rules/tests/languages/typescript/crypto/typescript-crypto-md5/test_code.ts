import * as crypto from 'crypto';
import * as CryptoJS from 'crypto-js';

function basicMD5() {
  const data = Buffer.from('test data');

  const hasher1: crypto.Hash = crypto.createHash('md5');
  hasher1.update(data);
  const hash1 = hasher1.digest();

  const hash2 = crypto.createHash('md5').update(data).digest();

  const hash3 = crypto.createHash("md5");
}

function cryptoJSUsage() {
  const data = 'message';

  const hash1 = CryptoJS.MD5(data);
  const hash2 = require('crypto-js').MD5(data);
}
