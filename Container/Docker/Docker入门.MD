Docker 入门
------------------

Docker是一个用于开发、发布和运行应用程序的开放平台。

Docker使您能够将应用程序与基础结构分离，以便快速交付软件。
使用Docker，您可以用与管理应用程序相同的方式管理基础设施。
通过利用Docker的方法快速发布、测试和部署代码，您可以显著减少编写代码和在生产环境中运行代码之间的延迟。


### Docker 流程

![Docker Flow](https://gitee.com/mingzijian/resources/raw/master/picgo/2074831-20201129203506049-178243366.png)

### Docker 架构

![Docker Architecture Diagram](https://gitee.com/mingzijian/resources/raw/master/picgo/architecture.svg)


### Docker 对象
Images / Containers
#### Images （镜像）
镜像是只读模板，其中包含创建Docker容器的说明。

#### Containers (容器)
容器是镜像的可运行实例。

### Docker 模块
Docker 的两个主要组成模块是： Docker Daemon 和 Docker CLI。
#### Docker Daemon
一个常驻的后台进程，帮助管理和创建 Docker 镜像、容器、网络和存储卷。

#### Docker CLI
一个用来与 Docker 守护进程进行交互的 Docker 命令行客户端，也就是 Docker 命令。

Docker 命令:

| 命令                                                         | 描述                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [docker attach](https://docs.docker.com/engine/reference/commandline/attach/) | 将本地标准输入、输出和错误流附加到正在运行的容器             |
| [docker build](https://docs.docker.com/engine/reference/commandline/build/) | 从Dockerfile生成镜像                                         |
| [docker builder](https://docs.docker.com/engine/reference/commandline/builder/) | 管理构建                                                     |
| [docker checkpoint](https://docs.docker.com/engine/reference/commandline/checkpoint/) | 管理检查点                                                   |
| [docker commit](https://docs.docker.com/engine/reference/commandline/commit/) | 根据容器的更改创建新镜像                                     |
| [docker config](https://docs.docker.com/engine/reference/commandline/config/) | 管理配置                                                     |
| [docker container](https://docs.docker.com/engine/reference/commandline/container/) | 管理容器                                                     |
| [docker context](https://docs.docker.com/engine/reference/commandline/context/) | 管理上下文                                                   |
| [docker cp](https://docs.docker.com/engine/reference/commandline/cp/) | 在容器和本地文件系统之间复制文件/文件夹                      |
| [docker create](https://docs.docker.com/engine/reference/commandline/create/) | 创建一个新容器                                               |
| [docker diff](https://docs.docker.com/engine/reference/commandline/diff/) | 检查对容器文件系统上的文件或目录所做的更改                   |
| [docker events](https://docs.docker.com/engine/reference/commandline/events/) | 从服务器获取实时事件                                         |
| [docker exec](https://docs.docker.com/engine/reference/commandline/exec/) | 在正在运行的容器中运行命令                                   |
| [docker export](https://docs.docker.com/engine/reference/commandline/export/) | 将容器的文件系统导出为tar存档                                |
| [docker history](https://docs.docker.com/engine/reference/commandline/history/) | 显示镜像历史记录                                             |
| [docker image](https://docs.docker.com/engine/reference/commandline/image/) | 管理镜像                                                     |
| [docker images](https://docs.docker.com/engine/reference/commandline/images/) | 镜像列表                                                     |
| [docker import](https://docs.docker.com/engine/reference/commandline/import/) | 从tarball导入内容以创建文件系统镜像                          |
| [docker info](https://docs.docker.com/engine/reference/commandline/info/) | 显示系统范围的信息                                           |
| [docker inspect](https://docs.docker.com/engine/reference/commandline/inspect/) | 返回Docker对象的低级信息                                     |
| [docker kill](https://docs.docker.com/engine/reference/commandline/kill/) | 终结一个或多个运行中的容器                                   |
| [docker load](https://docs.docker.com/engine/reference/commandline/load/) | 从tar存档或STDIN(标准输入)加载图像                           |
| [docker login](https://docs.docker.com/engine/reference/commandline/login/) | 登入Docker注册表                                             |
| [docker logout](https://docs.docker.com/engine/reference/commandline/logout/) | 登出Docker注册表                                             |
| [docker logs](https://docs.docker.com/engine/reference/commandline/logs/) | 取容器日志                                                   |
| [docker manifest](https://docs.docker.com/engine/reference/commandline/manifest/) | 管理Docker镜像清单和清单列表                                 |
| [docker network](https://docs.docker.com/engine/reference/commandline/network/) | 管理网络                                                     |
| [docker node](https://docs.docker.com/engine/reference/commandline/node/) | Manage Swarm nodes                                           |
| [docker pause](https://docs.docker.com/engine/reference/commandline/pause/) | 暂停一个或多个容器中的所有进程                               |
| [docker plugin](https://docs.docker.com/engine/reference/commandline/plugin/) | 管理插件                                                     |
| [docker port](https://docs.docker.com/engine/reference/commandline/port/) | 列出端口映射或容器的特定映射                                 |
| [docker ps](https://docs.docker.com/engine/reference/commandline/ps/) | 容器列表                                                     |
| [docker pull](https://docs.docker.com/engine/reference/commandline/pull/) | 从注册表中提取镜像或存储库                                   |
| [docker push](https://docs.docker.com/engine/reference/commandline/push/) | 将镜像或存储库推送到注册表                                   |
| [docker rename](https://docs.docker.com/engine/reference/commandline/rename/) | 重命名容器                                                   |
| [docker restart](https://docs.docker.com/engine/reference/commandline/restart/) | 重启一个或多个容器                                           |
| [docker rm](https://docs.docker.com/engine/reference/commandline/rm/) | 移除一个或多个容器                                           |
| [docker rmi](https://docs.docker.com/engine/reference/commandline/rmi/) | 移除一个或多个镜像                                           |
| [docker run](https://docs.docker.com/engine/reference/commandline/run/) | 在新容器中运行命令                                           |
| [docker save](https://docs.docker.com/engine/reference/commandline/save/) | 将一个或多个镜像保存到tar存档（默认情况下流式传输到STDOUT）  |
| [docker search](https://docs.docker.com/engine/reference/commandline/search/) | 在Docker Hub中搜索镜像                                       |
| [docker secret](https://docs.docker.com/engine/reference/commandline/secret/) | Manage Docker secrets                                        |
| [docker service](https://docs.docker.com/engine/reference/commandline/service/) | 管理服务                                                     |
| [docker stack](https://docs.docker.com/engine/reference/commandline/stack/) | 管理Docker栈                                                 |
| [docker start](https://docs.docker.com/engine/reference/commandline/start/) | 启动一个或多个停止的容器                                     |
| [docker stats](https://docs.docker.com/engine/reference/commandline/stats/) | 显示容器资源使用统计信息的实时流                             |
| [docker stop](https://docs.docker.com/engine/reference/commandline/stop/) | 停止一个或多个停止的容器                                     |
| [docker swarm](https://docs.docker.com/engine/reference/commandline/swarm/) | Manage Swarm                                                 |
| [docker system](https://docs.docker.com/engine/reference/commandline/system/) | 管理Docker                                                   |
| [docker tag](https://docs.docker.com/engine/reference/commandline/tag/) | 创建标签                                                     |
| [docker top](https://docs.docker.com/engine/reference/commandline/top/) | 显示容器的运行进程                                           |
| [docker trust](https://docs.docker.com/engine/reference/commandline/trust/) | Manage trust on Docker images                                |
| [docker unpause](https://docs.docker.com/engine/reference/commandline/unpause/) | 取消暂停一个或多个容器中的所有进程                           |
| [docker update](https://docs.docker.com/engine/reference/commandline/update/) | 更新一个或多个容器的配置                                     |
| [docker version](https://docs.docker.com/engine/reference/commandline/version/) | 显示Docker版本信息                                           |
| [docker volume](https://docs.docker.com/engine/reference/commandline/volume/) | 管理卷                                                       |
| [docker wait](https://docs.docker.com/engine/reference/commandline/wait/) | 阻塞直到一个或多个容器停止，然后打印其退出代码（exit codes） |


### Dockerfile
Dockerfile 是一个用来构建镜像的文本文件，文本内容包含了一条条构建镜像所需的指令和说明。

### Docker Compose
Compose 是用于定义和运行多容器 Docker 应用程序的工具。

通过 Compose，您可以使用 YML 文件来配置应用程序需要的所有服务。

然后，使用一个命令，就可以从 YML 文件配置中创建并启动所有服务。

Compose 使用的三个步骤：
 1. 使用 Dockerfile 定义应用程序的环境。
 2. 使用 docker-compose.yml 定义构成应用程序的服务，这样它们可以在隔离环境中一起运行。
 3. 最后，执行 docker-compose up 命令来启动并运行整个应用程序。


### 参考
 - [docker docs](https://docs.docker.com/)