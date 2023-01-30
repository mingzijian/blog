Docker操作-目录挂载
------------------------
#### 如何挂载目录？
  Docker容器启动的时候，如果要挂载宿主机的一个目录，可以用-v参数指定。譬如我要启动一个centos容器，宿主机的`/data/test`目录挂载到容器的`/data`目录，可通过以下方式指定：

```shell
$ docker run -it -v /data/test:/data centos /bin/bash
```
 - 挂载规则
   - 宿主机目录如果不存在，则会自动生成
   - 容器目录不可以为相对路径
   - 宿主机目录如果设置为相对路径{relative-path}，挂载目录为：`/var/lib/docker/volumes/{relative-path}/_data`
   - 宿主机目录如果未设置，则容器会在`/var/lib/docker/volumes/`下随机配置一个目录，通过`docker inspect`命令可以查看容器在宿主机上生成的挂载目录。


#### 为何必须挂载 /data 目录？
  有状态容器都有数据持久化需求， 在容器的生命周期内，数据持久化是持续的，包括容器在被停止后，但当容器被删除后，数据也随之被删除了，因此 
 Docker 采用 volume （卷）的形式来向容器提供持久化存储，如果不设置该命令，数据库中的数据会默认保存在 redis 容器中的`/data`目录下，这样当执行` docker rm 容器id/容器name `命令，会丢失数据库中的数据

#### 容器销毁了，在宿主机上新建的挂载目录是否会消失？
  容器销毁了，新建的挂载目录不会消失。


#### 参考
 - https://www.cnblogs.com/ivictor/p/4834864.html