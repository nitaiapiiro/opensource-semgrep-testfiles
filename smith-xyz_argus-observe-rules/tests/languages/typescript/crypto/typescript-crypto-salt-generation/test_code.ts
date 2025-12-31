import * as crypto from 'crypto';
import * as bcrypt from 'bcrypt';

function saltGeneration() {
  const salt1: Buffer = crypto.randomBytes(16);
  crypto.pbkdf2Sync(Buffer.from('password'), salt1, 10000, 32, 'sha256');

  const salt2: Buffer = crypto.randomBytes(32);
  crypto.scryptSync(Buffer.from('password'), salt2, 32);

  const salt3 = crypto.getRandomValues(new Uint8Array(16));
  crypto.pbkdf2Sync(Buffer.from('password'), Buffer.from(salt3), 10000, 32, 'sha256');

  const saltLength = 16;
  const salt4 = crypto.randomBytes(saltLength);
  bcrypt.hashSync('password', 10);
}

async function webCryptoSalt() {
  const salt = await crypto.subtle.generateKey(
    { name: 'AES-GCM', length: 256 },
    true,
    ['encrypt', 'decrypt']
  );
  await crypto.subtle.deriveKey(
    { name: 'PBKDF2', salt: salt, iterations: 10000, hash: 'SHA-256' },
    await crypto.subtle.importKey('raw', new Uint8Array([1, 2, 3]), { name: 'PBKDF2' }, false, ['deriveKey']),
    { name: 'AES-GCM', length: 256 },
    false,
    ['encrypt', 'decrypt']
  );
}
