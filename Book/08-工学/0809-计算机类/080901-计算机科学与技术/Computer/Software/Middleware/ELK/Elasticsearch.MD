Elasticsearch (弹性搜索)
------------------------------------
Elasticsearch 是一个分布式、RESTful 风格的搜索和数据分析引擎，能够解决不断涌现出的各种用例。 作为 Elastic Stack 的核心，它集中存储您的数据，帮助您发现意料之中以及意料之外的情况。

### 数据录入：文档与索引
#### 文档
已序列化为JSON文档的复杂数据结构。

#### 索引
索引可以看作是文档的优化集合，每个文档都是字段的集合，这些字段是包含数据的键值对。
当一个文档被存储时，可以建立索引，在1秒内完成几乎实时的搜索。
##### 倒排索引：
Elasticsearch使用一种称为倒排索引的数据结构，它支持非常快速的全文搜索。
倒排索引列出出现在任何文档中的每个唯一单词，并标识每个单词出现在其中的所有文档。

#### 动态映射
启用动态映射后，Elasticsearch会自动检测并添加新字段到索引中。


### 信息输出：搜索与分析
Elasticsearch提供了一个简单、一致的restapi，用于管理集群、索引和搜索数据。
出于测试目的，您可以轻松地直接从命令行或通过Kibana中的开发人员控制台提交请求。
#### 数据搜索
Elasticsearch restapi支持结构化查询、全文查询和将两者结合起来的复杂查询。
##### 结构化查询
与SQL类似。
##### 全文查询
查找与查询字符串匹配的所有文档，并按相关性排序返回这些文档与搜索项的匹配程度。

#### 数据分析
Elasticsearch聚合使您能够构建复杂的数据摘要，并深入了解关键指标、模式和趋势。

#### 更多
机器学习

### 弹性拓展：集群/节点/分片

Elasticsearch旨在保证可用性，并根据需要进行调整（自然分配）。
您可以轻松将服务器（节点）添加到集群，Elasticsearch会自动在所有可用节点上分配数据和查询负载。
Elasticsearch不需要彻底检查应用程序，它知道如何平衡多节点群集，以提供可扩展性和高可用性。
节点越多越好。

Elasticsearch索引实际上只是一个或多个物理碎片的逻辑分组，其中每个碎片实际上是一个自包含的索引。通过将索引中的文档分布在多个分片上，并将这些分片分布在多个节点上，Elasticsearch可以确保冗余，这既可以防止硬件故障，又可以在节点添加到集群时增加查询容量。

随着集群的增长（或缩小），Elasticsearch会自动迁移分片以重新平衡集群。

#### 两种类型的分片
主分片和副分片。
顾名思义，副分片是主分片的备份。
预防了硬件故障造成的数据丢失，并提高了处理读请求（如搜索或检索文档）的能力。

#### 分片配置
索引中主碎片的数量在创建索引时是固定的，但是副本碎片的数量可以随时更改，而不会中断索引或查询操作。

创建索引时，在分片的大小和数量配置上有许多考虑因素。
数量：分片越多，维护索引的开销就越大；
大小：分片越大，移动分片的时间就越长；
总而言之：因地制宜，对症下药

一般而言，应该就分片的平均大小保持在数GB到数十GB之间。

为避免分片过多，一个节点可以容纳的分片数量与可用堆空间成正比。
建议每GB堆空间的分片数应小于20（最佳配置还需自行调试）。

#### 容灾
出于性能原因，集群中的节点一般位于同一网络中。
但是高可用框架要求避免将所有鸡蛋放在一个篮子里。
在一个位置发生重大故障时，另一个位置的服务器需要能够接管。

##### 跨集群复制（CCR）
CCR提供了一种将索引从主集群自动同步到可作为热备份的辅助远程集群的方法。如果主群集发生故障，则辅助群集可以接管。您还可以使用CCR创建辅助集群，以便在地理位置接近您的用户时提供读取请求。（类似主分片和副分片）

跨群集复制是主动-被动复制。主集群上的索引是活动的前导索引，它处理所有的写请求。复制到辅助集群的索引是只读跟随者。

#### 维护
与任何企业系统一样，您需要工具来保护、管理和监视Elasticsearch集群。
集成到Elasticsearch中的安全、监视和管理功能使您能够将Kibana用作管理集群的控制中心。
数据汇总和索引生命周期管理等功能可帮助您智能地管理数据。

### 常见问题
#### read_only_allow_delete
当read_only_allow_delete属性为true时，ES索引只允许读和删数据，不允许增和改数据。
##### 现象
```
[INFO][logstash.outputs.elasticsearch] retrying failed action with response code: 403 ({"type"=>"cluster_block_exception", 
"reason"=>"blocked by: [FORBIDDEN/12/index read-only / allow delete (api)];"})
```

##### 原因
- 内存空间不足
	JVMMemoryPressure 超过92%并持续30分钟时，ES触发保护机制，并且阻止写入操作，以防止集群达到红色状态，启用写保护后，写入操作将失败，并且抛出 ClusterBlockException ，无法创建新索引，并且抛出 IndexCreateBlockException ,当五分钟内恢复不到88%以下时，将禁用写保护。
- 磁盘空间不足
	es的默认磁盘水位警戒线是85%，一旦磁盘使用率超过85%，es不会再为该节点分配分片，es还有一个磁盘水位警戒线是90%，超过后，将尝试将分片重定位到其他节点。
	
##### 解决方案
把read_only_allow_delete设置为false。


### 分词
#### 分词器
- [IK分词器--github](https://github.com/medcl/elasticsearch-analysis-ik)

### 快速入门（小试牛刀）

1. 获得一个弹性搜索集群并运行

2. 索引一些示例文档

3. 使用Elasticsearch查询语言搜索文档

4. 使用bucket和metrics聚合分析结果

#### 本地运行Elasticsearch 
##### 下载解压安装

- Linux
```shell
> curl -L -O https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-7.9.3-linux-x86_64.tar.gz
> tar -xvf elasticsearch-7.9.3-linux-x86_64.tar.gz

```
- macOS
```shell
> curl -L -O https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-7.9.3-darwin-x86_64.tar.gz
> tar -xvf elasticsearch-7.9.3-darwin-x86_64.tar.gz

```
- Windows
https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-7.9.3-windows-x86_64.zip

##### 启动节点
需要为每个节点指定唯一的数据和日志路径。

- Linux / maxOS
```shell
> cd elasticsearch-7.9.3/bin
> ./elasticsearch -Epath.data=data1 -Epath.logs=log1
> ./elasticsearch -Epath.data=data2 -Epath.logs=log2
> ./elasticsearch -Epath.data=data3 -Epath.logs=log3
```
- Windows
```shell
> cd elasticsearch-7.9.3\bin
> .\elasticsearch.bat -E path.data=data1 -E path.logs=log1
> .\elasticsearch.bat -E path.data=data2 -E path.logs=log2
> .\elasticsearch.bat -E path.data=data3 -E path.logs=log3
```

##### 验证
使用cat health API验证您的三节点群集是否正在运行。
```shell
GET /_cat/health?v
```



##### 其他

在Docker中使用Elasticsearch

https://www.elastic.co/guide/en/elasticsearch/reference/7.9/docker.html



### Elasticsearch 配置

Elasticsearch提供了很好的默认设置，只需要很少的配置。可以使用群集更新设置API在正在运行的群集上更改大多数设置。

配置文件应该包含特定于节点的设置（例如`node.name`和`paths`），或节点为了能够加入集群而需要的设置，例如`cluster.name` , `network.host`

三个配置文件:
- `elasticsearch.yml` 主要配置
- `jvm.options` JVM参数配置
- `log4j2.properties` 日志配置
#### 动态配置

- 正在运行的节点：
	可以使用API进行配置和更新动态设置。
	使用API进行的更新可以是临时的或持久的。
	临时更新在重启时会被重置。
	还可以通过API为临时或持久设置分配一个`null`值来重置它们
- 尚未启动或已关闭的节点：
	可以修改`elasticsearch.yml`配置

如果使用多种方法配置同一设置，Elasticsearch将按以下优先顺序应用这些设置：
1. 临时（Transient）设置的值
2. 持久（Persistent）设置的值
3. `elasticsearch.yml`设置的值
4. 默认值

#### 静态配置

静态配置只能在未启动或关闭的节点上使用`elasticsearch.yml`设置。

必须在集群中的每个相关节点上配置静态设置。


### 参考
- [Elasticsearch官网--简体中文](https://www.elastic.co/cn/elasticsearch)
- [Elasticsearch参考](https://www.elastic.co/guide/en/elasticsearch/reference/master/index.html)
- [Elasticsearch 史上最全最常用工具清单 --三度](https://www.cnblogs.com/sanduzxcvbnm/p/12020541.html)
