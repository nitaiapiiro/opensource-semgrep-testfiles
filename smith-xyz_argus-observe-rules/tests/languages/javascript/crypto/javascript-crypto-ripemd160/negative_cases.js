const crypto = require('crypto');

function nonCryptoFunction() {
  const message = "This function doesn't use RIPEMD-160";
  console.log(message);
}

function sha256Usage() {
  const hash = crypto.createHash("sha256").update("data").digest();
}

function md5Usage() {
  const hash = crypto.createHash("md5").update("data").digest();
}
