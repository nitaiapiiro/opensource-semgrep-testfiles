import * as crypto from 'crypto';
import * as CryptoJS from 'crypto-js';

function basicHMAC() {
  const key = Buffer.from('secret key');
  const data = Buffer.from('message');

  const hmac1: crypto.Hmac = crypto.createHmac('sha256', key);
  hmac1.update(data);
  const result1 = hmac1.digest();

  const hmac2 = crypto.createHmac('sha512', key);
  const hmac3 = crypto.createHmac('sha1', key);
  const hmac4 = crypto.createHmac('md5', key);
  const hmac5 = crypto.createHmac('sha384', key);

  const hmac6 = crypto.createHmac('sha256', key).update(data).digest();
}

async function webCryptoHMAC() {
  const key = await crypto.subtle.generateKey(
    { name: 'HMAC', hash: 'SHA-256' },
    true,
    ['sign', 'verify']
  );
  const data = new Uint8Array([1, 2, 3]);
  const signature = await crypto.subtle.sign(
    { name: 'HMAC', hash: 'SHA-256' },
    key,
    data
  );
}

function cryptoJSUsage() {
  const data = 'message';
  const key = 'secret key';

  const hmac1 = CryptoJS.HmacSHA256(data, key);
  const hmac2 = require('crypto-js').HmacSHA256(data, key);

  const hmac3 = CryptoJS.HmacSHA512(data, key);
  const hmac4 = require('crypto-js').HmacSHA512(data, key);

  const hmac5 = CryptoJS.HmacSHA1(data, key);
  const hmac6 = require('crypto-js').HmacSHA1(data, key);
}
