Docker 操作  容器
--------------------------------

### 相关概念
#### 镜像
一个特殊的文件系统，只读

搜索镜像
`docker search xxx`

拉取镜像（远端到本地）
`docker pull xxx` 

镜像列表
`docker images`

删除镜像
`docker rmi xxx`

构建镜像
`docker build`

镜像标签
`docker tag`


#### 容器
镜像的实体，可读可写

运行镜像即启动对应容器
`docker run xxx`

查看容器列表
`docker ps -a`

停止容器
`docker stop <容器ID>`


启动（一个已停止的）容器
`docker start <容器ID>`

重启容器
`docker restart <容器ID>`

删除容器
`docker rm -f <容器ID>`

修剪容器(清理所有处于终止状态的容器)
`docker container prune`

进入容器
`docker attach` 退出容器时容器会停止
`docker exec` 退出容器时容器不停止

### 相关命令
- export （导出）
docker export :将文件系统作为一个tar归档文件导出到STDOUT。
`docker export [OPTIONS] CONTAINER`

- import （导入）
docker import : 从归档文件中创建镜像。
`docker import [OPTIONS] file|URL|- [REPOSITORY[:TAG]]`

- save （保存）
docker save : 将指定镜像保存成 tar 归档文件。
`docker save [OPTIONS] IMAGE [IMAGE...]`

- load （载入）
docker load : 导入使用 docker save 命令导出的镜像。
`docker load [OPTIONS]`

### 实操 (以转移showdoc为例)

#### 在源服务器上保存导出容器
```shell
> docker images
<
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
star7th/showdoc     latest              cd345955c0a0        7 weeks ago         395MB

> docker save -o showdoc-save.tar star7th/showdoc

> ls
<
showdoc-save.tar
```
#### 将源服务器上导出的tar文件传输到目标服务器
```shell
scp local_file remote_username@remote_ip:remote_file 
```

#### 在目标服务器上加载导入容器并启动

```shell
> docker load -i showdoc-save.tar

> docker images
<
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
star7th/showdoc     latest              cd345955c0a0        7 weeks ago         395MB

> docker run -d --name showdoc  -p 4999:80 -v /showdoc_data/html:/var/www/html/ star7th/showdoc

> docker ps 
<
CONTAINER ID        IMAGE                    COMMAND                  CREATED             STATUS              PORTS
                                                NAMES
894b62e93f72        star7th/showdoc:latest   "docker-php-entrypoi…"   19 minutes ago      Up 8 seconds        9000/tc p, 0.0.0.0:4999->80/tcp   showdoc
```


### 参考
- https://www.cnblogs.com/bethal/p/5945038.html
- https://www.runoob.com/docker/docker-command-manual.html