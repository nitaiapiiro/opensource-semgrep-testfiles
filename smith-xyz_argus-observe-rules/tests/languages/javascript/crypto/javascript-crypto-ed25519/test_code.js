const crypto = require('crypto');
const { sign, verify, getPublicKey } = require('@noble/ed25519');
const nacl = require('tweetnacl');
const sodium = require('libsodium-wrappers');
const sjcl = require('sjcl');

function ed25519Usage() {
  const message = Buffer.from('message');
  const privateKey = Buffer.from('private-key-32-bytes-long-enough');
  const publicKey = Buffer.from('public-key-32-bytes-long-enough');

  const sign1 = crypto.createSign("ed25519");
  sign1.update(message);
  const signature1 = sign1.sign(privateKey);

  const verify1 = crypto.createVerify("ed25519");
  verify1.update(message);
  const isValid1 = verify1.verify(publicKey, signature1);

  crypto.subtle.generateKey({ name: "Ed25519" }, false, ["sign", "verify"]);

  crypto.subtle.sign({ name: "Ed25519" }, privateKey, message);

  crypto.subtle.verify({ name: "Ed25519" }, publicKey, signature1, message);

  const signature2 = sign(message, privateKey);
  const isValid2 = verify(signature2, message, publicKey);
  const pubKey = getPublicKey(privateKey);

  const signature3 = nacl.sign.detached(message, privateKey);
  const signedMessage = nacl.sign(message, privateKey);
  const opened = nacl.sign.open(signedMessage, publicKey);
  const isValid3 = nacl.sign.detached.verify(message, signature3, publicKey);

  sodium.ready.then(() => {
    const signature4 = sodium.crypto_sign(message, privateKey);
    const opened2 = sodium.crypto_sign_open(signature4, publicKey);
    const signature5 = sodium.crypto_sign_detached(message, privateKey);
    const isValid4 = sodium.crypto_sign_verify_detached(signature5, message, publicKey);
  });

  const keyPair = sjcl.ecc.ecdsa.generateKeys(256, 10);
  const secretKey = sjcl.ecc.ecdsa.secretKey(keyPair);
  const pubKey2 = sjcl.ecc.ecdsa.publicKey(keyPair);
}
