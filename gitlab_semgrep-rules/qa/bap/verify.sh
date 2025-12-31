#!/usr/bin/env bash

set -e

ALLOWED_FAILURE=0
ALLOWED_TIMEDIFF=5m
ALLOWED_VULNDIFF=50
REPORT=./scan-reports/gl-bap-analysis-report.json

curl --retry 3 --retry-delay 3 --fail -sS --location --output artifacts.zip --location --header "JOB-TOKEN: ${CI_JOB_TOKEN}" "https://gitlab.com/api/v4/projects/${DOWNSTREAM_PROJECT_ID}/packages/generic/run-diff/${UPSTREAM_JOB_ID}/artifacts.zip"
unzip -n -qq artifacts.zip

go run qa/bap/verify.go -report $REPORT -fail $ALLOWED_FAILURE -timediff $ALLOWED_TIMEDIFF -vulndiff $ALLOWED_VULNDIFF
