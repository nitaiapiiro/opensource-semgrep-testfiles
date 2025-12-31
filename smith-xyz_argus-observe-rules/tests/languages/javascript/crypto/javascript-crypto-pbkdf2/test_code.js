const crypto = require('crypto');

function basicPBKDF2() {
  const password = Buffer.from('password');
  const salt = Buffer.from('salt');
  const iterations = 10000;
  const keylen = 32;

  crypto.pbkdf2(password, salt, iterations, keylen, 'sha256', (err, key) => {
    if (err) throw err;
  });

  const key1 = crypto.pbkdf2Sync(password, salt, iterations, keylen, 'sha256');

  crypto.pbkdf2(password, salt, 5000, 64, 'sha512', (err, key) => {
    if (err) throw err;
  });

  const key2 = crypto.pbkdf2Sync(password, salt, 100000, 32, 'sha256');
}
