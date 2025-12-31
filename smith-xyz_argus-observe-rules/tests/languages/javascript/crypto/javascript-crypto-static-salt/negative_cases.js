const crypto = require('crypto');

function nonStaticSaltUsage() {
  const password = Buffer.from('password');

  const salt1 = crypto.randomBytes(16);
  const key1 = crypto.pbkdf2Sync(password, salt1, 100000, 64, "sha256");

  const salt2 = crypto.getRandomValues(new Uint8Array(16));
  crypto.subtle.deriveKey(
    { name: "PBKDF2", salt: salt2, iterations: 100000, hash: "SHA-256" },
    new CryptoKey(),
    { name: "AES-GCM", length: 256 },
    false,
    ["encrypt", "decrypt"]
  );

  const salt3 = Buffer.alloc(16);
  crypto.randomFillSync(salt3);
  const key2 = crypto.scryptSync(password, salt3, 64);
}
