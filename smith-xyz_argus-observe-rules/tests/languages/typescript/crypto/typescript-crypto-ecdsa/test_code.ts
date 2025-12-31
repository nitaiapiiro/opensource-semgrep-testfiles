import * as crypto from 'crypto';

function basicECDSA() {
  const privateKey = '-----BEGIN PRIVATE KEY-----...';
  const publicKey = '-----BEGIN PUBLIC KEY-----...';
  const data = Buffer.from('message');

  const sign1: crypto.Sign = crypto.createSign('ecdsa-with-SHA256');
  sign1.update(data);
  const signature1 = sign1.sign(privateKey);

  const sign2: crypto.Sign = crypto.createSign('ecdsa-with-SHA512');
  sign2.update(data);
  const signature2 = sign2.sign(privateKey);

  const verify1: crypto.Verify = crypto.createVerify('ecdsa-with-SHA256');
  verify1.update(data);
  const isValid1 = verify1.verify(publicKey, signature1);

  const verify2: crypto.Verify = crypto.createVerify('ecdsa-with-SHA512');
  verify2.update(data);
  const isValid2 = verify2.verify(publicKey, signature2);
}

function ecdhUsage() {
  const ecdh1: crypto.ECDH = crypto.createECDH('prime256v1');
  ecdh1.generateKeys();
  const shared1 = ecdh1.computeSecret(publicKey);

  const ecdh2 = crypto.createECDH('secp256k1');
  const ecdh3 = crypto.createECDH('secp384r1');
  const ecdh4 = crypto.createECDH('secp521r1');
}

async function webCryptoECDSA() {
  const keyPair = await crypto.subtle.generateKey({ name: 'ECDSA', namedCurve: 'P-256' }, true, ['sign', 'verify']);

  const keyPair2 = await crypto.subtle.generateKey({ name: 'ECDSA', namedCurve: 'P-384' }, true, ['sign', 'verify']);

  const keyPair3 = await crypto.subtle.generateKey({ name: 'ECDSA', namedCurve: 'P-521' }, true, ['sign', 'verify']);

  const privateKey = keyPair.privateKey;
  const data = new Uint8Array([1, 2, 3]);
  await crypto.subtle.sign({ name: 'ECDSA', hash: 'SHA-256' }, privateKey, data);

  const publicKey = keyPair.publicKey;
  const signature = new Uint8Array([1, 2, 3]);
  await crypto.subtle.verify({ name: 'ECDSA', hash: 'SHA-256' }, publicKey, signature, data);
}
