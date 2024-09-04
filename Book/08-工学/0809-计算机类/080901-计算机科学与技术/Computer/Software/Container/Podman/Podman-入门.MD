Podman 入门
--------------------

Podman 是一个无守护进程的容器引擎。
用于在 Linux 系统上进行开发、管理和运行 OCI Containers。 
Containers 能以 root 模式运行，也能以非 root 模式运行。
Podman 直接与镜像注册表、容器和镜像存储进行交互。
Podman 兼容 Docker (镜像/命令`alias docker=podman`)。

 ![Podman](https://gitee.com/mingzijian/resources/raw/master/picgo/2074831-20201129203558088-125133373.png)

 ### 安装
 ```shell
> yum list podman
...
podman.x86_64                                                               2.2.1-7.module_el8.3.0+699+d61d9c41                                                                @appstream
> yum install -y podman.x86_64
...
Complete!

> podman -v
podman version 2.2.1

> yum install -y podman-compose
...
Complete!

> podman-compose version
using podman version: podman version 2.2.1
podman-composer version  0.1.7dev


```

### 镜像加速
`vim /etc/containers/registries.conf`


### 参考

 - [官网](https://podman.io)