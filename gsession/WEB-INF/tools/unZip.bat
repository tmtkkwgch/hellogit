@echo off

if exist "%JAVA_HOME%" goto okJava
goto ngJava

:ngJava
echo JAVA_HOMEを指定してください
goto end

:okJava
goto dirCheck

:dirCheck
if exist "%cd%\unZip.bat" goto okDir
echo unZip.batファイルのある場所へ移動し実行してください
goto end

:okDir

set CLASSPATH=..\classes
set CLASSPATH=%CLASSPATH%;.\conf
set CLASSPATH=%CLASSPATH%;..\classes
set CLASSPATH=%CLASSPATH%;..\lib\commons-logging-1.1.2.jar
set CLASSPATH=%CLASSPATH%;..\lib\log4j-1.2.16.jar
set CLASSPATH=%CLASSPATH%;..\lib\ant.jar
set CLASSPATH=%CLASSPATH%;..\lib\commons-compress-1.5.jar
set CLASSPATH=%CLASSPATH%;..\lib\commons-io-2.4.jar

rem echo CLASSPATH %CLASSPATH%
set __LOGDIR=.\..\log
if exist "%__LOGDIR%" goto javaExe
mkdir %__LOGDIR%
goto javaExe

:javaExe

set EXE_JAVACMD="%JAVA_HOME%\bin\java"

%EXE_JAVACMD% -classpath %CLASSPATH% jp.groupsession.v3.tools.UnZip
:end