#!/bin/bash

export LANG=zh_CN.UTF-8
export JAVA_FILE_ENCODING=UTF-8
export NLS_LANG=AMERICAN_AMERICA.ZHS16GBK
#export CPU_COUNT="$(expr `cat /sys/fs/cgroup/cpu/cpu.cfs_quota_us` / `cat /sys/fs/cgroup/cpu/cpu.cfs_period_us`)"
export CPU_COUNT="$(grep -c 'cpu[0-9][0-9]*' /proc/stat)"
ulimit -c unlimited

#mypwd=$(cd `dirname "${BASH_SOURCE[0]}"` && pwd)/`basename "${BASH_SOURCE[0]}"`
#mypwd=`dirname $mypwd`
mypwd=$(cd "$(dirname "$0")";pwd)

NODE_HOME=$mypwd
DIR_NAME="${NODE_HOME##*/}"
# 2.8G, 默认用于 4G 内存 pod, 大约正好在80%内存报警线上
Xmx_HEAPSIZE=${jvm_xmx:-2867}
Xms_HEAPSIZE=${jvm_xms:-1024}
CONFIG_DIR=$NODE_HOME/conf
LIBPATH=$NODE_HOME/lib
ROOT_LOG_DIR=/data/logs/
NODE_LOG_DIR=$ROOT_LOG_DIR$DIR_NAME
CLASSPATH=.:$JAVA_HOME
LOG=$NODE_LOG_DIR
LOG_GC=$NODE_LOG_DIR/java.gc
CLASS_NAME=com.helipy.biz.service.Application

checkDir(){
  if [ ! -d $1 ] ; then
    mkdir -p $1
  fi
}

checkDir $NODE_LOG_DIR

for f in `find $LIBPATH -name '*.jar'`
do
  CLASSPATH=$CLASSPATH:$f
done

CLASSPATH=$CLASSPATH:$CONFIG_DIR

JAVA_OPTS="-javaagent:${JMX_AGENT_CONF}"
JAVA_OPTS="${JAVA_OPTS} -Xmx${Xmx_HEAPSIZE}m"
JAVA_OPTS="${JAVA_OPTS} -Xms${Xms_HEAPSIZE}m"
JAVA_OPTS="${JAVA_OPTS} -Xss256k"
JAVA_OPTS="${JAVA_OPTS} -XX:+UseG1GC"
JAVA_OPTS="${JAVA_OPTS} -XX:ParallelGCThreads=${CPU_COUNT}"
JAVA_OPTS="${JAVA_OPTS} -XX:MaxGCPauseMillis=100"
JAVA_OPTS="${JAVA_OPTS} -XX:MaxTenuringThreshold=10"
JAVA_OPTS="${JAVA_OPTS} -verbose:gc"
JAVA_OPTS="${JAVA_OPTS} -XX:+PrintGCDetails"
JAVA_OPTS="${JAVA_OPTS} -XX:+PrintGCDateStamps"
JAVA_OPTS="${JAVA_OPTS} -XX:+HeapDumpOnOutOfMemoryError"
JAVA_OPTS="${JAVA_OPTS} -XX:-OmitStackTraceInFastThrow"
JAVA_OPTS="${JAVA_OPTS} -XX:HeapDumpPath=${LOG}"
JAVA_OPTS="${JAVA_OPTS} -Xloggc:${LOG_GC}"
JAVA_OPTS="${JAVA_OPTS} -cp ${CLASSPATH}"
#JAVA_OPTS="${JAVA_OPTS} -Djava.util.logging.manager=org.apache.logging.log4j.jul.LogManager"
JAVA_OPTS="${JAVA_OPTS} -Dlog.dir=${LOG}"
JAVA_OPTS="${JAVA_OPTS} -Djava.util.Arrays.useLegacyMergeSort=true"
JAVA_OPTS="${JAVA_OPTS} -Duser.dir=${NODE_HOME}"
JAVA_OPTS="${JAVA_OPTS} -Dfile.encoding=UTF-8"

echo java $JAVA_OPTS $CLASS_NAME
exec java $JAVA_OPTS $CLASS_NAME
