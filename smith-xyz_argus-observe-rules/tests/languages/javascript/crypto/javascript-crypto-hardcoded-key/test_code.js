const crypto = require('crypto');

function hardcodedKey1() {
  const key = "my-secret-key-12345";
  const iv = Buffer.from('1234567890123456');
  crypto.createCipheriv('aes-256-cbc', key, iv);
}

function hardcodedKey2() {
  crypto.createCipheriv('aes-256-cbc', "hardcoded-secret-key", Buffer.from('1234567890123456'));
}

function hardcodedKey3() {
  const KEY = "another-hardcoded-key";
  const iv = Buffer.from('1234567890123456');
  crypto.createCipheriv('aes-256-cbc', KEY, iv);
}
