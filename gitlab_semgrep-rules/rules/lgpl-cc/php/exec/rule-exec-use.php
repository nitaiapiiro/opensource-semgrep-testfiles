<?php
// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
// source (original): https://github.com/semgrep/semgrep-rules/blob/a3fef245/php/lang/security/php_exec_rule-exec-use.php
// hash: a3fef245

// ruleid: php_exec_rule-exec-use
exec($user_input);

// ok: php_exec_rule-exec-use
exec('whoami');

// ruleid: php_exec_rule-exec-use
passthru($user_input);

// ruleid: php_exec_rule-exec-use
$proc = proc_open($cmd, $descriptorspec, $pipes);

// ruleid: php_exec_rule-exec-use
$handle = popen($user_input, "r");

// ruleid: php_exec_rule-exec-use
$output = shell_exec($user_input);

// ruleid: php_exec_rule-exec-use
$output = system($user_input, $retval);

// ruleid: php_exec_rule-exec-use
pcntl_exec($path);

?>