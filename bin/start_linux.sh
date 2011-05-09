#!/bin/bash

JAVA=$(which java)
RTFM_MAINCLASS="org.essembeh.rtfm.starter.MainClass"
RTFM_APP="$(dirname $0)/../"
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
