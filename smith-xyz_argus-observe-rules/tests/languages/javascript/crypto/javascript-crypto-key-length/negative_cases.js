const crypto = require('crypto');

function nonKeyLengthUsage() {
  const hash = crypto.createHash("sha256");
  hash.update("data");
  const result = hash.digest();

  const hmac = crypto.createHmac("sha256", "key");
  hmac.update("data");
  const hmacResult = hmac.digest();

  const random = crypto.randomBytes(32);
}
