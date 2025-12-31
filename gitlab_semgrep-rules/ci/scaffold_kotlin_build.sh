#!/usr/bin/env bash

set -e

dest="/tmp/scaffold-kotlin-build"

prefixes=("rule")

mkdir -p "$dest/src/main/kotlin"

find_names=()
for prfx in "${prefixes[@]}"; do
  find_names+=("-name" "$prfx-*.kt" "-o")
done
unset 'find_names[${#find_names[@]}-1]'

echo "> copying project manifest to $dest/pom.xml"
cp rules/lgpl/kotlin/pom.xml "$dest/"

echo "> copying Kotlin test files $dest/src/main/kotlin"
cd rules/lgpl

find kotlin -type f \( "${find_names[@]}" \) -print0 |
  while IFS= read -r -d '' file; do
    package_name="$(dirname "${file#kotlin/}")"
    dest_dir="$dest/src/main/kotlin/$package_name"
    mkdir -p "$dest_dir"
    cp "$file" "$dest_dir/"
  done

(
  cd "$dest/src/main/kotlin"
  for prfx in "${prefixes[@]}"; do
    echo "> removing '$prfx-' prefix from filenames"
    for file in **/"$prfx"-*.kt; do
      bn=$(basename "$file")
      dn=$(dirname "$file")
      new_name="$dn/${bn#"$prfx-"}"
      mv "$file" "$new_name"
    done
  done
)

echo "> building project..."
(
  cd "$dest"
  mvn compile -DdisplayCmd=true -DrecompileMode=all
)
