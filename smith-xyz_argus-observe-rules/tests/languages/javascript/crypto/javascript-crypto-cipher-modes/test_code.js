const crypto = require('crypto');

function cipherModes() {
  const key = Buffer.from('12345678901234567890123456789012');
  const iv = Buffer.from('1234567890123456');
  const data = Buffer.from('message');

  const cipher1 = crypto.createCipheriv('aes-256-cbc', key, iv);
  const cipher2 = crypto.createCipheriv('aes-256-ecb', key, iv);
  const cipher3 = crypto.createCipheriv('aes-256-cfb', key, iv);
  const cipher4 = crypto.createCipheriv('aes-256-ofb', key, iv);
  const cipher5 = crypto.createCipheriv('aes-256-ctr', key, iv);
  const cipher6 = crypto.createCipheriv('aes-256-gcm', key, iv);

  const decipher1 = crypto.createDecipheriv('aes-256-cbc', key, iv);
  const decipher2 = crypto.createDecipheriv('aes-256-ecb', key, iv);
  const decipher3 = crypto.createDecipheriv('aes-256-cfb', key, iv);
  const decipher4 = crypto.createDecipheriv('aes-256-ofb', key, iv);
  const decipher5 = crypto.createDecipheriv('aes-256-ctr', key, iv);
  const decipher6 = crypto.createDecipheriv('aes-256-gcm', key, iv);

  const cipher7 = crypto.createCipheriv('aes-256-cbc', key, iv);
  const ciphertext = cipher7.update(data);
  const final = ciphertext + cipher7.final();

  const decipher7 = crypto.createDecipheriv('aes-256-cbc', key, iv);
  const plaintext = decipher7.update(data);
  const final2 = plaintext + decipher7.final();
}
