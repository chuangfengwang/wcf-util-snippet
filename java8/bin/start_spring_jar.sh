#!/bin/bash
APP_NAME=$1
ACTION=$2

#创建日志文件
LOG_DIR=${app_logs:-./logs/app}
mkdir -p $LOG_DIR

#配置jvm启动参数
export LANG=zh_CN.UTF-8
export JAVA_FILE_ENCODING=UTF-8
export NLS_LANG=AMERICAN_AMERICA.ZHS16GB
export CPU_COUNT="$(grep -c 'cpu[0-9][0-9]*' /proc/stat)"
export TZ=Asia/Shanghai
ulimit -c unlimited

JAVA_OPTS="-server"

#配置jvm内存
let memTotal=`cat /sys/fs/cgroup/memory/memory.limit_in_bytes`
if [ $memTotal -ne 9223372036854771712 ]; then
    memTotal=$(expr ${memTotal} / 1048576)
    memTotal30=$(expr $memTotal \* 3 / 10)
    limitMem=$memTotal
    if [ $memTotal -gt 1024  ] &&  [ $memTotal -lt 3072 ]; then
       limitMem=$(expr $memTotal - 700 )
    elif [ $memTotal -ge 3072  ] && [ $memTotal -le 4096 ]; then
       limitMem=$(expr $memTotal - 1056 )
    elif [ $memTotal -gt 4096 ]; then
       # 留30%内存,避免内存报警
       #limitMem=$(expr $memTotal - 1512 )
       limitMem=$(expr $memTotal - $memTotal30)
    elif [ $memTotal -ge 512 ]; then
       limitMem=$(expr $memTotal - 370 )
    else
       echo "warning limit mix memory is allowed more then 512m"
       exit
    fi
    JAVA_OPTS="${JAVA_OPTS} -Xms${limitMem}m -Xmx${limitMem}m"
    let mnVal="$(expr ${limitMem} / 2 )"
    JAVA_OPTS="${JAVA_OPTS} -Xmn${mnVal}m"
else
    JAVA_OPTS="${JAVA_OPTS} -Xms2g -Xmx2g"
    JAVA_OPTS="${JAVA_OPTS} -Xmn1g"
fi
#配置jvm其他参数
JAVA_OPTS="${JAVA_OPTS} -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=512m"

#配置jvm 堆外内存
#JAVA_OPTS="${JAVA_OPTS} -XX:MaxDirectMemorySize=1g"
#配置晋升代数
JAVA_OPTS="${JAVA_OPTS} -XX:SurvivorRatio=10"
JAVA_OPTS="${JAVA_OPTS} -XX:+UseConcMarkSweepGC -XX:CMSMaxAbortablePrecleanTime=5000"
JAVA_OPTS="${JAVA_OPTS} -XX:+CMSClassUnloadingEnabled -XX:CMSInitiatingOccupancyFraction=80 -XX:+UseCMSInitiatingOccupancyOnly"
JAVA_OPTS="${JAVA_OPTS} -XX:+ExplicitGCInvokesConcurrent -Dsun.rmi.dgc.server.gcInterval=2592000000 -Dsun.rmi.dgc.client.gcInterval=2592000000"
JAVA_OPTS="${JAVA_OPTS} -XX:ParallelGCThreads=${CPU_COUNT}"
JAVA_OPTS="${JAVA_OPTS} -Djava.awt.headless=true"
JAVA_OPTS="${JAVA_OPTS} -Dsun.net.client.defaultConnectTimeout=10000"
JAVA_OPTS="${JAVA_OPTS} -Dsun.net.client.defaultReadTimeout=30000"
JAVA_OPTS="${JAVA_OPTS} -Dfile.encoding=UTF-8"
JAVA_OPTS="${JAVA_OPTS} -Xloggc:${LOG_DIR}/gc.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps"
JAVA_OPTS="${JAVA_OPTS} -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${LOG_DIR}/java.hprof"
JAVA_OPTS="${JAVA_OPTS} -Dlog.dir=${LOG_DIR}"
export JAVA_OPTS


startjava() {
    echo " start java process"
    echo "java -jar -javaagent:$JMX_AGENT_CONF $JAVA_OPTS $APP_NAME"

    #使用 exec 来确保java进程为1号进程
    #-javaagent:$JMX_AGENT_CONF 是用来启动jvm监控的，需要镜像中已经配有相关agent和env。其他base镜像需要删除这个配置。
    exec java -jar -javaagent:$JMX_AGENT_CONF $JAVA_OPTS $APP_NAME

}


case "$ACTION" in
    start)
        if [ -f $APP_NAME ]; then
           startjava
        fi
    ;;
esac

# 上面是启动脚本内容, 启动命令填:
# sh start.sh app.jar start
