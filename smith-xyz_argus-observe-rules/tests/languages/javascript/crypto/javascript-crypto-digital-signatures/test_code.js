const crypto = require('crypto');

function basicSignatures() {
  const privateKey = '-----BEGIN PRIVATE KEY-----...';
  const publicKey = '-----BEGIN PUBLIC KEY-----...';
  const data = Buffer.from('message');

  const sign1 = crypto.createSign('RSA-SHA256');
  sign1.update(data);
  const signature1 = sign1.sign(privateKey);

  const sign2 = crypto.createSign('RSA-SHA512');
  sign2.update(data);
  const signature2 = sign2.sign(privateKey);

  const sign3 = crypto.createSign('ecdsa-with-SHA256');
  sign3.update(data);
  const signature3 = sign3.sign(privateKey);

  const sign4 = crypto.createSign('ecdsa-with-SHA512');
  sign4.update(data);
  const signature4 = sign4.sign(privateKey);

  const verify1 = crypto.createVerify('RSA-SHA256');
  verify1.update(data);
  const isValid1 = verify1.verify(publicKey, signature1);

  const verify2 = crypto.createVerify('RSA-SHA512');
  verify2.update(data);
  const isValid2 = verify2.verify(publicKey, signature2);

  const verify3 = crypto.createVerify('ecdsa-with-SHA256');
  verify3.update(data);
  const isValid3 = verify3.verify(publicKey, signature3);

  const verify4 = crypto.createVerify('ecdsa-with-SHA512');
  verify4.update(data);
  const isValid4 = verify4.verify(publicKey, signature4);
}

async function webCryptoSignatures() {
  const privateKey = await crypto.subtle.generateKey(
    { name: 'RSASSA-PKCS1-v1_5', modulusLength: 2048, publicExponent: new Uint8Array([1, 0, 1]), hash: 'SHA-256' },
    true,
    ['sign']
  );

  const data = new Uint8Array([1, 2, 3]);

  const signature1 = await crypto.subtle.sign(
    { name: 'RSASSA-PKCS1-v1_5', hash: 'SHA-256' },
    privateKey.privateKey,
    data
  );

  const signature2 = await crypto.subtle.sign(
    { name: 'RSA-PSS', hash: 'SHA-256' },
    privateKey.privateKey,
    data
  );

  const signature3 = await crypto.subtle.sign(
    { name: 'ECDSA', hash: 'SHA-256' },
    privateKey.privateKey,
    data
  );

  const isValid1 = await crypto.subtle.verify(
    { name: 'RSASSA-PKCS1-v1_5', hash: 'SHA-256' },
    privateKey.publicKey,
    signature1,
    data
  );

  const isValid2 = await crypto.subtle.verify(
    { name: 'RSA-PSS', hash: 'SHA-256' },
    privateKey.publicKey,
    signature2,
    data
  );

  const isValid3 = await crypto.subtle.verify(
    { name: 'ECDSA', hash: 'SHA-256' },
    privateKey.publicKey,
    signature3,
    data
  );
}
