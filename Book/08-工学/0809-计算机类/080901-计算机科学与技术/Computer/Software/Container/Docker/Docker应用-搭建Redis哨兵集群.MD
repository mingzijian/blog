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

 - 创建 Redis 挂载配置文件 （创建一个总目录 `mkdir -p /data/redis`）
   - 3 个 server 节点
       - 创建 redis-server-6379 主服务配置 (创建目录 `mkdir -p /data/redis/redis-server-6379-data`) 
         `vim /data/redis/redis-server-6379-data/redis.conf`
            ```
            logfile "redis.log"
            port 6379
            dir /data
            databases 16
            daemonize no
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
       - 创建 redis-server-6380 从服务配置 (创建目录 `mkdir /data/redis/redis-server-6380-data`)
         `vim /data/redis/redis-server-6380-data/redis.conf`
            ```
            logfile "redis.log"
            port 6380
            dir /data
            databases 16
            daemonize no
            appendonly yes
            appendfilename appendonly.aof
            slaveof 172.17.0.4 6379
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
       - 创建 redis-server-6381 从服务配置 (创建目录 `mkdir /data/redis/redis-server-6381-data`)
         `vim /data/redis/redis-server-6381-data/redis.conf`
            ```
            logfile "redis.log"
            port 6381
            dir /data
            databases 16
            daemonize no
            appendonly yes
            appendfilename appendonly.aof
            slaveof 172.17.0.4 6379
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

   - 3 个 sentinel 节点
       - 创建 redis-sentinel-26379 哨兵配置 (创建目录 `mkdir /data/redis/redis-sentinel-26379-data`)
         `vim /data/redis/redis-sentinel-26379-data/redis-sentinel.conf`
            ```
            logfile "sentinel.log"
            port 26379
            # 哨兵监控的主服务器名称为mymaster，ip为172.17.0.4，端口为6379，将这个主服务器标记为失效至少需要2个哨兵进程的同意
            sentinel monitor mymaster 172.17.0.4 6379 2
            sentinel auth-pass mymaster "redisredis"
            daemonize no
            ```
       - 创建 redis-sentinel-26380 哨兵配置 (创建目录 `mkdir /data/redis/redis-sentinel-26380-data`)
         `vim /data/redis/redis-sentinel-26380-data/redis-sentinel.conf`
            ```
            logfile "sentinel.log"
            port 26379
            # 哨兵监控的主服务器名称为mymaster，ip为172.17.0.4，端口为6379，将这个主服务器标记为失效至少需要2个哨兵进程的同意
            sentinel monitor mymaster 172.17.0.4 6379 2
            sentinel auth-pass mymaster "redisredis"
            daemonize no
            ```
       - 创建 redis-sentinel-26381 哨兵配置 (创建目录 `mkdir /data/redis/redis-sentinel-26381-data`)
         `vim /data/redis/redis-sentinel-26381-data/redis-sentinel.conf`
            ```
            logfile "sentinel.log"
            port 26379
            # 哨兵监控的主服务器名称为mymaster，ip为172.17.0.4，端口为6379，将这个主服务器标记为失效至少需要2个哨兵进程的同意
            sentinel monitor mymaster 172.17.0.4 6379 2
            sentinel auth-pass mymaster "redisredis"
            daemonize no
            ```


 - 启动一‘主’两‘从’三‘哨兵’共6个容器 （ Redis 服务节点容器内部端口：6379 ；Redis 哨兵节点容器内部端口：26379 ）

    ```shell
    # 启动容器 redis-server-6379 主 172.17.0.4
    $ docker run -p 6379:6379 -v /data/redis/redis-server-6379-data/:/data --name redis-server-6379 -d redis:4.0 redis-server /data/redis.conf
    
    # 启动容器 redis-server-6380 从1 172.17.0.5
    $ docker run -p 6380:6379 -v /data/redis/redis-server-6380-data/:/data --name redis-server-6380 -d redis:4.0 redis-server /data/redis.conf
    
    # 启动容器 redis-server-6381 从2 172.17.0.6
    $ docker run -p 6381:6379 -v /data/redis/redis-server-6381-data/:/data --name redis-server-6381 -d redis:4.0 redis-server /data/redis.conf

    # 启动容器 redis-sentinel-26379 哨兵1 172.17.0.7
    $ docker run -p 26379:26379 -v /data/redis/redis-sentinel-26379-data/:/data --name redis-sentinel-26379 -d redis:4.0 redis-sentinel /data/redis-sentinel.conf

    # 启动容器 redis-sentinel-26380 哨兵2 172.17.0.8
    $ docker run -p 26380:26379 -v /data/redis/redis-sentinel-26380-data/:/data --name redis-sentinel-26380 -d redis:4.0 redis-sentinel /data/redis-sentinel.conf

    # 启动容器 redis-sentinel-26381 哨兵3 172.17.0.9
    $ docker run -p 26381:26379 -v /data/redis/redis-sentinel-26381-data/:/data --name redis-sentinel-26381 -d redis:4.0 redis-sentinel /data/redis-sentinel.conf


    # 6个 redis 容器已启动
    $ docker ps |grep redis
    
    # 6个 redis 容器对应的ip
    $ docker inspect --format='{{.NetworkSettings.IPAddress}}' redis-server-6379
    172.17.0.4
    $ docker inspect --format='{{.NetworkSettings.IPAddress}}' redis-server-6380
    172.17.0.5
    $ docker inspect --format='{{.NetworkSettings.IPAddress}}' redis-server-6381
    172.17.0.6
    $ docker inspect --format='{{.NetworkSettings.IPAddress}}' redis-sentinel-26379
    172.17.0.7
    $ docker inspect --format='{{.NetworkSettings.IPAddress}}' redis-sentinel-26380
    172.17.0.8
    $ docker inspect --format='{{.NetworkSettings.IPAddress}}' redis-sentinel-26381
    172.17.0.9
    
    ```

#### 参考
 - [构建Redis哨兵集群](https://www.cnblogs.com/hutao722/p/9644620.html)