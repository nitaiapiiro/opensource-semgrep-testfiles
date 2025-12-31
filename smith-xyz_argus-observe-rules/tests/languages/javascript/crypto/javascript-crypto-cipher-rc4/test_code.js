const crypto = require('crypto');
const CryptoJS = require('crypto-js');

function basicRC4() {
  const key = Buffer.from('secret key');
  const data = Buffer.from('message');

  const cipher1 = crypto.createCipher('rc4', key);
  const cipher2 = crypto.createCipher('rc4-40', key);
  const cipher3 = crypto.createCipher('rc4-hmac-md5', key);

  const decipher1 = crypto.createDecipher('rc4', key);
  const decipher2 = crypto.createDecipher('rc4-40', key);
  const decipher3 = crypto.createDecipher('rc4-hmac-md5', key);
}

function cryptoJSUsage() {
  const data = 'message';
  const key = 'secret key';

  const encrypted1 = CryptoJS.RC4.encrypt(data, key);
  const encrypted2 = require('crypto-js').RC4.encrypt(data, key);
}
