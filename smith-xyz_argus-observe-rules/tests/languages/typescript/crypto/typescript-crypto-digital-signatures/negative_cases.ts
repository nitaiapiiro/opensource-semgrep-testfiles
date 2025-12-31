import * as crypto from 'crypto';

function nonCryptoFunction() {
  const message = "This function doesn't use digital signatures";
  console.log(message);
}

function confusingNames() {
  const signLooking = 'not actually a sign';
  const verifyLooking = 'not actually verify';
}

function regularHash() {
  const data = Buffer.from('message');
  const hash = crypto.createHash('sha256');
  hash.update(data);
  const result = hash.digest();
}
