#!/usr/bin/env bash

set -e

dest="/tmp/scaffold-java-build"

prefixes=("rule")

mkdir -p "$dest/src/main"

find_names=()
for prfx in "${prefixes[@]}"; do
  find_names+=("-name" "$prfx-*.java" "-o")
done
unset 'find_names[${#find_names[@]}-1]'

echo "> copying project manifest to $dest/pom.xml"
cp java/pom.xml "$dest/"

echo "> copying Java test files $dest/src/main/"
find java -type f \( "${find_names[@]}" \) -exec cp {} "$dest/src/main" \;

(
  cd "$dest/src/main"
  for prfx in "${prefixes[@]}"; do
    echo "> removing '$prfx-' prefix from filenames"
    for file in "$prfx"-*.java; do
      new_name="${file#"$prfx-"}"
      mv "$file" "$new_name"
    done
  done
)

echo "> building project..."
(
  cd "$dest"
  mvn clean install
)
