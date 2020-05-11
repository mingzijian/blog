Docker应用-搭建Redis哨兵集群
----------------------------------
<pre>Redis4.0 一主两从三哨兵</pre>
#### 准备工作
 - 服务器（物理机/云主机/虚拟机）
 - Docker 安装
 - Redis 版本选择
   - 版本查看
   ```shell
    # 在服务器上查看
    $ redis-server -v

    # 在客户端上查看
    > info server
   ```
#### 开始搭建
 - 拉取 Redis 镜像 (注意版本)
    ```shell
    # 拉取
    $ docker pull redis:4.0
    
    # 查看
    $ docker images|grep redis
    ```
 - 启动一‘主’两‘从’共3个容器
    ```shell
    # 启动容器 redis-6379
    $ docker run -p 6380:6379 --name redis-6380 -d redis:4.0
    
    # 启动容器 redis-6380
    $ docker run -p 6381:6379 --name redis-6381 -d redis:4.0
    
    # 启动容器 redis-6381
    $ docker run -p 6381:6379 --name redis-6381 -d redis:4.0
    ```

    ```shell
    # 3个 redis 容器已启动
    $ docker ps |grep redis
    b6f03fca7200        redis:4.0           "docker-entrypoint.s…"   32 seconds ago      Up 31 seconds       0.0.0.0:6381->6379/tcp               redis-6381
    60146c6b3385        redis:4.0           "docker-entrypoint.s…"   39 seconds ago      Up 38 seconds       0.0.0.0:6380->6379/tcp               redis-6380
    67a6bba703fe        redis:4.0           "docker-entrypoint.s…"   5 minutes ago       Up 5 minutes        0.0.0.0:6379->6379/tcp               redis-6379

    # 3个 redis 容器对应的ip
    $ docker inspect --format='{{.NetworkSettings.IPAddress}}' redis-6379
    172.17.0.4
    $ docker inspect --format='{{.NetworkSettings.IPAddress}}' redis-6380
    172.17.0.5
    $ docker inspect --format='{{.NetworkSettings.IPAddress}}' redis-6381
    172.17.0.6

    ```

 - 分别配置3个容器
   - 配置 redis-6379 （作为： Master）(ip:172.17.0.4)
     - 进入容器内部 `docker exec -it redis-6379 /bin/bash`
     - 更换容器内软件源 (默认源相对较慢)
        ```shell
        # 备份（习惯）
        $ cp /etc/apt/sources.list /etc/apt/sources.list_bak
        # 替换 (mirrors.ustc.edu.cn 为 中国科学技术大学源)
        $ sed -i 's/deb.debian.org/mirrors.ustc.edu.cn/g' /etc/apt/sources.list
        ```
      - 容器内安装 vim `apt update` `apt install vim`
      - 配置实例 `vim /data/redis.conf`

      - 配置哨兵 `vim /data/redis-sentinel.conf`

   - 配置 redis-6380 （作为： Slave）(ip:172.17.0.5)
    ```
    logfile "redis.log"
    port 6379
    dir /data
    databases 16
    appendonly yes
    appendfilename appendonly.aof
    # 密码
    requirepass "redisredis"
    # 主数据库连接密码
    masterauth "redisredis"
    # 从库只读
    slave-read-only yes
    # 刷新快照到硬盘中，必须满足两者要求才会触发，即900秒之后至少1个关键字发生变化。
    save 900 1 
    # 必须是300秒之后至少10个关键字发生变化。
    save 300 10 
    # 必须是60秒之后至少10000个关键字发生变化。
    save 60 10000 
    #如果master不能再正常工作，那么会在多个slave中，选择优先值最小的一个slave提升为master，优先值为0表示不能提升为master。
    slave-priority 100 
    ```
   - 配置 redis-6381 （作为： Slave）(ip:172.17.0.6)

#### 参考
 - https://www.cnblogs.com/hutao722/p/9644620.html