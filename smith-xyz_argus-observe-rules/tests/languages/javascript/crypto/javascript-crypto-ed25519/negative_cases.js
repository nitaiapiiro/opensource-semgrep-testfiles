const crypto = require('crypto');

function nonEd25519Usage() {
  const message = Buffer.from('message');
  const privateKey = Buffer.from('private-key');

  const sign1 = crypto.createSign("RSA-SHA256");
  sign1.update(message);
  const signature1 = sign1.sign(privateKey);

  const verify1 = crypto.createVerify("ecdsa-with-SHA256");
  verify1.update(message);
  const isValid1 = verify1.verify(privateKey, signature1);

  crypto.subtle.generateKey({ name: "ECDSA", namedCurve: "P-256" }, false, ["sign"]);
  crypto.subtle.sign({ name: "RSASSA-PKCS1-v1_5" }, privateKey, message);
}
