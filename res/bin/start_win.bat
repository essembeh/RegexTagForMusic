@echo off
set RTFM_HOME=..
set CLASSPATH=%RTFM_HOME%\lib\log4j-1.2.15.jar;%RTFM_HOME%\lib\rtfm-1.1.jar;%RTFM_HOME%\lib\swingx-core-1.6.2.jar;%RTFM_HOME%\lib\jaudiotagger-2.0.4-SNAPSHOT.jar;
set CLASSPATH=%CLASSPATH%;%RTFM_HOME%\config\;%RTFM_HOME%\share\
start javaw -cp %CLASSPATH% org.essembeh.rtfm.starter.MainClass gui

