Docker 部署禅道
------------------------

### 环境准备
确保已安装Docker
```shell 
> docker -v
< 
Docker version 18.09.6, build 481bc77
```

### 拉取禅道docker镜像
```shell 
> docker pull idoop/zentao
< 	
Using default tag: latest
latest: Pulling from idoop/zentao
...
```
#### 命令说明
`sudo docker pull [镜像名]:[镜像标签]`
`sudo docker pull idoop/zentao:12.3.2`

#### Docker Hub 镜像版本
- https://hub.docker.com/r/idoop/zentao/tags
- https://hub.docker.com/r/easysoft/zentao/tags

### 启动禅道容器
```shell
> docker run -d -p 8183:80 -p 8184:3306 -e ADMINER_USER="user@name" -e ADMINER_PASSWD="pass@word" -e BIND_ADDRESS="false"  --name zentao idoop/zentao:latest
<
afcd...
```
#### 命令说明

`sudo docker run --name [容器名] -p [主机端口]:80 --network=[网络驱动名] --ip [容器IP] --mac-address [mac地址] -v [主机禅道目录]:/www/zentaopms -v [主机mysql目录]:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=[数据库密码] -d easysoft/zentao:[镜像标签]`
- 容器名：启动的容器名字，可随意指定；
- 主机端口：主机端口为web访问端口；
- 网络驱动名：刚刚创建的网络驱动名；
- 容器IP：在网络驱动范围内选择一个作为该容器的固定ip；
- mac地址：指定固定的mac地址，建议范围为02:42:ac:11:00:00  到  02:42:ac:11:ff:ff；
- 主机禅道目录：必须指定，方便禅道代码、附件等数据的持久化，非升级情况需指定空目录；
- 主机mysql目录：必须指定，方便禅道数据持久化，非升级情况需指定空目录；
- 数据库密码： 容器内置mysql用户名为root,默认密码123456，如果不修改可以不指定该变量，如果想更改密码可以设置 MYSQL_ROOT_PASSWORD变量来更改密码；
- 镜像标签：禅道版本。

### 查看日志
```shell
> docker logs -f zentao
<
Installing Zentao version: 12.3.2 ...
...
```
### WEB访问
http://localhost:port

![zentao-user-login](https://images.gitee.com/uploads/images/2020/1022/181551_f3adcebd_536895.png "zentao-user-login.png")

### 参考
- https://www.zentao.net/book/zentaopmshelp/405.html