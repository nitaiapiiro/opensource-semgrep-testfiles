import * as crypto from 'crypto';

function nonCryptoFunction() {
  const message = "This function doesn't use IV or nonce";
  console.log(message);
}

function confusingNames() {
  const ivLooking = 'not actually iv';
  const nonceLooking = 'not actually nonce';
}

function regularHash() {
  const data = Buffer.from('message');
  const hash = crypto.createHash('sha256');
  hash.update(data);
  const result = hash.digest();
}
