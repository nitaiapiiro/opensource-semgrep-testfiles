const crypto = require('crypto');

function nonBlake2Usage() {
  const data = Buffer.from('message');

  const hash1 = crypto.createHash("sha256");
  hash1.update(data);
  const result1 = hash1.digest();

  const hash2 = crypto.createHash("md5");
  hash2.update(data);
  const result2 = hash2.digest();
}
