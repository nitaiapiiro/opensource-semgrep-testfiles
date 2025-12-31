const crypto = require('crypto');

function staticSaltUsage() {
  const password = Buffer.from('password');

  const SALT1 = Buffer.from("static-salt-value");
  const key1 = crypto.pbkdf2Sync(password, SALT1, 100000, 64, "sha256");

  const SALT2 = Buffer.from("static-salt-value");
  const key2 = crypto.scryptSync(password, SALT2, 64);

  const key3 = crypto.pbkdf2Sync(password, Buffer.from("static-salt"), 100000, 64, "sha256");
  const key4 = crypto.scryptSync(password, Buffer.from("static-salt"), 64);

  const SALT3 = new Uint8Array([1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]);
  crypto.subtle.deriveKey(
    { name: "PBKDF2", salt: SALT3, iterations: 100000, hash: "SHA-256" },
    new CryptoKey(),
    { name: "AES-GCM", length: 256 },
    false,
    ["encrypt", "decrypt"]
  );

  const SALT4 = Buffer.from("static-salt-value", "hex");
  const key5 = crypto.pbkdf2Sync(password, SALT4, 100000, 64, "sha256");

  const SALT5 = Buffer.from("static-salt-value", "base64");
  const key6 = crypto.pbkdf2Sync(password, SALT5, 100000, 64, "sha256");
}
