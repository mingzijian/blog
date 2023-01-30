Redis-版本说明
-----------------------
### 命名规则
Redis借鉴了Linux操作系统对于版本号的命名规则：
<pre>
版本号第二位如果是奇数，则为非稳定版本（例如2.7、2.9、3.1），如果是偶数，则为稳定版本（例如2.6、2.8、3.0、3.2），
当前奇数版本就是下一个稳定版本的开发版本，例如2.9版本是3.0版本的开发版本
</pre>

#### 版本号：r.x.y
 - r：目前发布的内核主版本。
 - x：偶数表示稳定版本；奇数表示开发中版本。
 - y：错误修补的次数。

#### 各版本特性
##### 1.Redis2.6 （2012-10-23）
  <pre>Redis2.6在2012年正是发布，经历了17个版本，到2.6.17版本，相对于Redis2.4，主要特性如下：</pre>
  - 1）服务端支持Lua脚本。
  - 2）去掉虚拟内存相关功能。
  - 3）放开对客户端连接数的硬编码限制。
  - 4）键的过期时间支持毫秒。
  - 5）从节点支持只读功能。
  - 6）两个新的位图命令：bitcount和bitop。
  - 7）增强了redis-benchmark的功能：支持定制化的压测，CSV输出等功能。
  - 8）基于浮点数自增命令：incrbyfloat和hincrbyfloat。
  - 9）redis-cli可以使用--eval参数实现Lua脚本执行。
  - 10）shutdown命令增强。
  - 11）重构了大量的核心代码，所有集群相关的代码都去掉了，cluster功能将会是3.0版本最大的亮点。
  - 12）info可以按照section输出，并且添加了一些统计项
  - 13）sort命令优化
 
##### 2.Redis2.8 （2013-11-22）
  <pre>Redis2.8在2013年11月22日正式发布，经历了24个版本，到2.8.24版本，相比于Redis2.6，主要特性如下：</pre>
  - 1）添加部分主从复制的功能，在一定程度上降低了由于网络问题，造成频繁全量复制生成RDB对系统造成的压力。
  - 2）尝试性的支持IPv6.
  - 3）可以通过config set命令设置maxclients。
  - 4）可以用bind命令绑定多个IP地址。
  - 5）Redis设置了明显的进程名，方便使用ps命令查看系统进程。
  - 6）config rewrite命令可以将config set持久化到Redis配置文件中。
  - 7）发布订阅添加了pubsub。
  - 8）Redis Sentinel第二版，相比于Redis2.6的Redis Sentinel，此版本已经变成生产可用。

##### 3.Redis3.0 （2015-04-01）
  <pre>Redis3.0在2015年4月1日正式发布，相比于Redis2.8主要特性如下：</pre>
  - 1）Redis Cluster：Redis的官方分布式实现。
  - 2）全新的embedded string对象编码结果，优化小对象内存访问，在特定的工作负载下载速度大幅提升。
  - 3）Iru算法大幅提升。
  - 4）migrate连接缓存，大幅提升键迁移的速度。
  - 5）migrate命令两个新的参数copy和replace。
  - 6）新的client pause命令，在指定时间内停止处理客户端请求。
  - 7）bitcount命令性能提升。
  - 8）cinfig set设置maxmemory时候可以设置不同的单位（之前只能是字节）。
  - 9）Redis日志小做调整：日志中会反应当前实例的角色（master或者slave）。
  - 10）incr命令性能提升。

##### 4.Redis3.2 （2016-05-06）
  <pre>增加的最大功能就是对GEO（地理位置）的支持。相比于Redis3.0主要特征如下：</pre>
  - 1）添加GEO相关功能。
  - 2）SDS在速度和节省空间上都做了优化。
  - 3）支持用upstart或者systemd管理Redis进程。
  - 4）新的List编码类型：quicklist。
  - 5）从节点读取过期数据保证一致性。
  - 6）添加了hstrlen命令。
  - 7）增强了debug命令，支持了更多的参数。
  - 8）Lua脚本功能增强。
  - 9）添加了Lua Debugger。
  - 10）config set 支持更多的配置参数。
  - 11）优化了Redis崩溃后的相关报告。
  - 12）新的RDB格式，但是仍然兼容旧的RDB。
  - 13）加速RDB的加载速度。
  - 14）spop命令支持个数参数。
  - 15）cluster nodes命令得到加速。
  - 16）Jemalloc更新到4.0.3版本。
 
##### 5.Redis4.0 （2017-07-15）
  <pre>更好的复制（PSYNC2），线程DEL / FLUSH，混合RDB + AOF格式，活动内存碎片整理，内存使用和性能改进。下面是Redis4.0的新特性：</pre>
  - 1）提供了模块系统，方便第三方开发者拓展Redis的功能。
  - 2）PSYNC2.0：优化了之前版本中，主从节点切换必然引起全量复制的问题。
  - 3）提供了新的缓存剔除算法：LFU（Last Frequently Used），并对已有算法进行了优化。
  - 4）提供了非阻塞del和flushall/flushdb功能，有效解决删除了bigkey可能造成的Redis阻塞。
  - 5）提供了memory命令，实现对内存更为全面的监控统计。
  - 6）提供了交互数据库功能，实现Redis内部数据库的数据置换。
  - 7）提供了RDB-AOF混合持久化格式，充分利用了AOF和RDB各自优势。
  - 8）Redis Cluster 兼容NAT和Docker。

##### 6.Redis5.0 （2018-10-18）
  <pre>引入流数据类型(Stream data type) 。下面是Redis5.0的新特性：</pre>
  - 1） 新的流数据类型(Stream data type) https://redis.io/topics/streams-intro
  - 2） 新的 Redis 模块 API：定时器、集群和字典 API(Timers, Cluster and Dictionary APIs)
  - 3） RDB 现在可存储 LFU 和 LRU 信息
  - 4） redis-cli 中的集群管理器从 Ruby (redis-trib.rb) 移植到了 C 语言代码。执行 `redis-cli --cluster help` 命令以了解更多信息
  - 5） 新的有序集合(sorted set)命令：ZPOPMIN/MAX 和阻塞变体(blocking variants)
  - 6） 升级 Active defragmentation 至 v2 版本
  - 7） 增强 HyperLogLog 的实现
  - 8） 更好的内存统计报告
  - 9） 许多包含子命令的命令现在都有一个 HELP 子命令
  - 10）客户端频繁连接和断开连接时，性能表现更好
  - 11）许多错误修复和其他方面的改进
  - 12）升级 Jemalloc 至 5.1 版本
  - 13）引入 CLIENT UNBLOCK 和 CLIENT ID
  - 14）新增 LOLWUT 命令 http://antirez.com/news/123
  - 15）在不存在需要保持向后兼容性的地方，弃用 "slave" 术语
  - 16）网络层中的差异优化
  - 17）Lua 相关的改进：
     - 将 Lua 脚本更好地传播到 replicas / AOF
     - Lua 脚本现在可以超时并在副本中进入 -BUSY 状态
  
  - 18）引入动态的 HZ(Dynamic HZ) 以平衡空闲 CPU 使用率和响应性
  - 19）对 Redis 核心代码进行了重构并在许多方面进行了改进

##### 6.Redis6.0 （2020）
  


