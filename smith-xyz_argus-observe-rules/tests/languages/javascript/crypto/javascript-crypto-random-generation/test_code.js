const crypto = require('crypto');

function basicRandom() {
  const bytes1 = crypto.randomBytes(32);
  const bytes2 = crypto.getRandomValues(new Uint8Array(16));

  const bytes3 = crypto.randomBytes(64);
  const bytes4 = crypto.getRandomValues(new Uint8Array(32));
}

function mathRandom() {
  const random1 = Math.random();
  const random2 = Math.floor(Math.random() * 100);
  const random3 = Math.floor(Math.random() * 1000);
}
