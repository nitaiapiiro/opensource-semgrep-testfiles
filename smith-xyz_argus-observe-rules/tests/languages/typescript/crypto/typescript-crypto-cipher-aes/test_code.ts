import * as crypto from 'crypto';
import * as CryptoJS from 'crypto-js';

function basicAES() {
  const key = Buffer.from('12345678901234567890123456789012');
  const iv = Buffer.from('1234567890123456');
  const data = Buffer.from('message');

  const cipher1 = crypto.createCipher('aes-256-cbc', key);
  const cipher2: crypto.Cipher = crypto.createCipheriv('aes-256-cbc', key, iv);
  const decipher1 = crypto.createDecipher('aes-256-cbc', key);
  const decipher2: crypto.Decipher = crypto.createDecipheriv('aes-256-cbc', key, iv);

  const cipher3: crypto.Cipher = crypto.createCipheriv('aes-256-gcm', key, iv);
  const ciphertext = cipher3.update(data);
  const final = ciphertext + cipher3.final();
}

async function webCryptoAES() {
  const key = await crypto.subtle.generateKey(
    { name: 'AES-GCM', length: 256 },
    true,
    ['encrypt', 'decrypt']
  );

  const key2 = await crypto.subtle.generateKey(
    { name: 'AES-CBC', length: 256 },
    true,
    ['encrypt', 'decrypt']
  );

  const key3 = await crypto.subtle.generateKey(
    { name: 'AES-CTR', length: 256 },
    true,
    ['encrypt', 'decrypt']
  );
}

function cryptoJSUsage() {
  const data = 'message';
  const key = 'secret key';

  const encrypted1 = CryptoJS.AES.encrypt(data, key);
  const encrypted2 = require('crypto-js').AES.encrypt(data, key);
}
