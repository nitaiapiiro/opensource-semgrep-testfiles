import * as crypto from 'crypto';

function nonCryptoFunction() {
  const message = "This function doesn't use key derivation";
  console.log(message);
}

function confusingNames() {
  const kdfLooking = 'not actually kdf';
  const kdfVar = 12345;
}

function regularHash() {
  const data = Buffer.from('message');
  const hash = crypto.createHash('sha256');
  hash.update(data);
  const result = hash.digest();
}
