#!/bin/bash

RTFM_BIN=$0
while [ -L "$RTFM_BIN" ]; do
	RTFM_BIN="$(dirname "$RTFM_BIN")/$(readlink "$RTFM_BIN")"
	echo "RTFM_BIN=$RTFM_BIN"
done

JAVA=$(which java)
RTFM_MAINCLASS="org.essembeh.rtfm.starter.MainClass"
RTFM_APP="$(dirname "$RTFM_BIN")/../"
RTFM_HOME="$HOME/.rtfm/"

## configuration folder in user home folder
if [ -d "$RTFM_HOME" ]; then
	export CLASSPATH=$CLASSPATH:$RTFM_HOME
fi

for JAR in $RTFM_APP/lib/*jar; do 
	export CLASSPATH=$CLASSPATH:$JAR
done

export CLASSPATH=$CLASSPATH:$RTFM_APP/config:$RTFM_APP/resources

$JAVA -cp $CLASSPATH $RTFM_MAINCLASS $@
