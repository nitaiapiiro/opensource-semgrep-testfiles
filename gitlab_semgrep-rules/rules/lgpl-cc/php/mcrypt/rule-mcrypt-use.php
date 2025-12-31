<?php
// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
// source (original): https://github.com/semgrep/semgrep-rules/blob/a3fef245/php/lang/security/php_mcrypt_rule-mcrypt-use.yaml
// hash: a3fef245

// ruleid: php_mcrypt_rule-mcrypt-use
mcrypt_ecb(MCRYPT_BLOWFISH, $key, base64_decode($input), MCRYPT_DECRYPT);

// ruleid: php_mcrypt_rule-mcrypt-use
mcrypt_create_iv($iv_size, MCRYPT_RAND);

// ruleid: php_mcrypt_rule-mcrypt-use
mdecrypt_generic($td, $c_t);

// ok: php_mcrypt_rule-mcrypt-use
sodium_crypto_secretbox("Hello World!", $nonce, $key);

// ok: php_mcrypt_rule-mcrypt-use
openssl_encrypt($plaintext, $cipher, $key, $options=0, $iv, $tag);

?>