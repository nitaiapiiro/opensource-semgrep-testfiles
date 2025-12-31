<?php
// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
// source (original): https://github.com/semgrep/semgrep-rules/blob/a3fef245/php/lang/security/php_crypto_rule-weak-crypto.php#L1-1
// hash: a3fef245

// ruleid: php_crypto_rule-weak-crypto
$hashed_password = crypt('mypassword');

// ruleid: php_crypto_rule-weak-crypto
$hashed_password = md5('mypassword');

// ruleid: php_crypto_rule-weak-crypto
$hashed_password = md5_file('filename.txt');

// ruleid: php_crypto_rule-weak-crypto
$hashed_password = sha1('mypassword');

// ruleid: php_crypto_rule-weak-crypto
$hashed_password = sha1_file('filename.txt');

// ruleid: php_crypto_rule-weak-crypto
$hashed_password = str_rot13('totally secure');

// ok: php_crypto_rule-weak-crypto
$hashed_password = sodium_crypto_generichash('mypassword');

?>