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


