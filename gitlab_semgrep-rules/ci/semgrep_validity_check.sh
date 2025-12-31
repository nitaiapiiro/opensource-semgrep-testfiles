#!/bin/bash

set -e

find dist -name "*.yml" | while read -r file; do
  echo "checking $file"
  if ! semgrep --config="$file" --validate; then
    # if an error was detected, re-run the validation, but use the
    # validate command to output detailed error information
    semgrep --config=dist/bandit.yml validate
  fi
done
