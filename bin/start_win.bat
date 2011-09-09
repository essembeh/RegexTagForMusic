@echo off
set RTFM_HOME=..
set CLASSPATH=%RTFM_HOME%\lib\log4j-1.2.15.jar;%RTFM_HOME%\lib\rtfm-1.0beta1.jar;%RTFM_HOME%\lib\swingx-core-1.6.2.jar;%RTFM_HOME%\lib\yajil-0.4.0.jar;
set CLASSPATH=%CLASSPATH%;%RTFM_HOME%\config\;%RTFM_HOME%\resources\
java -cp %CLASSPATH% org.essembeh.rtfm.starter.MainClass gui

