#!/usr/bin/env bash

set -e

dest="/tmp/scaffold-scala-build"

prefixes=("rule")

mkdir -p "$dest/src/main/scala"

find_names=()
for prfx in "${prefixes[@]}"; do
  find_names+=("-name" "$prfx-*.scala" "-o")
done
unset 'find_names[${#find_names[@]}-1]'

echo "> copying project manifest to $dest/pom.xml"
cp scala/pom.xml "$dest/"

echo "> copying Scala test files $dest/src/main/scala"
find scala -type f \( "${find_names[@]}" \) -exec cp {} "$dest/src/main/scala" \;

(
  cd "$dest/src/main/scala"
  for prfx in "${prefixes[@]}"; do
    echo "> removing '$prfx-' prefix from filenames"
    for file in "$prfx"-*.scala; do
      new_name="${file#"$prfx-"}"
      mv "$file" "$new_name"
    done
  done
)

echo "> building project..."
(
  cd "$dest"
  mvn scala:compile -DdisplayCmd=true -DrecompileMode=all
)
