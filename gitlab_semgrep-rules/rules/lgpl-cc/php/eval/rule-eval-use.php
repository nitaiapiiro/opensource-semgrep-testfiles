<?php
// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
// source (original): https://github.com/semgrep/semgrep-rules/blob/a3fef245/php/lang/security/php_eval_rule-eval-use.php
// hash: a3fef245

// ruleid: php_eval_rule-eval-use
eval($user_input);

// ok: php_eval_rule-eval-use
eval('echo "OK"');

?>