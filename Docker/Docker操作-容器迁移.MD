Docker 操作  容器迁移
--------------------------------

### 相关概念
#### 镜像
一个特殊的文件系统，只读
#### 容器
镜像的实体，可读可写
### 相关命令
- export
docker export :将文件系统作为一个tar归档文件导出到STDOUT。
`docker export [OPTIONS] CONTAINER`

- import
docker import : 从归档文件中创建镜像。
`docker import [OPTIONS] file|URL|- [REPOSITORY[:TAG]]`

- save
docker save : 将指定镜像保存成 tar 归档文件。
`docker save [OPTIONS] IMAGE [IMAGE...]`

- load
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