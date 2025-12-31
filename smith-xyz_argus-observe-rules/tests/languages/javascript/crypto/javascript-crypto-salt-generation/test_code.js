const crypto = require('crypto');
const bcrypt = require('bcrypt');

function saltGeneration() {
  const salt1 = crypto.randomBytes(16);
  crypto.pbkdf2Sync(Buffer.from('password'), salt1, 10000, 32, 'sha256');

  const salt2 = crypto.randomBytes(32);
  crypto.scryptSync(Buffer.from('password'), salt2, 32);

  const salt3 = crypto.getRandomValues(new Uint8Array(16));
  crypto.pbkdf2Sync(Buffer.from('password'), Buffer.from(salt3), 10000, 32, 'sha256');

  const saltLength = 16;
  const salt4 = crypto.randomBytes(saltLength);
  bcrypt.hashSync('password', 10);
}
