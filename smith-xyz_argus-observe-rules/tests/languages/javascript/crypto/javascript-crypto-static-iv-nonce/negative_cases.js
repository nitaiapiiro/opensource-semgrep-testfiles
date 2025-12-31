const crypto = require('crypto');

function nonStaticIVNonceUsage() {
  const key = Buffer.from('12345678901234567890123456789012');

  const IV1 = crypto.randomBytes(16);
  const cipher1 = crypto.createCipheriv("aes-256-cbc", key, IV1);

  const IV2 = crypto.getRandomValues(new Uint8Array(12));
  crypto.subtle.encrypt({ name: "AES-GCM", iv: IV2 }, key, new Uint8Array(16));

  const IV3 = Buffer.alloc(16);
  crypto.randomFillSync(IV3);
  const cipher2 = crypto.createCipheriv("aes-256-cbc", key, IV3);
}
