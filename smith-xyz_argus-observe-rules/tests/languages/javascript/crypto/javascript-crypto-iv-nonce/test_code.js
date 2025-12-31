const crypto = require('crypto');

function ivGeneration() {
  const key = Buffer.from('12345678901234567890123456789012');

  const iv1 = crypto.randomBytes(16);
  const cipher1 = crypto.createCipheriv('aes-256-cbc', key, iv1);

  const iv2 = crypto.randomBytes(12);
  const cipher2 = crypto.createCipheriv('aes-256-gcm', key, iv2);
}

function nonceGeneration() {
  const key = Buffer.from('12345678901234567890123456789012');

  const nonce1 = crypto.randomBytes(12);
  const cipher1 = crypto.createCipheriv('aes-256-gcm', key, nonce1);

  const nonce2 = crypto.getRandomValues(new Uint8Array(16));
  const cipher2 = crypto.createCipheriv('aes-256-cbc', key, Buffer.from(nonce2));
}

async function webCryptoIV() {
  const key = await crypto.subtle.generateKey(
    { name: 'AES-CBC', length: 256 },
    true,
    ['encrypt', 'decrypt']
  );

  const iv = crypto.getRandomValues(new Uint8Array(16));
  const data = new Uint8Array([1, 2, 3]);
  const encrypted = await crypto.subtle.encrypt(
    { name: 'AES-CBC', iv: iv },
    key,
    data
  );

  const nonce = crypto.getRandomValues(new Uint8Array(12));
  const encrypted2 = await crypto.subtle.encrypt(
    { name: 'AES-GCM', iv: nonce },
    key,
    data
  );
}
