import * as argon2 from 'argon2';
import * as argon2Browser from 'argon2-browser';

async function basicArgon2() {
  const password = 'password';
  const options = { type: argon2.argon2i, memoryCost: 65536, timeCost: 3, parallelism: 4 };

  const hash1 = await argon2.hash(password, options);
  const hash2 = await argon2.hash(password, { type: argon2.argon2id, memoryCost: 32768, timeCost: 2, parallelism: 2 });

  const match1 = await argon2.verify(hash1, password);
  const match2 = await argon2.verify(hash2, password);
}

async function argon2BrowserUsage() {
  const password = 'password';
  const hash1 = await argon2Browser.hash(password, { type: argon2Browser.argon2i });
  const match1 = await argon2Browser.verify(hash1, password);
}

function requireUsage() {
  const password = 'password';
  const hash1 = require('argon2').hash(password, {});
  const hash2 = require('argon2-browser').hash(password, {});
}
