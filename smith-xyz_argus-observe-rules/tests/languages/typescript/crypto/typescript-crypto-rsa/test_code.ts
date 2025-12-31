import * as crypto from 'crypto';
import * as forge from 'node-forge';

function basicRSA() {
  const publicKey = '-----BEGIN PUBLIC KEY-----...';
  const privateKey = '-----BEGIN PRIVATE KEY-----...';
  const data = Buffer.from('message');

  const ciphertext = crypto.publicEncrypt(publicKey, data);
  const plaintext = crypto.privateDecrypt(privateKey, ciphertext);

  const ciphertext2 = crypto.privateEncrypt(privateKey, data);
  const plaintext2 = crypto.publicDecrypt(publicKey, ciphertext2);
}

function rsaSigning() {
  const privateKey = '-----BEGIN PRIVATE KEY-----...';
  const publicKey = '-----BEGIN PUBLIC KEY-----...';
  const data = Buffer.from('message');

  const sign: crypto.Sign = crypto.createSign('RSA-SHA256');
  sign.update(data);
  const signature = sign.sign(privateKey);

  const sign2: crypto.Sign = crypto.createSign('RSA-SHA512');
  sign2.update(data);
  const signature2 = sign2.sign(privateKey);

  const verify: crypto.Verify = crypto.createVerify('RSA-SHA256');
  verify.update(data);
  const isValid = verify.verify(publicKey, signature);

  const verify2: crypto.Verify = crypto.createVerify('RSA-SHA512');
  verify2.update(data);
  const isValid2 = verify2.verify(publicKey, signature2);
}

async function webCryptoRSA() {
  const key = await crypto.subtle.generateKey(
    { name: 'RSA-OAEP', modulusLength: 2048, publicExponent: new Uint8Array([1, 0, 1]), hash: 'SHA-256' },
    true,
    ['encrypt', 'decrypt']
  );

  const key2 = await crypto.subtle.generateKey(
    { name: 'RSA-PSS', modulusLength: 2048, publicExponent: new Uint8Array([1, 0, 1]), hash: 'SHA-256' },
    true,
    ['sign', 'verify']
  );
}

function forgeRSA() {
  const keyPair = forge.pki.rsa.generateKeyPair(2048);
  const publicKey = keyPair.publicKey;
  const privateKey = keyPair.privateKey;
  const data = 'message';

  const encrypted = forge.pki.rsa.encrypt(data, publicKey);
  const decrypted = forge.pki.rsa.decrypt(encrypted, privateKey);
}
