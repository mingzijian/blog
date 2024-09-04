SEATA
----------
Simple Extensible Autonomous Transaction Architecture
Seata 是一款开源的分布式事务解决方案，致力于提供高性能和简单易用的分布式事务服务。
Seata 将为用户提供了 AT、TCC、SAGA 和 XA 事务模式，为用户打造一站式的分布式解决方案。

### 术语
#### TC (Transaction Coordinator) - 事务协调者
维护全局和分支事务的状态，驱动全局事务提交或回滚。

#### TM (Transaction Manager) - 事务管理器
定义全局事务的范围：开始全局事务、提交或回滚全局事务。

#### RM (Resource Manager) - 资源管理器
管理分支事务处理的资源，与TC交谈以注册分支事务和报告分支事务的状态，并驱动分支事务提交或回滚。

### 事务模式支持
#### AT （Automatic Transaction）

无侵入的分布式事务解决方案。

##### 前提

- 基于支持本地 ACID 事务的关系型数据库。
- Java 应用，通过 JDBC 访问数据库。



AT 模式基于 **支持本地 ACID 事务** 的 **关系型数据库**：

- 一阶段 prepare 行为：在本地事务中，一并提交业务数据更新和相应回滚日志记录。

- 二阶段 commit 行为：马上成功结束，**自动** 异步批量清理回滚日志。

- 二阶段 rollback 行为：通过回滚日志，**自动** 生成补偿操作，完成数据回滚。

  

AT模式分两个阶段来管理全局事务：
- 第一阶段： 执行各分支事务
- 第二阶段： 控制全局事务最终提交或回滚

#### TCC （Try Commit Cancel）
基于两阶段提交（2PC），自定义补偿型，支持把 **自定义** 的分支事务纳入到全局事务的管理中。

TCC 模式，不依赖于底层数据资源的事务支持：

- 一阶段 prepare 行为：调用 **自定义** 的 prepare 逻辑。
- 二阶段 commit 行为：调用 **自定义** 的 commit 逻辑。
- 二阶段 rollback 行为：调用 **自定义** 的 rollback 逻辑。

![Overview of a global transaction](https://gitee.com/mingzijian/resources/raw/master/picgo/TB14Kguw1H2gK0jSZJnXXaT1FXa-853-482.png)

#### SAGA

长事务解决方案。

理论基础：Hector & Kenneth 发表论⽂ Sagas （1987）

##### 适用场景

- 业务流程长、业务流程多
- 参与者包含其它公司或遗留系统服务，无法提供 TCC 模式要求的三个接口

业务流程中每个参与者都提交本地事务，当出现某一个参与者失败则补偿前面已经成功的参与者，一阶段正向服务和二阶段补偿服务都由业务开发实现。

 ![Saga模式示意图](https://gitee.com/mingzijian/resources/raw/master/picgo/TB1Y2kuw7T2gK0jSZFkXXcIQFXa-445-444.png)

#### XA

分布式事务处理规范

"eXtended Architecture"，简称XA，是一种分布式事务处理的协议。

流程：
1. TM 开启全局事务
2. RM 向 TC 注册分支事务
3. RM 向 TC 报告分支事务状态
4. TC 向 RM 发送 commit/rollback 请求
5. TM 结束全局事务

##### 两阶段提交

 ![两阶段提交.png](https://gitee.com/mingzijian/resources/raw/master/picgo/454228ca218640b690665d6c664b741a.png)

##### 三阶段提交

 ![三阶段提交.png](https://gitee.com/mingzijian/resources/raw/master/picgo/1a07db6e5d6048c8b35bd56c9c2a75c0.png)


### 参数配置

#### 公共参数

| key                     | desc                           | remark                                                       |
| ----------------------- | ------------------------------ | ------------------------------------------------------------ |
| transport.serialization | client和server通信编解码方式   | seata(ByteBuf)、protobuf、kryo、hession、fst，<br>默认seata  |
| transport.compressor    | client和server通信数据压缩方式 | none、gzip，<br>默认none                                     |
| transport.heartbeat     | client和server通信心跳检测开关 | 默认true开启                                                 |
| **registry.type**       | 注册中心类型                   | 默认file，<br>支持file 、nacos 、eureka、redis、zk、consul、etcd3、sofa、custom |
| **config.type**         | 配置中心类型                   | 默认file，<br>支持file、nacos 、apollo、zk、consul、etcd3、custom |

#### Server端参数

| key                                       | desc                                             | remark                                                       |
| ----------------------------------------- | ------------------------------------------------ | ------------------------------------------------------------ |
| server.undo.logSaveDays                   | undo保留天数                                     | 默认7天,log_status=1（<a href="#appendix-3">附录3</a>）和未正常清理的undo |
| server.undo.logDeletePeriod               | undo清理线程间隔时间                             | 默认86400000，单位毫秒                                       |
| server.maxCommitRetryTimeout              | 二阶段提交重试超时时长                           | 单位ms,s,m,h,d,对应毫秒,秒,分,小时,天,默认毫秒。<br/>默认值-1表示无限重试。<br/>公式: timeout>=now-globalTransactionBeginTime,true表示超时则不再重试 |
| server.maxRollbackRetryTimeout            | 二阶段回滚重试超时时长                           | 同commit                                                     |
| server.recovery.committingRetryPeriod     | 二阶段提交未完成状态全局事务重试提交线程间隔时间 | 默认1000，单位毫秒                                           |
| server.recovery.asynCommittingRetryPeriod | 二阶段异步提交状态重试提交线程间隔时间           | 默认1000，单位毫秒                                           |
| server.recovery.rollbackingRetryPeriod    | 二阶段回滚状态重试回滚线程间隔时间               | 默认1000，单位毫秒                                           |
| server.recovery.timeoutRetryPeriod        | 超时状态检测重试线程间隔时间                     | 默认1000，单位毫秒，检测出超时将全局事务置入回滚会话管理器   |
| **store.mode**                            | 事务会话信息存储方式                             | file本地文件(不支持HA)，db数据库\|redis(支持HA)              |
| store.file.dir                            | file模式文件存储文件夹名                         | 默认sessionStore                                             |
| **store.db.datasource**                   | db模式数据源类型                                 | dbcp、druid、hikari；无默认值，store.mode=db时必须指定。     |
| **store.db.dbType**                       | db模式数据库类型                                 | mysql、oracle、db2、sqlserver、sybaee、h2、sqlite、access、postgresql、oceanbase；无默认值，store.mode=db时必须指定。 |
| **store.db.driverClassName**              | db模式数据库驱动                                 | store.mode=db时必须指定                                      |
| **store.db.url**                          | db模式数据库url                                  | store.mode=db时必须指定，<br/>在使用mysql作为数据源时，建议在连接参数中加上`rewriteBatchedStatements=true`(详细原因请阅读<a href="#appendix-7">附录7</a>) |
| **store.db.user**                         | db模式数据库账户                                 | store.mode=db时必须指定                                      |
| **store.db.password**                     | db模式数据库账户密码                             | store.mode=db时必须指定                                      |
| store.db.minConn                          | db模式数据库初始连接数                           | 默认1                                                        |
| store.db.maxConn                          | db模式数据库最大连接数                           | 默认20                                                       |
| store.db.maxWait                          | db模式获取连接时最大等待时间                     | 默认5000，单位毫秒                                           |
| store.db.globalTable                      | db模式全局事务表名                               | 默认global_table                                             |
| store.db.branchTable                      | db模式分支事务表名                               | 默认branch_table                                             |
| store.db.lockTable                        | db模式全局锁表名                                 | 默认lock_table                                               |
| store.db.queryLimit                       | db模式查询全局事务一次的最大条数                 | 默认100                                                      |
| **store.redis.host**                      | redis模式ip                                      | 默认127.0.0.1                                                |
| **store.redis.port**                      | redis模式端口                                    | 默认6379                                                     |
| store.redis.maxConn                       | redis模式最大连接数                              | 默认10                                                       |
| store.redis.minConn                       | redis模式最小连接数                              | 默认1                                                        |
| **store.redis.database**                  | redis模式默认库                                  | 默认0                                                        |
| **store.redis.password**                  | redis模式密码(无可不填)                          | 默认null                                                     |
| store.redis.queryLimit                    | redis模式一次查询最大条数                        | 默认100                                                      |
| metrics.enabled                           | 是否启用Metrics                                  | 默认false关闭，<br/>在False状态下，所有与Metrics相关的组件将不会被初始化，使得性能损耗最低 |
| metrics.registryType                      | 指标注册器类型                                   | Metrics使用的指标注册器类型，<br/>默认为内置的compact（简易）实现，这个实现中的Meter仅使用有限内存计数，性能高足够满足大多数场景；<br/>目前只能设置一个指标注册器实现 |
| metrics.exporterList                      | 指标结果Measurement数据输出器列表                | 默认prometheus，<br/>多个输出器使用英文逗号分割，例如"prometheus,jmx"，<br/>目前仅实现了对接prometheus的输出器 |
| metrics.exporterPrometheusPort            | prometheus输出器Client端口号                     | 默认9898                                                     |

#### Client端参数

| key                                                | desc                                          | remark                                                       |
| -------------------------------------------------- | --------------------------------------------- | ------------------------------------------------------------ |
| seata.enabled                                      | 是否开启spring-boot自动装配                   | true、false,(SSBS)专有配置，默认true（<a href="#appendix-4">附录4</a>） |
| seata.enableAutoDataSourceProxy=true               | 是否开启数据源自动代理                        | true、false,seata-spring-boot-starter(SSBS)专有配置,<br/>SSBS默认会开启数据源自动代理,可通过该配置项关闭. |
| seata.useJdkProxy=false                            | 是否使用JDK代理作为数据源自动代理的实现方式   | true、false,(SSBS)专有配置,默认false,<br/>采用CGLIB作为数据源自动代理的实现方式 |
| transport.enableClientBatchSendRequest             | 客户端事务消息请求是否批量合并发送            | 默认true，false单条发送                                      |
| client.log.exceptionRate                           | 日志异常输出概率                              | 默认100，<br/>目前用于undo回滚失败时异常堆栈输出，百分之一的概率输出，回滚失败基本是脏数据，无需输出堆栈占用硬盘空间 |
| **service.vgroupMapping.my_test_tx_group**         | 事务群组（<a href="#appendix-1">附录1</a>）   | my_test_tx_group为分组，配置项值为TC集群名                   |
| **service.default.grouplist**                      | TC服务列表（<a href="#appendix-2">附录2</a>） | 仅注册中心为file时使用                                       |
| **service.disableGlobalTransaction**               | 全局事务开关                                  | 默认false。false为开启，true为关闭                           |
| client.tm.degradeCheck                             | 降级开关                                      | 默认false。<br/>业务侧根据连续错误数自动降级不走seata事务(详细介绍请阅读<a href="#appendix-6">附录6</a>) |
| client.tm.degradeCheckAllowTimes                   | 升降级达标阈值                                | 默认10                                                       |
| client.tm.degradeCheckPeriod                       | 服务自检周期                                  | 默认2000,单位ms.每2秒进行一次服务自检,来决定                 |
| client.rm.reportSuccessEnable                      | 是否上报一阶段成功                            | true、false，从1.1.0版本开始,默认false.<br/>true用于保持分支事务生命周期记录完整，false可提高不少性能 |
| client.rm.asynCommitBufferLimit                    | 异步提交缓存队列长度                          | 默认10000。 二阶段提交成功，RM异步清理undo队列               |
| client.rm.lock.retryInterval                       | 校验或占用全局锁重试间隔                      | 默认10，单位毫秒                                             |
| client.rm.lock.retryTimes                          | 校验或占用全局锁重试次数                      | 默认30                                                       |
| client.rm.lock.retryPolicyBranchRollbackOnConflict | 分支事务与其它全局回滚事务冲突时锁策略        | 默认true，优先释放本地锁让回滚成功                           |
| client.rm.reportRetryCount                         | 一阶段结果上报TC重试次数                      | 默认5次                                                      |
| client.rm.tableMetaCheckEnable                     | 自动刷新缓存中的表结构                        | 默认false                                                    |
| client.tm.commitRetryCount                         | 一阶段全局提交结果上报TC重试次数              | 默认1次，建议大于1                                           |
| client.tm.rollbackRetryCount                       | 一阶段全局回滚结果上报TC重试次数              | 默认1次，建议大于1                                           |
| client.undo.dataValidation                         | 二阶段回滚镜像校验                            | 默认true开启，false关闭                                      |
| client.undo.logSerialization                       | undo序列化方式                                | 默认jackson                                                  |
| client.undo.logTable                               | 自定义undo表名                                | 默认undo_log                                                 |
### API
#### High-Level API
用于事务边界定义、控制及事务状态查询。
##### GlobalTransaction
全局事务：包括开启事务、提交、回滚、获取当前状态等方法。

##### GlobalTransactionContext
GlobalTransaction 实例的获取需要通过 GlobalTransactionContext

##### TransactionalTemplate
事务化模板：通过上述 GlobalTransaction 和 GlobalTransactionContext API 把一个业务服务的调用包装成带有分布式事务支持的服务。

#### Low-Level API
用于控制事务上下文的传播。

##### RootContext
```java
// 获取
String xid = RootContext.getXID();
// 绑定
RootContext.bind(rpcXid);
// 解绑
String unbindXid = RootContext.unbind();
// 重绑
RootContext.bind(unbindXid);
```

### 附录
#### <a name="appendix-1">附录1：事务分组说明</a>

事务分组说明。
1. 事务分组是什么？
事务分组是seata的资源逻辑，类似于服务实例。在file.conf中的my_test_tx_group就是一个事务分组。
2. 通过事务分组如何找到后端集群？
首先程序中配置了事务分组（GlobalTransactionScanner 构造方法的txServiceGroup参数），程序会通过用户配置的配置中心去寻找service.vgroupMapping
.事务分组配置项，取得配置项的值就是TC集群的名称。拿到集群名称程序通过一定的前后缀+集群名称去构造服务名，各配置中心的服务名实现不同。拿到服务名去相应的注册中心去拉取相应服务名的服务列表，获得后端真实的TC服务列表。
3. 为什么这么设计，不直接取服务名？
这里多了一层获取事务分组到映射集群的配置。这样设计后，事务分组可以作为资源的逻辑隔离单位，当发生故障时可以快速failover。

#### <a name="appendix-2">附录2：关于grouplist问题说明</a>

关于grouplist问题说明下。
1. 什么时候会用到file.conf中的default.grouplist？
当registry.type=file时会用到，其他时候不读。
2. default.grouplist的值列表是否可以配置多个？
可以配置多个，配置多个意味着集群，但当store.mode=file时，会报错。原因是在file存储模式下未提供本地文件的同步，所以需要使用store.mode=db，通过db来共享TC集群间数据
3. 是否推荐使用default.grouplist？
不推荐，如问题1，当registry.type=file时会用到，也就是说这里用的不是真正的注册中心，不具体服务的健康检查机制当tc不可用时无法自动剔除列表，推荐使用nacos 、eureka、redis、zk、consul、etcd3、sofa。registry.type=file或config.type=file 设计的初衷是让用户再不依赖第三方注册中心或配置中心的前提下，通过直连的方式，快速验证seata服务。    
4. seata-spring-boot-starter中的配置为什么是grouplist.default,也就是说和file.conf中的default.grouplist写法刚好颠倒了位置?  
由于spring-boot本身配置文件语法的要求,这个地方需要将file.conf中的default.grouplist写成grouplist.default,效果是一样的.

#### <a name="appendix-3">附录3：log_status</a>

log_status=1的是防御性的，是收到全局回滚请求，但是不确定某个事务分支的本地事务是否已经执行完成了，这时事先插入一条branchid相同的数据，插入的假数据成功了，本地事务继续执行就会报唯一索引冲突自动回滚。
假如插入不成功说明表里有数据这个本地事务已经执行完成了，那么取出这条undolog数据做反向回滚操作。

#### <a name="appendix-4">附录4：自动装配</a>

是否开启spring-boot自动装配，如果开启，则会自动配置seata与spring-boot的集成，包括数据源的自动代理以及GlobalTransactionScanner初始化。
注：1.0版本新特性，需依赖seata-spring-boot-starter。

#### <a name="appendix-5">附录5</a>

seata1.1.0版本新加入以下注解,用于开启数据源自动代理功能
`@EnableAutoDataSourceProxy`

| attribute   | desc                                        | remark                                                      |
| ----------- | ------------------------------------------- | ----------------------------------------------------------- |
| useJdkProxy | 是否使用JDK代理作为数据源自动代理的实现方式 | false、true,默认false,采用CGLIB作为数据源自动代理的实现方式 |

1. 对于使用seata-spring-boot-starter的方式，默认已开启数据源自动代理,如需关闭，请配置seata.enableAutoDataSourceProxy=false，该项配置默认为true。
    如需切换代理实现方式，请通过seata.useJdkProxy=false进行配置,默认为false，采用CGLIB作为数据源自动代理的实现方式。
2. 对于使用seata-all的方式，请使用@EnableAutoDataSourceProxy来显式开启数据源自动代理功能。如有需要，可通过该注解的useJdkProxy属性进行代理实现方式的切换。默认为false,采用CGLIB作为数据源自动代理的实现方式。

#### <a name="appendix-6">附录6：服务自动降级策略</a>

关于服务自动降级策略的具体实现介绍:
首先通过读取client.tm.degradeCheck是否为true,决定是否开启自检线程.
随后读取degradeCheckAllowTimes和degradeCheckPeriod,确认阈值与自检周期.
假设degradeCheckAllowTimes=10,degradeCheckPeriod=2000
那么每2秒钟会进行一个begin,commit的测试,如果失败,则记录连续失败数,如果成功则清空连续失败数.
连续错误由用户接口及自检线程进行累计,直到连续失败次数达到用户的阈值,则关闭Seata分布式事务,避免用户自身业务长时间不可用.
反之,假如当前分布式事务关闭,那么自检线程继续按照2秒一次的自检,直到连续成功数达到用户设置的阈值,那么Seata分布式事务将恢复使用

#### <a name="appendix-7">附录7</a>

在store.mode=db，由于seata是通过jdbc的executeBatch来批量插入全局锁的，根据MySQL官网的说明，连接参数中的rewriteBatchedStatements为true时，在执行executeBatch，并且操作类型为insert时，jdbc驱动会把对应的SQL优化成`insert into () values (), ()`的形式来提升批量插入的性能。
根据实际的测试，该参数设置为true后，对应的批量插入性能为原来的10倍多，因此在数据源为MySQL时，建议把该参数设置为true。

### 参考
 - [官网](https://seata.io/)
