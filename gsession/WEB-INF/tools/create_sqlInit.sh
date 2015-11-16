#!/bin/sh

LOGDIR=../log
GSROOT=./

if [ -z "$JAVA_HOME" ]
then
    echo "Please set the JAVA_HOME"
    exit 0
fi

#dirCheck
if [ -f "create_sqlInit.sh" ]
then
    echo "Directory OK"
else
    echo "Please change directory with the create_sqlInit.sh"
    exit 0
fi

export CLASSPATH="$CLASSPATH":./conf
export CLASSPATH="$CLASSPATH":../classes
export CLASSPATH="$CLASSPATH":../dsedit/lib/servlet-api.jar
export CLASSPATH="$CLASSPATH":../lib/postgresql.jar
export CLASSPATH="$CLASSPATH":../lib/commons-logging-1.1.2.jar
export CLASSPATH="$CLASSPATH":../lib/commons-digester-1.8.jar
export CLASSPATH="$CLASSPATH":../lib/commons-codec-1.9.jar
export CLASSPATH="$CLASSPATH":../lib/commons-collections-3.2.1.jar
export CLASSPATH="$CLASSPATH":../lib/commons-beanutils-1.8.3.jar
export CLASSPATH="$CLASSPATH":../lib/commons-pool-1.6.jar
export CLASSPATH="$CLASSPATH":../lib/oro-2.0.8.jar
export CLASSPATH="$CLASSPATH":../lib/h2_1.2.jar
export CLASSPATH="$CLASSPATH":../lib/velocity-1.4.jar
export CLASSPATH="$CLASSPATH":../lib/velocity-dep-1.4.jar
export CLASSPATH="$CLASSPATH":../lib/log4j-1.2.8.jar

echo "CLASSPATH=" $CLASSPATH
set __LOGDIR=$LOGDIR
if [ -d $LOGDIR ]
then
    echo "LOG Directory is Exist"
  else
    mkdir $LOGDIR
fi

$JAVA_HOME/bin/java -Xmx256M -classpath $CLASSPATH jp.groupsession.v3.tools.CreateSqlFile $GSROOT
exit 0
