#!/usr/bin/env bash

set -e

# enable globbing
shopt -s globstar

function check_header() {
  local filename="$1"
  grep -qE "[<!--|#|//] License:" "$filename" || {
    echo "no license header in $filename"
    return 1
  }
  return 0
}

for filename in **/rule-*.yml; do
  check_header "$filename" || exit 1
done

exit 0
