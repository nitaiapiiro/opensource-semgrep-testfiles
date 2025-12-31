const crypto = require('crypto');

function hashComparison() {
  const data1 = Buffer.from('data1');
  const data2 = Buffer.from('data2');
  const expected = 'expected-hash';

  const hash1 = crypto.createHash("md5");
  hash1.update(data1);
  const hash2 = crypto.createHash("md5");
  hash2.update(data2);
  const match1 = hash1.digest("hex") === hash2.digest("hex");

  const hash3 = crypto.createHash("sha1");
  hash3.update(data1);
  const hash4 = crypto.createHash("sha1");
  hash4.update(data2);
  const match2 = hash3.digest("hex") === hash4.digest("hex");

  const hash5 = crypto.createHash("sha256");
  hash5.update(data1);
  const hash6 = crypto.createHash("sha256");
  hash6.update(data2);
  const match3 = hash5.digest("hex") === hash6.digest("hex");

  const hash7 = crypto.createHash("md5");
  hash7.update(data1);
  if (hash7.digest("hex") === expected) {
    console.log("Match");
  }

  const hash8 = crypto.createHash("sha1");
  hash8.update(data1);
  if (hash8.digest("hex") === expected) {
    console.log("Match");
  }

  const hash9 = crypto.createHash("sha256");
  hash9.update(data1);
  if (hash9.digest("hex") === expected) {
    console.log("Match");
  }

  const hash10 = crypto.createHash("md5");
  hash10.update(data1);
  const expectedBuf = Buffer.from(expected, "hex");
  const match4 = crypto.timingSafeEqual(hash10.digest(), expectedBuf);

  const hash11 = crypto.createHash("sha256");
  hash11.update(data1);
  const expectedBuf2 = Buffer.from(expected, "hex");
  const match5 = crypto.timingSafeEqual(hash11.digest(), expectedBuf2);
}
