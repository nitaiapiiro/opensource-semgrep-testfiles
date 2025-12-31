#!/usr/bin/env bash

set -e -x

# Grab latest semgrep
git clone https://gitlab.com/gitlab-org/security-products/analyzers/semgrep.git

# sast-rules.zip is an artifact built in verify stage
cp sast-rules.zip semgrep/
cd semgrep

# Inject copy of our sast-rules.zip
sed -i -r 's/(\/analyzer-binary)/\1\nCOPY sast-rules.zip \/sast-rules.zip/;' Dockerfile

# Noop out getting latest rules since we copied in our sast-rules
# Disable shellcheck rule because we want to perform a literal string replacement, without expanding variables
# shellcheck disable=SC2016
sed -i 's|wget https://gitlab.com/api/v4/projects/${SAST_RULES_PROJECTID}/packages/generic/sast-rules/v${SAST_RULES_VERSION}/sast-rules-v${SAST_RULES_VERSION}.zip -O sast-rules.zip|/bin/true|' Dockerfile

# Now build out our docker image with _this_ branch's updated rules instead of allowing the Dockerfile to pull latest rules
docker build -t semgrep-sast-rules -f Dockerfile .
docker info

echo "$CI_JOB_TOKEN" | docker login -u gitlab-ci-token --password-stdin "$CI_REGISTRY"
docker tag semgrep-sast-rules "registry.gitlab.com/gitlab-org/security-products/sast-rules/semgrep-sast-rules:$TARGET_TAG"
docker push "registry.gitlab.com/gitlab-org/security-products/sast-rules/semgrep-sast-rules:$TARGET_TAG"

# Move back to top level dir
cd ..

# prepare BAP related information
echo "UPSTREAM_JOB_ID=$CI_JOB_ID" >>build.env
cp qa/bap/work.json work.json

# update work.json TARGET_TAG to point to our newly created semgrep-sast-rules:$TARGET_TAG
sed -i "s/TARGET_TAG/$TARGET_TAG/g" work.json
