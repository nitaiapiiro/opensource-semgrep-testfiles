const crypto = require('crypto');
const CryptoJS = require('crypto-js');

function basicMD5() {
  const data = Buffer.from('test data');

  const hasher1 = crypto.createHash('md5');
  hasher1.update(data);
  const hash1 = hasher1.digest();

  const hash2 = crypto.createHash('md5').update(data).digest();
}

function cryptoJSUsage() {
  const data = 'test data';

  const hash1 = CryptoJS.MD5(data);
  const hash2 = require('crypto-js').MD5(data);
}
