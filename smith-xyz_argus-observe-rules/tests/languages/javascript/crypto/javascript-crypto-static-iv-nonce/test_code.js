const crypto = require('crypto');

function staticIVNonceUsage() {
  const key = Buffer.from('12345678901234567890123456789012');

  const IV1 = Buffer.from("static-iv-value");
  const cipher1 = crypto.createCipheriv("aes-256-cbc", key, IV1);

  const IV2 = Buffer.from("static-iv-value");
  const cipher2 = crypto.createCipheriv("aes-256-gcm", key, IV2);

  const NONCE1 = Buffer.from("static-nonce-value");
  const cipher3 = crypto.createCipheriv("aes-256-gcm", key, NONCE1);

  const cipher4 = crypto.createCipheriv("aes-256-cbc", key, Buffer.from("static-iv"));
  const cipher5 = crypto.createCipheriv("aes-256-gcm", key, Buffer.from("static-nonce"));

  const IV3 = new Uint8Array([1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]);
  crypto.subtle.encrypt({ name: "AES-CBC", iv: IV3 }, key, new Uint8Array(16));

  const NONCE2 = new Uint8Array([1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]);
  crypto.subtle.encrypt({ name: "AES-GCM", iv: NONCE2 }, key, new Uint8Array(16));

  const IV4 = Buffer.from("static-iv-value", "hex");
  const cipher6 = crypto.createCipheriv("aes-256-cbc", key, IV4);

  const IV5 = Buffer.from("static-iv-value", "base64");
  const cipher7 = crypto.createCipheriv("aes-256-cbc", key, IV5);
}
