const crypto = require('crypto');

function ripemd160Usage1() {
  const hasher = crypto.createHash("ripemd160");
  hasher.update("data");
  const hash = hasher.digest();
}

function ripemd160Usage2() {
  const hash = crypto.createHash("ripemd160").update("data").digest("hex");
}

function ripemd160Usage3() {
  const hash = crypto.createHash("rmd160").update("data").digest("base64");
}

function ripemd160Usage4() {
  const hasher = crypto.createHash('ripemd160');
  hasher.update("data");
  const hash = hasher.digest("hex");
}
