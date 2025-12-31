import * as crypto from 'crypto';

function nonCryptoFunction() {
  const message = "This function doesn't use cryptographic key lengths";
  console.log(message);
}

function regularBuffer() {
  const buffer = Buffer.alloc(1024);
  const data = buffer.toString();
}

function regularArray() {
  const array = new Uint8Array(100);
  const length = array.length;
}

function nonCryptoKeyGeneration() {
  const randomKey = Math.random().toString(36);
  const hashKey = crypto.createHash('sha256').update('data').digest();
}

function keyWithoutLength() {
  const key = await crypto.subtle.generateKey(
    { name: 'Ed25519' },
    true,
    ['sign', 'verify']
  );
}
