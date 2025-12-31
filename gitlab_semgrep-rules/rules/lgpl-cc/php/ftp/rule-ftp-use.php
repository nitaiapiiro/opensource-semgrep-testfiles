<?php
// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
// source (original): https://github.com/semgrep/semgrep-rules/blob/a3fef245/php/lang/security/php_ftp_rule-ftp-use.php
// hash: a3fef245

// ruleid: php_ftp_rule-ftp-use
$conn_id = ftp_connect($ftp_server);

// ruleid: php_ftp_rule-ftp-use
$login_result = ftp_login($conn_id, $ftp_user_name, $ftp_user_pass);

// ok: php_ftp_rule-ftp-use
ssh2_scp_send($connection, '/local/filename', '/remote/filename', 0644);

?>