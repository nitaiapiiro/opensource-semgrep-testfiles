#!/usr/bin/env bash

set -euo pipefail

semgrep_cli="${SEMGREP_CLI:-semgrep}"

watch_dirs=(
  "c"
  "csharp"
  "go"
  "java"
  "javascript"
  "python"
  "scala"
  "rules/lgpl/kotlin"
)

watchexec_args=(
  "-p"         # Don't run initial command on startup, wait for file event.
  "-c" "clear" # Clear screen between runs.
)
for dir in "${watch_dirs[@]}"; do
  watchexec_args+=("-w" "$dir")
done

if ! command -v "$semgrep_cli" >/dev/null 2>&1; then
  echo "Semgrep CLI is not installed or not available in your PATH."
  echo "Go here for installation instructions: https://semgrep.dev/docs/getting-started/quickstart/"
  exit 1
fi

if ! command -v watchexec >/dev/null 2>&1; then
  echo "watchexec is not installed or not available in your PATH."
  echo "Go here for installation instructions: https://github.com/watchexec/watchexec/tree/main/crates/cli#installation"
  exit 1
fi

echo "> watching for file changes..."
# Disable shellcheck rule "Expressions don't expand in single quotes, use double quotes for that."
# because $WATCHEXEC_* variables are expanded in the subprocess, not this script
# shellcheck disable=SC2016
watchexec "${watchexec_args[@]}" 'echo $WATCHEXEC_COMMON_PATH; semgrep --metrics=off --test $WATCHEXEC_COMMON_PATH'
