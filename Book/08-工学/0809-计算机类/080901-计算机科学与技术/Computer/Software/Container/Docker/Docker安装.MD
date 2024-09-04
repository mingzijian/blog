[TOC]

推荐使用[podman](https://podman.io/)替代docker

### 在 Linux 上安装
#### 在 Deepin 上安装
##### 前期准备
 - Deepin 镜像下载 https://www.deepin.org/mirrors/releases/
 - Deepin 镜像烧录与系统安装
 - 修改root密码 `sudo passwd root`
 - 使用root账户 `sudo su`
 - Deepin 开放远程端口访问
   - 添加华为源（当无法正常 `apt install` 时）
     ```shell
     # 备份 source.list (养成备份习惯，以防万一)
     $ sudo cp /etc/apt/source.list /etc/apt/source.list.original

     # 编辑 source.list
     $ vim /etc/apt/source.list
     # 添加华为源配置  保存退出 （shift ZZ / :wq ） （vim保存出现 E212 时使用 `: w ! sudo tee %`）
     deb [trusted=yes] https://mirrors.huaweicloud.com/deepin stable main contrib non-free
     
     # 更新
     $ sudo apt update
     ```
  - 安装 openssh-server (已安装请跳过)
    ```shell
    $ apt install openssh-server
    ```
    - 检查 ssh 状态 `service ssh status`
    - 启动 ssh `service ssh start`
    - 修改配置 sshd_config `sudo vim /etc/ssh/sshd_config`

  - 远程连接 （Xshell finalShell terminal）

##### 安装 Docker
 - 依赖检查 (系统内核版本检查，依赖包检查)
   - 显示系统信息 `uname -a`
   - curl (用以从网络上获取 Docker)
      - 检查是否安装 `which curl`
      - 安装 `sudo apt install curl`

 - 获取并安装 Dockers 容器
    - 获取在线脚本安装 (注意 `apt-get` 与 `apt` 区别 )
    ```shell
    $ curl -sSL https://get.docker.com/ | sh
    ```
    - 查看 Docker 版本 `docker -v`

 - 配置 Dockers
   - Docker 服务自启动 `systemctl enable docker.service`
   - Docker 镜像源
     - 国内镜像源
       - Docker中国 http://registry.docker-cn.com
       - 网易 http://hub-mirror.c.16
       - 中国科学技术大学 http://docker.mirrors.ustc.edu.cn
     - 配置方式 `vim /etc/docker/daemon.json`
       ``` 
       {
            "registry-mirrors": [
                "http://registry.docker-cn.com",
                "http://docker.mirrors.ustc.edu.cn",
                "http://hub-mirror.c.163.com"
            ],
            "insecure-registries": [
                "registry.docker-cn.com",
                "docker.mirrors.ustc.edu.cn"
            ],
            "debug": true,
            "experimental": true
        }
       ```
     - 修改后重启生效 `sudo systemctl daemon-reload` or `systemctl restart docker`



#### 在 CentOS 上安装
##### 前期准备
 - CentOS 镜像下载 http://isoredirect.centos.org/

##### 安装 Docker
 - 依赖检查 
   ```shell
   $ uname -r 
   ```
 - 获取并安装 Dockers 容器
   - 卸载旧版本
     ```shell
        > sudo yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine
     ```
   - 安装并使用仓库
     ```shell
        > sudo yum install -y yum-utils
        > sudo yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo
     
     ```
   - 安装Docker引擎
    ```shell
        > sudo yum install docker-ce docker-ce-cli containerd.io
    ```
 - 配置
   - 启动Docker
    ```shell
        > sudo systemctl start docker
    ```
   - 运行镜像
     ```shell
        > sudo docker images
        > sudo docker run hello-world
     ```

##### 安装 docker-compose
[官方教程](https://docs.docker.com/compose/install/)
```shell
> sudo curl -L "https://github.com/docker/compose/releases/download/1.29.1/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

> sudo chmod +x /usr/local/bin/docker-compose

> sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose

> docker-compose --version
docker-compose version 1.29.1, build c34c88b2
```



### 在 Windows 上安装
[Get Docker Desktop for Windows](https://desktop.docker.com/win/stable/Docker%20Desktop%20Installer.exe)

### 在 Mac 上安装
[Get Docker Desktop for Mac](https://desktop.docker.com/mac/stable/Docker.dmg)

