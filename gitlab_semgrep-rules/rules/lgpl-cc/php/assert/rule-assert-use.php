<?php
// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
// source (original): https://github.com/semgrep/semgrep-rules/blob/a3fef245/php/lang/security/php_assert_rule-assert-use.php
// hash: a3fef245

$tainted = $_GET['userinput'];

// ruleid: php_assert_rule-assert-use
assert($tainted);

// ok: php_assert_rule-assert-use
assert('2 > 1');

// todook: php_assert_rule-assert-use
assert($tainted > 1);

Route::get('bad', function ($name) {
  // ruleid: php_assert_rule-assert-use
  assert($name);

  // ok: php_assert_rule-assert-use
  assert('2 > 1');

  // todook: php_assert_rule-assert-use
  assert($name > 1);
});

?>
