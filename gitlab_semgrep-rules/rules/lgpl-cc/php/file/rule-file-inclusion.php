<?php
// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
// source (original): https://github.com/semgrep/semgrep-rules/blob/a3fef245/php/lang/security/php_file_rule-file-inclusion.php
// hash: a3fef245

$user_input = $_GET["tainted"];

// ruleid: php_file_rule-file-inclusion
include($user_input);

// ok: php_file_rule-file-inclusion
include('constant.php');

// ruleid: php_file_rule-file-inclusion
include_once($user_input);

// ok: php_file_rule-file-inclusion
include_once('constant.php');

// ruleid: php_file_rule-file-inclusion
require($user_input);

// ok: php_file_rule-file-inclusion
require('constant.php');

// ruleid: php_file_rule-file-inclusion
require_once($user_input);

// ok: php_file_rule-file-inclusion
require_once('constant.php');

// ruleid: php_file_rule-file-inclusion
include(__DIR__ . $user_input);

// ok: php_file_rule-file-inclusion
include(__DIR__ . 'constant.php');

// ok: php_file_rule-file-inclusion
include_safe(__DIR__ . $user_input);

// ok: php_file_rule-file-inclusion
require_once(CONFIG_DIR . '/constant.php');

// ok: php_file_rule-file-inclusion
require_once( dirname( __FILE__ ) . '/admin.php' );

// ok: php_file_rule-file-inclusion
$pth = 'foo/bar.php';
require_once $pth;

?>