#!/usr/bin/env sh

set -e

VERSION="$1"
MANIFEST="dist/manifest.json"
CHECKSUMS="checksums.txt"

[ -z "$VERSION" ] && {
  echo "no version provided"
  exit 1
}

ci/run_deploy.rb "$VERSION" || {
  echo "deployment failed"
  exit 1
}

ci/unique_ids.rb || {
  echo "unique id check failed"
  exit 1
}

cleanup() {
  # Disable shellcheck rule "Command appears to be unreachable" because it's a false positive, since shellcheck
  # is unable to understand that this function is called from the trap command
  # shellcheck disable=SC2317
  rm -f "$CHECKSUMS"
}

trap cleanup EXIT HUP INT QUIT TERM

find dist -type f -print0 | sort -z | xargs -r0 sha256sum | cut -d ' ' -f 1 | sha256sum >"$CHECKSUMS"
find dist -type f -name "*.yml" -print0 | sort -z | xargs -r0 sha256sum >>"$CHECKSUMS"

jq -nR '[inputs] | map (split("  ") | {"file": (if .[1] == "-" then "all files" else .[1] end), "checksum":.[0] })' <"$CHECKSUMS" >"$MANIFEST"

echo "$MANIFEST generated"

exit 0
