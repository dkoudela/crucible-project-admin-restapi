#!/bin/bash

sudo atlas-integration-test | tee $BUILD_LOG |sed -f .logfilter.txt
exit ${PIPESTATUS[0]}

