const crypto = require('crypto');
const bcrypt = require('bcrypt');
const bcryptjs = require('bcryptjs');

async function bcryptUsage() {
  const password = 'password';
  const rounds = 10;

  const hash1 = await bcrypt.hash(password, rounds);
  const hash2 = bcrypt.hashSync(password, rounds);

  const match1 = await bcrypt.compare(password, hash1);
  const match2 = bcrypt.compareSync(password, hash2);

  const hash3 = await bcryptjs.hash(password, rounds);
  const hash4 = bcryptjs.hashSync(password, rounds);

  const match3 = await bcryptjs.compare(password, hash3);
  const match4 = bcryptjs.compareSync(password, hash4);
}

function pbkdf2Usage() {
  const password = Buffer.from('password');
  const salt = Buffer.from('salt');
  crypto.pbkdf2Sync(password, salt, 10000, 32, 'sha256');
}

function scryptUsage() {
  const password = Buffer.from('password');
  const salt = Buffer.from('salt');
  const keylen = 32;
  crypto.scryptSync(password, salt, keylen);
}
