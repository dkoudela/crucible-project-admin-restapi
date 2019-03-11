#!/bin/bash

# Unit tests are part of the Integration tests.
# It is kept only for further test downgrade
#    - sudo atlas-unit-test
# Integration tests
sudo atlas-integration-test | tee $BUILD_LOG |sed -f .logfilter.txt
exit ${PIPESTATUS[0]}

