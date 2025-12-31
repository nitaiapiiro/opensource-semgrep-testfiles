#!/bin/bash
# Regenerate all config files and format them with prettier

set -e

cd "$(dirname "$0")/.."

# Generate crypto configs for each language
for lang in c cpp csharp go java javascript python rust typescript; do
    uv run observe generate-config --language "$lang" --category crypto --name "crypto-${lang}.yml" --no-validate
done

# Generate crypto-all.yml and go-runtime-security.yml
uv run observe generate-config --category crypto --name crypto-all.yml --no-validate
uv run observe generate-config --language go --category runtime-security --name go-runtime-security.yml --no-validate 2>/dev/null || true

# Format all config files
if command -v prettier >/dev/null 2>&1; then
    prettier --write "configs/**/*.{yml,yaml}"
else
    echo "Error: prettier not found. Install with: npm install -g prettier" >&2
    exit 1
fi
