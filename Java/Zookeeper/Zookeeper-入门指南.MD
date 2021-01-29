ZooKeeper 入门指南
------------------------------
### 快速开始
#### 下载
- https://zookeeper.apache.org/releases.html#download
```shell
wget http://mirrors.hust.edu.cn/apache/zookeeper/zookeeper-3.6.2/apache-zookeeper-3.6.2-bin.tar.gz
```

#### 单机模式

##### 配置

启动ZooKeeper之前，需要一个配置文件（建议命名为：`conf/zoo.cfg`）。
示例：
```
tickTime=2000
dataDir=/home/data/zk
clientPort=2181
```
以下是每个字段的含义：
- tickTime
	ZooKeeper使用的基本时间单位（毫秒）。它用于做心跳，最小会话超时将是tickTime的两倍。
- dataDir
	存储内存中数据库快照以及数据库更新的事务日志（除非另有指定）的位置。
- clientPort
	监听客户端连接的端口

##### 启动
创建好配置文件后，可以启动ZooKeeper了
```shell
> cd /usr/local/apache-zookeeper-3.6.2-bin/bin
> ./zkServer.sh start
<
ZooKeeper JMX enabled by default
Using config: /usr/local/apache-zookeeper-3.6.2-bin/bin/../conf/zoo.cfg
Starting zookeeper ... STARTED

```


#### 管理ZooKeeper存储
对于长期运行的生产系统，必须在外部管理ZooKeeper存储（dataDir和logs）。
#### 连接到Zookeeper

```shell
> bin/zkCli.sh -server 127.0.0.1:2181
<
Connecting to localhost:2181
log4j:WARN No appenders could be found for logger (org.apache.zookeeper.ZooKeeper).
log4j:WARN Please initialize the log4j system properly.
Welcome to ZooKeeper!
JLine support is enabled
[zkshell: 0]
```

在shell中，键入help以获取可从客户端执行的命令列表，如：
```shell
[zkshell: 0] help
ZooKeeper host:port cmd args
    get path [watch]
    ls path [watch]
    set path data [version]
    delquota [-n|-b] path
    quit
    printwatches on|off
    create path data acl
    stat path [watch]
    listquota path
    history
    setAcl path acl
    getAcl path
    sync path
    redo cmdno
    addauth scheme auth
    delete path [version]
    deleteall path
    setquota -n|-b val path
```

#### Zookeeper编程
ZooKeeper有Java封装和C封装。它们在功能上是等价的。
C封装有两种变体：单线程和多线程。
#### 主从（复制）模式
在单机模式下运行ZooKeeper便于评估，开发和测试。
但在生产中，您应该以主从复制模式运行ZooKeeper。
**注意**
	对于复制模式，至少需要三台服务器，强烈建议您使用奇数个服务器。
	如果您只有两台服务器，当其中一台服务器出现故障时，就没有足够的机器来构成多数选举。
	两台服务器本质上不如单一服务器稳定，因为有两个单点故障。
	建议不要在一个物理服务器上部署多个ZooKeeper，因为一旦物理机宕机，多个ZooKeeper都将脱机。

复制模式下的配置文件和单机模式的有点差异。
示例：
```
tickTime=2000
dataDir=/var/lib/zookeeper
clientPort=2181
initLimit=5
syncLimit=2
server.1=zoo1:2888:3888
server.2=zoo2:2888:3888
server.3=zoo3:2888:3888
```
含义：
- initLimit
	暂停ZooKeeper用于限制选举中ZooKeeper服务器连接到领导者的时间长度（以tickTime为单位）
- syncLimit
	限制服务器与领导者的过期时间长度（以tickTime为单位）
- server.X
	列出构成ZooKeeper服务的服务器。



#### 其他优化
还有一些其他配置参数可以大大提高性能

### 运维指令
问题： xxxx is not executed because it is not in the whitelist.
[解决方案参考](https://blog.csdn.net/x763795151/article/details/80599498)
#### 配置４字运维指令白名单
```shell
> cd /usr/local/apache-zookeeper-3.6.2-bin/bin
> vim zkServer.sh

# 允许全部4字运维指令 (注意位置，别加在其他逻辑语句中) ４字指令的格式是用逗号（,）分隔，也可以直接用*
ZOOMAIN="-Dzookeeper.4lw.commands.whitelist=* ${ZOOMAIN}"

> ./zkServer.sh restart
<
ZooKeeper JMX enabled by default
Using config: /usr/local/apache-zookeeper-3.6.2-bin/bin/../conf/zoo.cfg
ZooKeeper JMX enabled by default
Using config: /usr/local/apache-zookeeper-3.6.2-bin/bin/../conf/zoo.cfg
Stopping zookeeper ... STOPPED
ZooKeeper JMX enabled by default
Using config: /usr/local/apache-zookeeper-3.6.2-bin/bin/../conf/zoo.cfg
Starting zookeeper ... STARTED
```


#### 常用运维指令

[zookeeperAdmin sc_4lw](https://zookeeper.apache.org/doc/r3.6.2/zookeeperAdmin.html#sc_4lw)
4lw: Four Letter Words (四个字母的单词)

- conf (configuration)
	(New in 3.3.0)输出相关服务配置的详细信息。比如端口、zk数据及日志配置路径、最大连接数，session超时时间、serverId等
- cons (connections)
	(New in 3.3.0)列出所有连接到这台服务器的客户端连接/会话的详细信息。包括“接受/发送”的包数量、session id 、操作延迟、最后的操作执行等信息。
- crst (connection_stat_reset)
	(New in 3.3.0)重置当前这台服务器所有连接/会话的统计信息
- dump
	列出未经处理的会话和临时节点（只在leader上有效）。
- envi (environment)
	输出关于服务器的环境详细信息(不同于conf命令)
- ruok (are you ok)
	测试服务是否处于正确运行状态。如果正常返回"imok"，否则返回空。
- srst (statistics reset)
	重置服务器的统计信息
- srvr (server_stats)
	(New in 3.3.0)输出服务器的详细信息。zk版本、接收/发送包数量、连接数、模式（leader/follower）、节点总数。
- stat
	输出服务器的详细信息：接收/发送包数量、连接数、模式（leader/follower）、节点总数、延迟。 所有客户端的列表。
- wchc (watches)
	按会话列出服务器监视的详细信息。
- wchs (watch_summary)
	列出服务器监视的简要信息。
- wchp (watches_by_path)
	按路径列出服务器监视的详细信息。
- dirs (directory size)
	输出有关日志文件目录和快照目录大小的信息（以字节为单位）。返回“datadir_size”和“logdir_size”。
- mntr (monitoring)
	(New in 3.4.0)列出集群的健康状态。包括“接受/发送”的包数量、操作延迟、当前服务模式（leader/follower）、节点总数、watch总数、临时节点总数。
- isro (is_read_only)
	当前服务器是否为只读模式。只读模式时返回： "read_only"
- hash 
- gtmk (Gets the current trace mask)
	以十进制格式获取64位带符号长值的当前跟踪掩码。
- stmk (Sets the current trace mask)
	设置当前跟踪掩码。


##### 使用示例
###### 查看Zookeeper版本号
```shell
# 查看Zookeeper版本号 （依赖nc，CentOS可使用命令`yum install -y nc`安装）
> echo stat | nc localhost 2181 |grep version
<
Zookeeper version: 3.6.2--803c7f1a12f85978cb049af5e4ef23bd8b688715, built on 09/04/2020 12:44 GMT

> echo srvr | nc localhost 2181 |grep version
<
Zookeeper version: 3.6.2--803c7f1a12f85978cb049af5e4ef23bd8b688715, built on 09/04/2020 12:44 GMT
```



### 参考

- https://zookeeper.apache.org
- https://github.com/apache/zookeeper/blob/master/zookeeper-docs/src/main/resources/markdown/zookeeperStarted.md
- https://developer.ibm.com/zh/tutorials/bd-zookeeper/

