import * as crypto from 'crypto';

function basicDH() {
  const prime = Buffer.from('prime');
  const primeLength = 2048;
  const publicKey = Buffer.from('public key');

  const dh1: crypto.DiffieHellman = crypto.createDiffieHellman(prime, 'hex');
  dh1.generateKeys();
  const shared1 = dh1.computeSecret(publicKey);

  const dh2: crypto.DiffieHellman = crypto.createDiffieHellman(primeLength);
  dh2.generateKeys();
  const shared2 = dh2.computeSecret(publicKey);

  const dh3: crypto.DiffieHellman = crypto.createDiffieHellmanGroup('modp14');
  dh3.generateKeys();
  const shared3 = dh3.computeSecret(publicKey);

  const dh4 = crypto.createDiffieHellmanGroup('modp15');
  const dh5 = crypto.createDiffieHellmanGroup('modp16');
}

function ecdhUsage() {
  const publicKey = Buffer.from('public key');
  const ecdh1: crypto.ECDH = crypto.createECDH('prime256v1');
  ecdh1.generateKeys();
  const shared1 = ecdh1.computeSecret(publicKey);

  const ecdh2: crypto.ECDH = crypto.createECDH('secp256k1');
  ecdh2.generateKeys();
  const shared2 = ecdh2.computeSecret(publicKey);
}

async function webCryptoECDH() {
  const key = await crypto.subtle.generateKey(
    { name: 'ECDH', namedCurve: 'P-256' },
    true,
    ['deriveKey', 'deriveBits']
  );

  const publicKey = new Uint8Array([1, 2, 3]);
  await crypto.subtle.deriveKey(
    { name: 'ECDH', public: publicKey },
    key,
    { name: 'AES-GCM', length: 256 },
    true,
    ['encrypt', 'decrypt']
  );
}
