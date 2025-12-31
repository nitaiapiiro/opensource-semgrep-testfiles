async function keyLengthUsage() {
  const key1 = await crypto.subtle.generateKey(
    { name: "RSA-OAEP", modulusLength: 2048, publicExponent: new Uint8Array([1, 0, 1]), hash: "SHA-256" },
    false,
    ["encrypt", "decrypt"]
  );

  const key2 = await crypto.subtle.generateKey(
    { name: "RSASSA-PKCS1-v1_5", modulusLength: 4096, publicExponent: new Uint8Array([1, 0, 1]), hash: "SHA-256" },
    false,
    ["sign", "verify"]
  );

  const key3 = await crypto.subtle.generateKey(
    { name: "ECDSA", namedCurve: "P-224" },
    false,
    ["sign", "verify"]
  );

  const key4 = await crypto.subtle.generateKey(
    { name: "ECDSA", namedCurve: "P-256" },
    false,
    ["sign", "verify"]
  );

  const key5 = await crypto.subtle.generateKey(
    { name: "ECDSA", namedCurve: "P-384" },
    false,
    ["sign", "verify"]
  );

  const key6 = await crypto.subtle.generateKey(
    { name: "ECDSA", namedCurve: "P-521" },
    false,
    ["sign", "verify"]
  );

  const key7 = await crypto.subtle.generateKey(
    { name: "ECDSA", namedCurve: "secp160k1" },
    false,
    ["sign", "verify"]
  );

  const key8 = await crypto.subtle.generateKey(
    { name: "ECDSA", namedCurve: "secp256k1" },
    false,
    ["sign", "verify"]
  );

  const key9 = await crypto.subtle.generateKey(
    { name: "ECDSA", namedCurve: "prime256v1" },
    false,
    ["sign", "verify"]
  );

  const ecdh1 = crypto.createECDH("prime256v1");
  const ecdh2 = crypto.createECDH("secp256k1");

  const key10 = await crypto.subtle.generateKey(
    { name: "AES-GCM", length: 128 },
    false,
    ["encrypt", "decrypt"]
  );

  const key11 = await crypto.subtle.generateKey(
    { name: "AES-GCM", length: 192 },
    false,
    ["encrypt", "decrypt"]
  );

  const key12 = await crypto.subtle.generateKey(
    { name: "AES-GCM", length: 256 },
    false,
    ["encrypt", "decrypt"]
  );

  const key13 = await crypto.subtle.generateKey(
    { name: "AES-CBC", length: 128 },
    false,
    ["encrypt", "decrypt"]
  );

  const key14 = await crypto.subtle.generateKey(
    { name: "AES-CBC", length: 256 },
    false,
    ["encrypt", "decrypt"]
  );

  const key15 = await crypto.subtle.generateKey(
    { name: "AES-KW", length: 128 },
    false,
    ["wrapKey", "unwrapKey"]
  );

  const key16 = await crypto.subtle.generateKey(
    { name: "AES-KW", length: 256 },
    false,
    ["wrapKey", "unwrapKey"]
  );
}
