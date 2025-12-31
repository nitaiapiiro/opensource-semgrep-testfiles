#!/usr/bin/env bash

# Runs current Java rules against the github.com/OWASP-Benchmark/BenchmarkJava
# project and copies the final SARIF report and benchmark scorecards to ./tmp

set -euo pipefail

if ! command -v git &>/dev/null; then
  echo "git command is required for this script"
  exit 1
fi

if ! command -v docker &>/dev/null; then
  echo "docker command is required for this script"
  exit 1
fi

if ! command -v mvn &>/dev/null; then
  echo "Maven (mvn) command is required for this script"
  exit 1
fi

ymd="$(date -I)"

tempdir="$(mktemp -d -t sast-rules-java-benchmark)"
mkdir "$tempdir/rules"
mkdir "$tempdir/results"

benchDir="$tempdir/BenchmarkJava"
utilsDir="$tempdir/BenchmarkUtils"

echo "> Copying Java rules to $tempdir/rules..."
find . -type f -name '*.yml' | grep 'java/' | grep -v 'fixtures/' | xargs -I {} cp {} "$tempdir/rules/"

echo "> Cloning OWASP-Benchmark/BenchmarkJava project to $benchDir..."
git clone -q --depth 1 https://github.com/OWASP-Benchmark/BenchmarkJava.git "$benchDir"

# Wipe checked in result and scorecard files.
rm -rf "$benchDir/results" && mkdir "$benchDir/results"
rm -rf "$benchDir/scorecard" && mkdir "$benchDir/scorecard"

echo "> Cloning OWASP-Benchmark/BenchmarkUtils project to $utilsDir..."
git clone -q --depth 1 https://github.com/OWASP-Benchmark/BenchmarkUtils.git "$utilsDir"

echo "> Installing Maven plugin for scorecard generation..."
(cd "$utilsDir" && mvn install -q -Dspotless.check.skip=true -Dspotless.apply.skip=true -DskipTests >/dev/null)

echo "> Pulling docker.io/semgrep/semgrep image..."
docker pull -q docker.io/semgrep/semgrep >/dev/null

benchmarkVersion=$("$benchDir/scripts/getBenchmarkVersion.sh")
semgrepVersion=$(docker run --rm semgrep/semgrep semgrep --version)
resultFile="Benchmark_$benchmarkVersion-Semgrep-v$semgrepVersion.sarif"

echo "> Scanning BenchmarkJava code..."
docker run --rm -v "$tempdir:/src" semgrep/semgrep semgrep -q -f /src/rules/ --metrics=off --sarif -o "/src/BenchmarkJava/results/$resultFile" /src/BenchmarkJava/src

echo "> Generating scorecards..."
(cd "$benchDir" && ./createScorecards.sh >/dev/null)

mkdir -p "tmp/java-benchmark-$ymd"
cp -R "$benchDir/scorecard" "tmp/java-benchmark-$ymd/"
cp -R "$benchDir/results" "tmp/java-benchmark-$ymd/"
echo "> Copied benchmark files to ./tmp/java-benchmark-$ymd/"

rm -rf "$tempdir"
