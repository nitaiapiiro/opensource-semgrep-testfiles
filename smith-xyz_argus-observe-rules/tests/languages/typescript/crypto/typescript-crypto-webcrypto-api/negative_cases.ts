function nonCryptoFunction() {
  const message = "This function doesn't use Web Crypto API";
  console.log(message);
}

function confusingNames() {
  const cryptoLooking = 'not actually crypto';
  const cryptoVar = 12345;
}

function regularFunction() {
  const data = [1, 2, 3];
  const result = data.map(x => x * 2);
}
