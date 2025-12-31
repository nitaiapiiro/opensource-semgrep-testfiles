const crypto = require('crypto');

function basicDH() {
  const prime = Buffer.from('prime');
  const primeLength = 2048;

  const dh1 = crypto.createDiffieHellman(prime, 'hex');
  dh1.generateKeys();
  const shared1 = dh1.computeSecret(publicKey);

  const dh2 = crypto.createDiffieHellman(primeLength);
  dh2.generateKeys();
  const shared2 = dh2.computeSecret(publicKey);

  const dh3 = crypto.createDiffieHellmanGroup('modp14');
  dh3.generateKeys();
  const shared3 = dh3.computeSecret(publicKey);

  const dh4 = crypto.createDiffieHellmanGroup('modp15');
  const dh5 = crypto.createDiffieHellmanGroup('modp16');
}

function ecdhUsage() {
  const ecdh1 = crypto.createECDH('prime256v1');
  ecdh1.generateKeys();
  const shared1 = ecdh1.computeSecret(publicKey);

  const ecdh2 = crypto.createECDH('secp256k1');
  ecdh2.generateKeys();
  const shared2 = ecdh2.computeSecret(publicKey);
}
