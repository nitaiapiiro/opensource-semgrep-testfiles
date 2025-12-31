const crypto = require('crypto');

function pbkdf2Usage() {
  const password = Buffer.from('password');
  const salt = Buffer.from('salt');
  crypto.pbkdf2Sync(password, salt, 10000, 32, 'sha256');
}

function scryptUsage() {
  const password = Buffer.from('password');
  const salt = Buffer.from('salt');
  const keylen = 32;
  crypto.scryptSync(password, salt, keylen);
}

function ecdhUsage() {
  const ecdh = crypto.createECDH('prime256v1');
  ecdh.generateKeys();
  const shared = ecdh.computeSecret(publicKey);
}

async function webCryptoDerivation() {
  const baseKey = await crypto.subtle.importKey(
    'raw',
    Buffer.from('password'),
    'PBKDF2',
    false,
    ['deriveKey']
  );

  const salt = crypto.getRandomValues(new Uint8Array(16));
  const key = await crypto.subtle.deriveKey(
    {
      name: 'PBKDF2',
      salt: salt,
      iterations: 100000,
      hash: 'SHA-256'
    },
    baseKey,
    { name: 'AES-GCM', length: 256 },
    false,
    ['encrypt', 'decrypt']
  );

  const bits = await crypto.subtle.deriveBits(
    {
      name: 'PBKDF2',
      salt: salt,
      iterations: 100000,
      hash: 'SHA-256'
    },
    baseKey,
    256
  );
}
