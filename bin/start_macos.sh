#!/bin/bash

JAVA=$(which java)
RTFM_MAINCLASS="org.essembeh.rtfm.starter.MainClass"
RTFM_HOME="$HOME/.rtfm/"


## configuration folder in user home folder
if [ -d "$RTFM_HOME" ]; then
	export CLASSPATH=$RTFM_HOME
fi

for JAR in ../lib/*jar; do 
	export CLASSPATH=$CLASSPATH:../lib/$JAR
done
export CLASSPATH=$CLASSPATH:../config:../resources

echo "Using classpath: $CLASSPATH"

$JAVA -cp $CLASSPATH $RTFM_MAINCLASS $@
