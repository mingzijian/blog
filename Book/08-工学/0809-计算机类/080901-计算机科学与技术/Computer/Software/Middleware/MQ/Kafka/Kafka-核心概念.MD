Kafka 核心概念
----------------------

### 主题 Topic
Kafka对消息进行归类，发送到集群的每一条消息都要指定一个topic

### 分区 Partition
每个topic包含一个或多个partition。
一个partition对应一个文件夹，这个文件夹下存储partition的数据和索引文件。
每个partition内部是有序的。

### 分片 Broker
消息中间件处理节点（服务器）。
一个节点就是一个broker，一个Kafka集群由一个或多个broker组成

### 生产者 Producer
生产者即数据的发布者，该角色将消息发布到Kafka的topic中。
broker接收到生产者发送的消息后，broker将该消息追加到当前用于追加数据的segment文件中。生产者发送的消息，存储到一个partition中，生产者也可以指定数据存储的partition。

### 消费者 Consumer
消费者可以从broker中读取数据。
消费者可以消费多个topic中的数据。
#### 消费群 ConsumerGroup
每个consumer属于一个特定的consumer group，可为每个consumer指定group name，若不指定，则属于默认的group。
一条消息可以发送到不同的consumer group，但一个consumer group中只能有一个consumer能消费这条消息。

### 集群 Cluster
Kafka 通过 ZooKeeper 管理集群
#### 主 Leader
每个partition有多个副本，其中有且仅有一个作为Leader，Leader是当前负责数据的读写的partition。

#### 从 Follower
Follower跟随Leader，所有写请求都通过Leader路由，数据变更会广播给所有Follower，Follower与Leader保持数据同步。
如果Leader失效，则从Follower中选举出一个新的Leader。
当Follower与Leader挂掉、卡住或者同步太慢，leader会把这个follower从“in sync replicas”（ISR）列表中删除，重新创建一个Follower。