#!/bin/bash

JAVA=$(which java)
RTFM_MAINCLASS="org.essembeh.rtfm.starter.MainClass"

export CLASSPATH=../config:../resources
for JAR in ../lib/*jar; do 
	export CLASSPATH=$CLASSPATH:../lib/$JAR
done

$JAVA -cp $CLASSPATH $RTFM_MAINCLASS $@
