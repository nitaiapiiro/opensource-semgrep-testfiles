import * as crypto from 'crypto';

function basicRandom() {
  const bytes1: Buffer = crypto.randomBytes(32);
  const bytes2 = crypto.getRandomValues(new Uint8Array(16));

  const bytes3 = crypto.randomBytes(64);
  const bytes4 = crypto.getRandomValues(new Uint8Array(32));
}

async function webCryptoRandom() {
  const key = await crypto.subtle.generateKey(
    { name: 'AES-GCM', length: 256 },
    true,
    ['encrypt', 'decrypt']
  );

  const iv = new Uint8Array(12);
  crypto.getRandomValues(iv);
}
