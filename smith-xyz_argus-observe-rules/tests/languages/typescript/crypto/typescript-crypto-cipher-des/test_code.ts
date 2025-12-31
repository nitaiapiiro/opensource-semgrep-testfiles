import * as crypto from 'crypto';
import * as CryptoJS from 'crypto-js';

function basicDES() {
  const key = Buffer.from('12345678');
  const iv = Buffer.from('12345678');
  const data = Buffer.from('message');

  const cipher1 = crypto.createCipher('des', key);
  const cipher2 = crypto.createCipher('des-ede', key);
  const cipher3 = crypto.createCipher('des-ede3', key);

  const cipher4 = crypto.createCipheriv('des', key, iv);
  const cipher5 = crypto.createCipheriv('des-ede', key, iv);
  const cipher6 = crypto.createCipheriv('des-ede3', key, iv);

  const decipher1 = crypto.createDecipher('des', key);
  const decipher2 = crypto.createDecipher('des-ede', key);
  const decipher3 = crypto.createDecipher('des-ede3', key);

  const decipher4 = crypto.createDecipheriv('des', key, iv);
  const decipher5 = crypto.createDecipheriv('des-ede', key, iv);
  const decipher6 = crypto.createDecipheriv('des-ede3', key, iv);
}

function cryptoJSUsage() {
  const data = 'message';
  const key = 'secret key';

  const encrypted1 = CryptoJS.DES.encrypt(data, key);
  const encrypted2 = require('crypto-js').DES.encrypt(data, key);

  const encrypted3 = CryptoJS.TripleDES.encrypt(data, key);
  const encrypted4 = require('crypto-js').TripleDES.encrypt(data, key);
}
