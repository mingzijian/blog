CentOS 安装 ZooKeeper
------------------------------------


### 查看版本
```
echo stat|nc localhost 2181|grep version
Zookeeper version: 3.4.6-1569965, built on 02/20/2014 09:09 GMT
```

### 下载解压

[Index of /dist/zookeeper (apache.org)](http://archive.apache.org/dist/zookeeper/)

```
cd /usr/local
wget http://archive.apache.org/dist/zookeeper/zookeeper-3.4.6/zookeeper-3.4.6.tar.gz

tar -zxvf zookeeper-3.4.6.tar.gz

```

### 配置启动
```
cd /usr/local/zookeeper-3.4.6

vim conf/zoo.cfg
  1 # The number of milliseconds of each tick
  2 tickTime=2000
  3 # The number of ticks that the initial 
  4 # synchronization phase can take
  5 initLimit=10
  6 # The number of ticks that can pass between 
  7 # sending a request and getting an acknowledgement
  8 syncLimit=5
  9 # the directory where the snapshot is stored.
 10 # do not use /tmp for storage, /tmp here is just 
 11 # example sakes.
 12 dataDir=/usr/local/zookeeper-3.4.6/data
 13 dataLogDir=/usr/local/zookeeper-3.4.6/logs
 14 # the port at which the clients will connect
 15 clientPort=2181
 16 # the maximum number of client connections.
 17 # increase this if you need to handle more clients
 18 #maxClientCnxns=60
 19 #
 20 # Be sure to read the maintenance section of the 
 21 # administrator guide before turning on autopurge.
 22 #
 23 # http://zookeeper.apache.org/doc/current/zookeeperAdmin.html#sc_maintenance
 24 #
 25 # The number of snapshots to retain in dataDir
 26 #autopurge.snapRetainCount=3
 27 # Purge task interval in hours
 28 # Set to "0" to disable auto purge feature
 29 #autopurge.purgeInterval=1
 30 skipACL=yes

zkServer.sh start

```