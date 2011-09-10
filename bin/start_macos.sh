#!/bin/bash

##
## Set RTFM_HOME
##
if [ -z "$RTFM_HOME" -o ! -d "$RTFM_HOME" ]; then
	RTFM_BIN="$0"
	while [ -L "$RTFM_BIN" ]; do
	    if echo "$RTFM_BIN" | grep -q "^/"; then
			RTFM_BIN="$(readlink "$RTFM_BIN")"
		else
			RTFM_BIN="$(dirname "$RTFM_BIN")/$(readlink "$RTFM_BIN")"
		fi
	done
	echo "RegexTagForMusic binary: $RTFM_BIN"
	RTFM_HOME="$(dirname $RTFM_BIN)/../"
fi
echo "Using RTFM_HOME: $RTFM_HOME"


##
## Configuration folder in user home folder
##
RTFM_MYCONF="$HOME/.rtfm/"
if [ -d "$RTFM_MYCONF" ]; then
	echo "Using personal configuration folder: $RTFM_MYCONF"
	export CLASSPATH=$CLASSPATH:$RTFM_MYCONF
fi


##
## Adding all lib/*.jar to classpath
##
for JAR in $RTFM_HOME/lib/*jar; do 
	export CLASSPATH=$CLASSPATH:$JAR
done


##
## Adding resources folder and config folder to classpath
##
export CLASSPATH=$CLASSPATH:$RTFM_HOME/config:$RTFM_HOME/resources


##
## Launching the Application
##
JAVA=$(which java)
RTFM_MAINCLASS="org.essembeh.rtfm.starter.MainClass"
$JAVA -cp $CLASSPATH $RTFM_MAINCLASS $@
