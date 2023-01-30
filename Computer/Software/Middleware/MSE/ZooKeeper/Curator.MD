Curator
-----------

Curator是Netflix公司开源的一套zookeeper客户端框架，解决了很多Zookeeper客户端非常底层的细节开发工作，包括连接重连、反复注册Watcher和NodeExistsException异常等等。
```
“Guava is to Java that Curator to Zookeeper”
                                            -- Patrixck Hunt（Zookeeper）
```
### Maven依赖
```xml
  ...
  <properties>
    <apache-curator.version>5.0.0</apache-curator.version>
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
### 基本用法
#### 连接管理
- 创建会话
```java
    int baseSleepTimeMs = 1000;
    int maxRetries = 5;
    String zkServers = "81.69.28.50:2181";
    String namespace = "base";
    int sessionTimeoutMs = 5000;
    int connectionTimeoutMs = 5000;

    RetryPolicy retryPolicy = new ExponentialBackoffRetry(baseSleepTimeMs, maxRetries);
    CuratorFramework client =
        CuratorFrameworkFactory.builder()
            .connectString(zkServers)
            .sessionTimeoutMs(sessionTimeoutMs)
            .connectionTimeoutMs(connectionTimeoutMs)
            .retryPolicy(retryPolicy)
            .namespace(namespace)
            .build();

    client.start();
```

#### 节点管理
- 新增节点 `client.create()`
  - 递归创建所需父节点
  - 指定节点类型（临时/持久）
  - 节点路径，节点数据
  - 异步创建
- 查看节点 `client.getData()`
  - 节点是否存在 `client.checkExists()`
  - 读取节点数据
  - 获取子节点路径 `client.getChildren()`
  - 节点状态
- 修改节点 `client.setData()`
  - 指定版本号
- 删除节点 `client.delete() `
  - 强制删除
  - 递归删除子节点
  - 指定版本号
- 事务操作 `client.inTransaction()`

### Methods 方法

| 方法        | 描述 |
| --------------- | ------------------------------------------------------------ |
| create()        | 创建<br>Begins a create operation. Call additional methods (mode or background) and finalize the operation by calling forPath() |
| delete()        | 删除<br>Begins a delete operation. Call additional methods (version or background) and finalize the operation by calling forPath() |
| checkExists()   | 检查ZNode是否存在<br>Begins an operation to check that a ZNode exists. Call additional methods (watch or background) and finalize the operation by calling forPath() |
| getData()       | 获取ZNode数据<br>Begins an operation to get a ZNode's data. Call additional methods (watch, background or get stat) and finalize the operation by calling forPath() |
| setData()       | 设置ZNode的数据<br>Begins an operation to set a ZNode's data. Call additional methods (version or background) and finalize the operation by calling forPath() |
| getChildren()   | 获取ZNode的子ZNode列表<br>Begins an operation to get a ZNode's list of children ZNodes. Call additional methods (watch, background or get stat) and finalize the operation by calling forPath() |
| transactionOp() | Used to allocate operations to be used with transaction().   |
| transaction()   | Atomically submit a set of operations as a transaction.      |
| getACL()        | 获取ACL (ACL：Access Control List 访问控制列表)<br>Begins an operation to return a ZNode's ACL settings. Call additional methods and finalize the operation by calling forPath() |
| setACL()        | 设置ACL<br>Begins an operation to set a ZNode's ACL settings. Call additional methods and finalize the operation by calling forPath() |
| getConfig()     | 返回最后提交的配置<br>Begins an operation to return the last committed configuration. Call additional methods and finalize the operation by calling forEnsemble() |
| reconfig()      | 更改配置<br>Begins an operation to change the configuration. Call additional methods and finalize the operation by calling forEnsemble() |

ACL 权限控制，使用：`scheme:id:perm` 来标识，主要涵盖 3 个方面：
　　权限模式（Scheme）：授权的策略
　　授权对象（ID）:授权的对象
　　权限（Permission）:授予的权限

其特性如下：
　　ZooKeeper的权限控制是基于每个znode节点的，需要对每个节点设置权限
　　每个znode支持设置多种权限控制方案和多个权限
　　子节点不会继承父节点的权限，客户端无权访问某节点，但可能可以访问它的子节点

### CuratorEvent 事件

| **Event Type** | **Event Methods**                                    |
| :------------- | :--------------------------------------------------- |
| CREATE         | getResultCode() and getPath()                        |
| DELETE         | getResultCode() and getPath()                        |
| EXISTS         | getResultCode(), getPath() and getStat()             |
| GET_DATA       | getResultCode(), getPath(), getStat() and getData()  |
| SET_DATA       | getResultCode(), getPath() and getStat()             |
| CHILDREN       | getResultCode(), getPath(), getStat(), getChildren() |
| SYNC           | getResultCode(), getStat()                           |
| GET_ACL        | getResultCode(), getACLList()                        |
| SET_ACL        | getResultCode()                                      |
| TRANSACTION    | getResultCode(), getOpResults()                      |
| WATCHED        | getWatchedEvent()                                    |
| GET_CONFIG     | getResultCode(), getData()                           |
| RECONFIG       | getResultCode(), getData()                           |

### 参考

- [Curator官网](http://curator.apache.org/)


