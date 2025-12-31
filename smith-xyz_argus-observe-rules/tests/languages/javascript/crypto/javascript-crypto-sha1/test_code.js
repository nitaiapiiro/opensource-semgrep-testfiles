const crypto = require('crypto');
const CryptoJS = require('crypto-js');

function basicSHA1() {
  const data = Buffer.from('test data');

  const hasher1 = crypto.createHash('sha1');
  hasher1.update(data);
  const hash1 = hasher1.digest();

  const hash2 = crypto.createHash('sha1').update(data).digest();
}

function cryptoJSUsage() {
  const data = 'test data';

  const hash1 = CryptoJS.SHA1(data);
  const hash2 = require('crypto-js').SHA1(data);
}
