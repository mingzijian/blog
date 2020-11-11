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


### Java Client
#### Curator
Curator是Netflix公司开源的一套zookeeper客户端框架，解决了很多Zookeeper客户端非常底层的细节开发工作，包括连接重连、反复注册Watcher和NodeExistsException异常等等。
```
“Guava is to Java that Curator to Zookeeper”
                                            -- Patrixck Hunt（Zookeeper）
```
##### Maven依赖
```xml
  ...
  <properties>
    <apache-curator.version>2.12.0</apache-curator.version>
    ...
  </properties>
  <dependencies>
      <!-- 框架，对zookeeper的底层api的一些封装 -->
      <dependency>
          <groupId>org.apache.curator</groupId>
          <artifactId>curator-framework</artifactId>
          <version>${apache-curator.version}</version>
      </dependency>
      <!-- 食谱，封装了一些高级特性，如：Cache事件监听、选举、分布式锁、分布式Barrier -->
      <dependency>
          <groupId>org.apache.curator</groupId>
          <artifactId>curator-recipes</artifactId>
          <version>${apache-curator.version}</version>
      </dependency>
      ...
  </dependencies>
```
##### 基本用法
###### 连接管理
- 创建会话


###### 节点管理
- 新增节点
- 查看节点
- 修改节点
- 删除节点
- 事务操作




### 参考

- https://zookeeper.apache.org
- https://github.com/apache/zookeeper/blob/master/zookeeper-docs/src/main/resources/markdown/zookeeperStarted.md
- https://developer.ibm.com/zh/tutorials/bd-zookeeper/

