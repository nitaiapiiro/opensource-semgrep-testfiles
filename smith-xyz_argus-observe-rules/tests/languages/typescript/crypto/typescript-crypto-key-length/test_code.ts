import * as crypto from 'crypto';

async function aesKeyLengths() {
  const key128 = await crypto.subtle.generateKey(
    { name: 'AES-GCM', length: 128 },
    true,
    ['encrypt', 'decrypt']
  );

  const key192 = await crypto.subtle.generateKey(
    { name: 'AES-CBC', length: 192 },
    true,
    ['encrypt', 'decrypt']
  );

  const key256 = await crypto.subtle.generateKey(
    { name: 'AES-CTR', length: 256 },
    true,
    ['encrypt', 'decrypt']
  );

  const keyWrap = await crypto.subtle.generateKey(
    { name: 'AES-KW', length: 256 },
    true,
    ['wrapKey', 'unwrapKey']
  );
}

async function rsaKeyLengths() {
  const key2048 = await crypto.subtle.generateKey(
    { name: 'RSA-OAEP', modulusLength: 2048, publicExponent: new Uint8Array([1, 0, 1]), hash: 'SHA-256' },
    true,
    ['encrypt', 'decrypt']
  );

  const key3072 = await crypto.subtle.generateKey(
    { name: 'RSA-PSS', modulusLength: 3072, publicExponent: new Uint8Array([1, 0, 1]), hash: 'SHA-256' },
    true,
    ['sign', 'verify']
  );

  const key4096 = await crypto.subtle.generateKey(
    { name: 'RSASSA-PKCS1-v1_5', modulusLength: 4096, publicExponent: new Uint8Array([1, 0, 1]), hash: 'SHA-256' },
    true,
    ['sign', 'verify']
  );
}

function ecdsaCurves() {
  const keyP256 = crypto.createECDH('prime256v1');
  const keyP384 = crypto.createECDH('secp384r1');
  const keyP521 = crypto.createECDH('secp521r1');
}

async function keyDerivationLengths() {
  const password = Buffer.from('password');
  const salt = crypto.randomBytes(16);

  const key1 = crypto.pbkdf2Sync(password, salt, 100000, 32, 'sha256');
  const key2 = crypto.scryptSync(password, salt, 64, { N: 16384, r: 8, p: 1 });

  const baseKey = await crypto.subtle.importKey(
    'raw',
    password,
    { name: 'PBKDF2' },
    false,
    ['deriveKey']
  );

  const derivedKey = await crypto.subtle.deriveKey(
    { name: 'PBKDF2', salt: salt, iterations: 100000, hash: 'SHA-256' },
    baseKey,
    { name: 'AES-GCM', length: 256 },
    false,
    ['encrypt', 'decrypt']
  );
}

function bufferKeyLengths() {
  const key16 = Buffer.alloc(16);
  const iv = crypto.randomBytes(12);
  crypto.createCipheriv('aes-128-gcm', key16, iv);

  const key32 = Buffer.alloc(32);
  crypto.createCipheriv('aes-256-cbc', key32, iv);
}

async function uint8ArrayKeyLengths() {
  const key16 = new Uint8Array(16);
  await crypto.subtle.importKey('raw', key16, { name: 'AES-GCM' }, false, ['encrypt', 'decrypt']);

  const key24 = new Uint8Array(24);
  await crypto.subtle.importKey('raw', key24, { name: 'AES-GCM' }, false, ['encrypt', 'decrypt']);
}
