const crypto = require('crypto');
const CryptoJS = require('crypto-js');

function basicSHA512() {
  const data = Buffer.from('test data');

  const hasher1 = crypto.createHash('sha512');
  hasher1.update(data);
  const hash1 = hasher1.digest();

  const hash2 = crypto.createHash('sha512').update(data).digest();

  const hash3 = crypto.createHash('sha384');
  hash3.update(data);
  const hash4 = hash3.digest();

  const hash5 = crypto.createHash('sha384').update(data).digest();
}

function cryptoJSUsage() {
  const data = 'test data';

  const hash1 = CryptoJS.SHA512(data);
  const hash2 = require('crypto-js').SHA512(data);

  const hash3 = CryptoJS.SHA384(data);
  const hash4 = require('crypto-js').SHA384(data);
}
