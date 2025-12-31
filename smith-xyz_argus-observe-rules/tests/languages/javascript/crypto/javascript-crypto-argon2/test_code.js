const argon2 = require('argon2');
const argon2Browser = require('argon2-browser');
const argon2Wasm = require('argon2-wasm');

async function argon2Usage() {
  const password = 'password123';

  const hash1 = await argon2.hash(password, {
    type: argon2.argon2i,
    memoryCost: 65536,
    timeCost: 3,
    parallelism: 4
  });

  const hash2 = await argon2.hash(password, {
    type: argon2.argon2d,
    memoryCost: 65536,
    timeCost: 3,
    parallelism: 4
  });

  const hash3 = await argon2.hash(password, {
    type: argon2.argon2id,
    memoryCost: 65536,
    timeCost: 3,
    parallelism: 4
  });

  const isValid = await argon2.verify(hash1, password);

  const hash4 = argon2.hashSync(password, {
    type: argon2.argon2i,
    memoryCost: 65536,
    timeCost: 3,
    parallelism: 4
  });

  const isValid2 = argon2.verifySync(hash4, password);

  const hash5 = await argon2Browser.hash(password, {
    type: argon2Browser.argon2i,
    memoryCost: 65536,
    timeCost: 3,
    parallelism: 4
  });

  const isValid3 = await argon2Browser.verify(hash5, password);

  const hash6 = await argon2Wasm.hash(password, {
    type: argon2Wasm.argon2i,
    memoryCost: 65536,
    timeCost: 3,
    parallelism: 4
  });

  const isValid4 = await argon2Wasm.verify(hash6, password);
}
