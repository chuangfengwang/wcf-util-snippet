#!/bin/bash

export LANG=zh_CN.UTF-8
export JAVA_FILE_ENCODING=UTF-8
export NLS_LANG=AMERICAN_AMERICA.ZHS16GBK
export CPU_COUNT="$(grep -c 'cpu[0-9][0-9]*' /proc/stat)"
ulimit -c unlimited

# 启动类
CLASS_NAME=com.helipy.biz.Application

# 检测必要参数
checkRequireEnvParam() {
  if [ -z $2 ]
    then
      echo "参数:[$1]=[$2], 值为空"
      exit 1
    else
      echo "非空"
  fi
}

mypwd=$(cd `dirname "${BASH_SOURCE[0]}"` && pwd)/`basename "${BASH_SOURCE[0]}"`
mypwd=`dirname $mypwd`

NODE_HOME=$mypwd
DIR_NAME="${NODE_HOME##*/}"
CONFIG_DIR=$NODE_HOME/conf
LIBPATH=$NODE_HOME/lib
ROOT_LOG_DIR=/data/logs/
NODE_LOG_DIR=$ROOT_LOG_DIR$DIR_NAME
CLASSPATH=.:$JAVA_HOME
LOG=$NODE_LOG_DIR
LOG_GC=$NODE_LOG_DIR/java.gc

echo "----- User Define Env Param -----"
echo "XMX_HEAPSIZE: $XMX_HEAPSIZE"
echo "XMS_HEAPSIZE: $XMS_HEAPSIZE"
echo "CLASS_NAME: $CLASS_NAME"
if [[ -n ${CONC_GC_THREADS} ]];then
  echo "use user define ConcGCThreads = ${CONC_GC_THREADS}"
else
  CONC_GC_THREADS=1
  echo "use default ConcGCThreads = ${CONC_GC_THREADS}"
fi
if [[ -n ${ZCollectionInterval} ]];then
  echo "use user define ZCollectionInterval = ${ZCollectionInterval}"
else
  ZCollectionInterval=60
  echo "use default ZCollectionInterval = ${ZCollectionInterval}"
fi
if [[ -n ${ZAllocationSpikeTolerance} ]];then
  echo "use user define ZAllocationSpikeTolerance = ${ZAllocationSpikeTolerance}"
else
  ZAllocationSpikeTolerance=2
  echo "use default ZAllocationSpikeTolerance = ${ZAllocationSpikeTolerance}"
fi
checkRequireEnvParam CLASS_NAME $CLASS_NAME
checkRequireEnvParam XMX_HEAPSIZE $XMX_HEAPSIZE
checkRequireEnvParam XMS_HEAPSIZE $XMS_HEAPSIZE
checkRequireEnvParam CONC_GC_THREADS $CONC_GC_THREADS
checkRequireEnvParam ZCollectionInterval $ZCollectionInterval
checkRequireEnvParam ZAllocationSpikeTolerance $ZAllocationSpikeTolerance

echo "----- Auto Define Env Param -----"
echo "LANG: $LANG"
echo "JAVA_FILE_ENCODING: $JAVA_FILE_ENCODING"
echo "NLS_LANG: $NLS_LANG"
echo "CPU_COUNT: $CPU_COUNT"
echo "NODE_HOME: $NODE_HOME"
echo "LOG: $LOG"
echo "LOG_GC: $LOG_GC"

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

#JAVA_OPTS="${JAVA_OPTS} -javaagent:${JMX_AGENT_CONF}"
JAVA_OPTS="${JAVA_OPTS} -Xmx${XMX_HEAPSIZE}m"
JAVA_OPTS="${JAVA_OPTS} -Xms${XMS_HEAPSIZE}m"
JAVA_OPTS="${JAVA_OPTS} -Xss256k"
JAVA_OPTS="${JAVA_OPTS} -XX:+UnlockDiagnosticVMOptions"
JAVA_OPTS="${JAVA_OPTS} -XX:+UseZGC"
JAVA_OPTS="${JAVA_OPTS} -XX:+ZGenerational"
JAVA_OPTS="${JAVA_OPTS} -XX:ZAllocationSpikeTolerance=${ZAllocationSpikeTolerance}"
JAVA_OPTS="${JAVA_OPTS} -XX:ZCollectionInterval=${ZCollectionInterval}"
JAVA_OPTS="${JAVA_OPTS} -XX:ConcGCThreads=${CONC_GC_THREADS}"
JAVA_OPTS="${JAVA_OPTS} -XX:ZUncommitDelay=120"
# 没有这个选项，JIT编译器可能会优化掉for循环处的safepoint，那么直到循环结束线程才能进入safepoint，而加了这个参数后，每次for循环都能进入safepoint了，建议加上此选项
JAVA_OPTS="${JAVA_OPTS} -XX:+UseCountedLoopSafepoints"
JAVA_OPTS="${JAVA_OPTS} -XX:+HeapDumpOnOutOfMemoryError"
JAVA_OPTS="${JAVA_OPTS} -XX:HeapDumpPath=${LOG}"
JAVA_OPTS="${JAVA_OPTS} -XX:+PrintVMOptions"
JAVA_OPTS="${JAVA_OPTS} -Xlog:async"
JAVA_OPTS="${JAVA_OPTS} -XX:AsyncLogBufferSize=2097152"
JAVA_OPTS="${JAVA_OPTS} -Xlog:gc+init,gc+start,gc+task,gc+phases,gc+ref,gc+heap,gc=info:file=${LOG_GC}-%t.log:time,tid,tags:filecount=3,filesize=20m"
JAVA_OPTS="${JAVA_OPTS} -Xlog:safepoint=info:file=${LOG_SAFE_POINT}-%t.log:time,tid,tags:filecount=3,filesize=20m"
JAVA_OPTS="${JAVA_OPTS} -cp ${CLASSPATH}"
JAVA_OPTS="${JAVA_OPTS} --add-opens java.base/java.lang=ALL-UNNAMED"
JAVA_OPTS="${JAVA_OPTS} --add-opens java.base/java.lang.reflect=ALL-UNNAMED"
JAVA_OPTS="${JAVA_OPTS} --add-opens java.base/java.math=ALL-UNNAMED"
JAVA_OPTS="${JAVA_OPTS} --add-opens java.base/java.nio=ALL-UNNAMED"
JAVA_OPTS="${JAVA_OPTS} --add-opens java.base/java.util=ALL-UNNAMED"
JAVA_OPTS="${JAVA_OPTS} --add-opens java.base/java.text=ALL-UNNAMED"
JAVA_OPTS="${JAVA_OPTS} --add-opens java.base/java.net=ALL-UNNAMED"
JAVA_OPTS="${JAVA_OPTS} --add-opens java.base/java.util.concurrent=ALL-UNNAMED"
JAVA_OPTS="${JAVA_OPTS} --add-opens java.sql/java.sql=ALL-UNNAMED"
JAVA_OPTS="${JAVA_OPTS} --add-opens java.base/jdk.internal.access.foreign=ALL-UNNAMED"
JAVA_OPTS="${JAVA_OPTS} --add-opens java.management/sun.management=ALL-UNNAMED"
JAVA_OPTS="${JAVA_OPTS} -Dlog.dir=${LOG}"
JAVA_OPTS="${JAVA_OPTS} -Duser.dir=${NODE_HOME}"
JAVA_OPTS="${JAVA_OPTS} -Dfile.encoding=UTF-8"
JAVA_OPTS="${JAVA_OPTS} -Ddubbo.application.qos.enable=false"
JAVA_OPTS="${JAVA_OPTS} -Dio.netty.tryReflectionSetAccessible=true"

echo java $JAVA_OPTS $CLASS_NAME
exec java $JAVA_OPTS $CLASS_NAME
