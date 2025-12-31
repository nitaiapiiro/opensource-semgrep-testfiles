import * as crypto from 'crypto';

function basicPBKDF2() {
  const password = Buffer.from('password');
  const salt = Buffer.from('salt');
  const iterations = 10000;
  const keylen = 32;

  crypto.pbkdf2(password, salt, iterations, keylen, 'sha256', (err, key) => {
    if (err) throw err;
  });

  const key1 = crypto.pbkdf2Sync(password, salt, iterations, keylen, 'sha256');

  crypto.pbkdf2(password, salt, 5000, 64, 'sha512', (err, key) => {
    if (err) throw err;
  });

  const key2 = crypto.pbkdf2Sync(password, salt, 100000, 32, 'sha256');
}

async function webCryptoPBKDF2() {
  const baseKey = await crypto.subtle.importKey(
    'raw',
    new Uint8Array([1, 2, 3]),
    { name: 'PBKDF2' },
    false,
    ['deriveKey']
  );
  const salt = new Uint8Array([4, 5, 6]);
  const key = await crypto.subtle.deriveKey(
    { name: 'PBKDF2', salt: salt, iterations: 10000, hash: 'SHA-256' },
    baseKey,
    { name: 'AES-GCM', length: 256 },
    false,
    ['encrypt', 'decrypt']
  );
}
