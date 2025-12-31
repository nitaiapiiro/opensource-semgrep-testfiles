const crypto = require('crypto');

function nonHashComparison() {
  const data = Buffer.from('data');

  const hash = crypto.createHash("sha256");
  hash.update(data);
  const result = hash.digest("hex");

  const hash2 = crypto.createHash("sha256");
  hash2.update(data);
  const result2 = hash2.digest("base64");

  const str1 = "string1";
  const str2 = "string2";
  const match = str1 === str2;
}
