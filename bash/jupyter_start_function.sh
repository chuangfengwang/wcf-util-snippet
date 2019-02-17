#!/usr/bin/env bash

function start_jupyter_notebook(){
# 启动 jupyter notebook, 在 8888 端口上

local jupyter_pid_file=~/.jupyter/jupyter.pid

# 查看 pid 是不是存在
local run_jupyter_pid=$(ps -f -p $(cat ${jupyter_pid_file}) | grep bin/anaconda/anaconda3/bin/jupyter-notebook | awk '{print $2}')

if [ "${run_jupyter_pid}" == '' ]
then
    # 不存在,启动
    nohup jupyter notebook  --port 8888 >> ~/.jupyter/jupyter.log 2>&1 & echo $! > ${jupyter_pid_file} &
    # echo 'start jupyter notebook'
# else
    # 存在,啥也不做
    # echo 'jupyter notebook is running'
fi

}


# set Anaconda3
source ~/bin/anaconda/anaconda3/etc/profile.d/conda.sh
conda activate base


# start jupyter notebook
start_jupyter_notebook
