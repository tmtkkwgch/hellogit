@echo off

if exist "%JAVA_HOME%" goto okJava
goto ngJava

:ngJava
echo JAVA_HOME���w�肵�Ă�������
goto end

:okJava
goto dirCheck

:dirCheck
if exist "%cd%\convert.bat" goto okDir
echo convert.bat�t�@�C���̂���ꏊ�ֈړ������s���Ă��������B
goto end

:okDir
set CLASSPATH=..\..\classes
set CLASSPATH=%CLASSPATH%;..\conf
set CLASSPATH=%CLASSPATH%;..\lib\servlet-api.jar
set CLASSPATH=%CLASSPATH%;..\..\lib\commons-logging-1.1.2.jar
set CLASSPATH=%CLASSPATH%;..\..\lib\commons-digester-1.8.jar
set CLASSPATH=%CLASSPATH%;..\..\lib\commons-collections-3.2.1.jar
set CLASSPATH=%CLASSPATH%;..\..\lib\commons-beanutils-1.8.3.jar
set CLASSPATH=%CLASSPATH%;..\..\lib\commons-pool-1.6.jar
set CLASSPATH=%CLASSPATH%;..\..\lib\commons-dbcp-1.4.jar
set CLASSPATH=%CLASSPATH%;..\..\lib\commons-codec-1.9.jar
set CLASSPATH=%CLASSPATH%;..\..\lib\xml-apis.jar
set CLASSPATH=%CLASSPATH%;..\..\lib\oro-2.0.8.jar
set CLASSPATH=%CLASSPATH%;..\..\lib\h2_1.3.jar
set CLASSPATH=%CLASSPATH%;..\..\lib\lucene-analyzers-3.1.0.jar
set CLASSPATH=%CLASSPATH%;..\..\lib\lucene-core-3.1.0.jar
set CLASSPATH=%CLASSPATH%;..\..\lib\velocity-1.4.jar
set CLASSPATH=%CLASSPATH%;..\..\lib\velocity-dep-1.4.jar
set CLASSPATH=%CLASSPATH%;..\..\lib\log4j-1.2.16.jar
set CLASSPATH=%CLASSPATH%;..\..\lib\quartz-2.1.7.jar
set CLASSPATH=%CLASSPATH%;..\..\lib\struts-core-1.3.10.jar
set CLASSPATH=%CLASSPATH%;..\..\lib\postgresql.jar
set CLASSPATH=%CLASSPATH%;..\..\lib\mail.jar
set CLASSPATH=%CLASSPATH%;..\..\lib\mailapi.jar
set CLASSPATH=%CLASSPATH%;..\log4j\

rem echo CLASSPATH %CLASSPATH%
set __LOGDIR=.\..\log
if exist "%__LOGDIR%" goto javaExe
mkdir %__LOGDIR%
goto javaExe

:javaExe
set GSROOT=.\..\..\..\
set EXE_JAVACMD="%JAVA_HOME%\bin\java"

%EXE_JAVACMD% -Xmx768M -classpath %CLASSPATH% jp.groupsession.v2.convert.ConvertGsListenerImpl %GSROOT%
:end