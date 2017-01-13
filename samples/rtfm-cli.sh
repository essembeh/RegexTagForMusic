#!/bin/sh
set -e
LAUNCHER=$(readlink -f "$0")
ROOT=$(dirname "$LAUNCHER")
JAR=`ls "$ROOT"/../rtfm-releng/target/rtfm-releng-*-jar-with-dependencies.jar | sort | tail -1`
test -f "$JAR"
java -jar "$JAR" $@
