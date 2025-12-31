const crypto = require('crypto');

function basicScrypt() {
  const password = Buffer.from('password');
  const salt = Buffer.from('salt');
  const keylen = 32;

  crypto.scrypt(password, salt, keylen, { N: 16384, r: 8, p: 1 }, (err, key) => {
    if (err) throw err;
  });

  const key1 = crypto.scryptSync(password, salt, keylen);

  const key2 = crypto.scryptSync(password, salt, keylen, { N: 16384, r: 8, p: 1 });

  crypto.scrypt(password, salt, 64, { N: 32768, r: 8, p: 2 }, (err, key) => {
    if (err) throw err;
  });
}
