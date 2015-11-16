@echo off

if exist "%JAVA_HOME%" goto okJava
goto ngJava

:ngJava
echo JAVA_HOMEを指定してください
goto end

:okJava
goto dirCheck

:dirCheck
if exist "%cd%\reset_adminpass.bat" goto okDir
echo reset_adminpass.batファイルのある場所へ移動し実行してください。
goto end

:okDir

set CLASSPATH=..\classes
set CLASSPATH=%CLASSPATH%;.\conf
set CLASSPATH=%CLASSPATH%;..\classes
set CLASSPATH=%CLASSPATH%;..\dsedit\lib\servlet-api.jar
set CLASSPATH=%CLASSPATH%;..\lib\commons-logging-1.1.2.jar
set CLASSPATH=%CLASSPATH%;..\lib\commons-digester-1.8.jar
set CLASSPATH=%CLASSPATH%;..\lib\commons-codec-1.9.jar
set CLASSPATH=%CLASSPATH%;..\lib\commons-collections-3.2.1.jar
set CLASSPATH=%CLASSPATH%;..\lib\commons-beanutils-1.8.3.jar
set CLASSPATH=%CLASSPATH%;..\lib\commons-pool-1.6.jar
set CLASSPATH=%CLASSPATH%;..\lib\oro-2.0.8.jar
set CLASSPATH=%CLASSPATH%;..\lib\h2_1.3.jar
set CLASSPATH=%CLASSPATH%;..\lib\velocity-1.4.jar
set CLASSPATH=%CLASSPATH%;..\lib\velocity-dep-1.4.jar
set CLASSPATH=%CLASSPATH%;..\lib\log4j-1.2.16.jar

rem echo CLASSPATH %CLASSPATH%
set __LOGDIR=.\..\log
if exist "%__LOGDIR%" goto javaExe
mkdir %__LOGDIR%
goto javaExe

:javaExe
set GSROOT=.\..\..\
set EXE_JAVACMD="%JAVA_HOME%\bin\java"

%EXE_JAVACMD% -classpath %CLASSPATH% jp.groupsession.v3.tools.ResetAdminPassword %GSROOT%
:end