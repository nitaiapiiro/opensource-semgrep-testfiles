<?php
// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
// source (original): https://github.com/semgrep/semgrep-rules/blob/a3fef245/php/lang/security/php_backticks_rule-backticks-use.php
// hash: a3fef245

// ruleid: php_backticks_rule-backticks-use
echo `ping -n 3 {$user_input}`;

// ok: php_backticks_rule-backticks-use
echo "Hello `` backticks";

?>