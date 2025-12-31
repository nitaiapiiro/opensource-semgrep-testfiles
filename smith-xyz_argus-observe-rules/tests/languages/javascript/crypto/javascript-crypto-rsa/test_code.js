const crypto = require('crypto');
const forge = require('node-forge');

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

  const sign = crypto.createSign('RSA-SHA256');
  sign.update(data);
  const signature = sign.sign(privateKey);

  const sign2 = crypto.createSign('RSA-SHA512');
  sign2.update(data);
  const signature2 = sign2.sign(privateKey);

  const verify = crypto.createVerify('RSA-SHA256');
  verify.update(data);
  const isValid = verify.verify(publicKey, signature);

  const verify2 = crypto.createVerify('RSA-SHA512');
  verify2.update(data);
  const isValid2 = verify2.verify(publicKey, signature2);
}

function forgeRSA() {
  const keyPair = forge.pki.rsa.generateKeyPair(2048);
  const publicKey = keyPair.publicKey;
  const privateKey = keyPair.privateKey;
  const data = 'message';

  const encrypted = forge.pki.rsa.encrypt(data, publicKey);
  const decrypted = forge.pki.rsa.decrypt(encrypted, privateKey);
}
