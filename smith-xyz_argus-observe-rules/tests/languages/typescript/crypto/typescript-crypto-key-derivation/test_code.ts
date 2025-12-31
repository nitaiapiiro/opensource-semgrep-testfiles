import * as crypto from 'crypto';

function basicKeyDerivation() {
  const password = Buffer.from('password');
  const salt = Buffer.from('salt');
  const keylen = 32;

  crypto.pbkdf2(password, salt, 10000, keylen, 'sha256', () => {});
  crypto.pbkdf2Sync(password, salt, 10000, keylen, 'sha256');

  crypto.scrypt(password, salt, keylen, {}, () => {});
  crypto.scryptSync(password, salt, keylen);

  const ecdh: crypto.ECDH = crypto.createECDH('prime256v1');
  ecdh.generateKeys();
  const shared = ecdh.computeSecret(Buffer.from('public key'));
}

async function webCryptoKeyDerivation() {
  const baseKey = await crypto.subtle.importKey(
    'raw',
    new Uint8Array([1, 2, 3]),
    { name: 'PBKDF2' },
    false,
    ['deriveKey']
  );
  const salt = new Uint8Array([4, 5, 6]);
  await crypto.subtle.deriveKey(
    { name: 'PBKDF2', salt: salt, iterations: 10000, hash: 'SHA-256' },
    baseKey,
    { name: 'AES-GCM', length: 256 },
    false,
    ['encrypt', 'decrypt']
  );

  const info = new Uint8Array([7, 8, 9]);
  await crypto.subtle.deriveKey(
    { name: 'HKDF', hash: 'SHA-256', salt: salt, info: info },
    baseKey,
    { name: 'AES-GCM', length: 256 },
    false,
    ['encrypt', 'decrypt']
  );
}
