const crypto = require('crypto');
const bcrypt = require('bcrypt');

function nonArgon2Usage() {
  const password = 'password123';

  const hash1 = crypto.pbkdf2Sync(password, 'salt', 100000, 64, 'sha256');
  const hash2 = crypto.scryptSync(password, 'salt', 64);

  const hash3 = bcrypt.hashSync(password, 10);
  const isValid = bcrypt.compareSync(password, hash3);
}
