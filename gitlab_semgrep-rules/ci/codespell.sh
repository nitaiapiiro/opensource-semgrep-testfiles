#!/usr/bin/env bash

set -e

langs=(c csharp go java javascript python scala kotlin swift ruby php generic)

# Codespell does not support running with built-in and custom dictionaries
# in the same run, so the first run uses the built-ins.
codespell "${langs[@]}"

# The second run uses our custom dictionary.
codespell "${langs[@]}" --dictionary ci/codespell.dict

echo "All rules and test files passed spell check"
