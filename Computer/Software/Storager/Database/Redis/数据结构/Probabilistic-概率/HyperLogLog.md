# 超级日志日志

HyperLogLog 是一种概率数据结构，用于估计集合的基数。

HyperLogLog 是一种概率数据结构，用于估计集合的基数。作为一种概率数据结构，HyperLogLog 以完美的准确性换取高效的空间利用。

Redis HyperLogLog 实现最多使用 12 KB，并提供 0.81% 的标准错误。

计算唯一项目通常需要与要计算的项目数量成比例的内存量，因为您需要记住过去已经见过的元素，以避免多次计算它们。然而，存在一组以内存换取精度的算法：它们返回带有标准误差的估计度量，在 HyperLogLog 的 Redis 实现的情况下，该标准误差小于 1%。该算法的神奇之处在于，您不再需要使用与计数的项目数成正比的内存量，而是可以使用恒定量的内存；最坏情况下为 12k 字节，或者如果您的 HyperLogLog（从现在起我们将其称为 HLL）看到的元素很少，则要少得多。

Redis 中的 HLL 虽然在技术上是不同的数据结构，但被编码为 Redis 字符串，因此您可以调用[`GET`](https://redis.io/commands/get)来序列化 HLL，并将[`SET`](https://redis.io/commands/set) 其反序列化回服务器。

从概念上讲，HLL API 就像使用 Set 来完成相同的任务。您将 [`SADD`](https://redis.io/commands/sadd)每个观察到的元素放入一个集合中，并用于[`SCARD`](https://redis.io/commands/scard)检查集合内元素的数量，这些元素是唯一的，因为[`SADD`](https://redis.io/commands/sadd)不会重新添加现有元素。

虽然您并未真正*将项目添加*到 HLL 中，因为数据结构仅包含不包含实际元素的状态，但 API 是相同的：

- 每次看到新元素时，都可以使用 将该元素添加到计数中[`PFADD`](https://redis.io/commands/pfadd)。
- 当您想要检索使用该[`PFADD`](https://redis.io/commands/pfadd)命令添加的唯一元素的当前近似值时，可以使用该[`PFCOUNT`](https://redis.io/commands/pfcount)命令。如果需要合并两个不同的 HLL，[`PFMERGE`](https://redis.io/commands/pfmerge)可以使用该命令。由于 HLL 提供了唯一元素的近似计数，因此合并结果将为您提供两个源 HLL 中唯一元素数量的近似值。

\>_ Redis CLI

```sh
> PFADD bikes Hyperion Deimos Phoebe Quaoar
(integer) 1
> PFCOUNT bikes
(integer) 4
> PFADD commuter_bikes Salacia Mimas Quaoar
(integer) 1
> PFMERGE all_bikes bikes commuter_bikes
OK
> PFCOUNT all_bikes
(integer) 6
```

此数据结构的一些用例示例是计算用户每天在搜索表单中执行的唯一查询、网页的唯一访问者数量以及其他类似情况。

Redis 还能够执行 HLL 的并集，请查看 [完整文档](https://redis.io/commands#hyperloglog)以获取更多信息。

## 示例

**网页的匿名唯一访问（SaaS、分析工具）**

该应用程序回答了以下问题：

- 这一天该页面的独立访问次数有多少？
- 有多少独立用户播放过这首歌？
- 有多少独立用户观看过该视频？

笔记

在某些国家/地区，存储 IP 地址或任何其他类型的个人标识符是违法的，这使得您无法在您的网站上获取唯一的访问者统计信息。

每个时间段的每页（视频/歌曲）都会创建一个 HyperLogLog，并且每次访问时都会将每个 IP/标识符添加到其中。

## 基本命令

- [`PFADD`](https://redis.io/commands/pfadd)将一个项目添加到 HyperLogLog。
- [`PFCOUNT`](https://redis.io/commands/pfcount)返回集合中项目数量的估计值。
- [`PFMERGE`](https://redis.io/commands/pfmerge)将两个或多个 HyperLogLog 合并为一个。

请参阅[HyperLogLog 命令的完整列表](https://redis.io/commands/?group=hyperloglog)。

## 性能

向HyperLogLog写入 ( [`PFADD`](https://redis.io/commands/pfadd)) 和读取 ( ) 是在恒定的时间和空间内完成的。[`PFCOUNT`](https://redis.io/commands/pfcount)合并 HLL 的时间复杂度为 O(n)，其中*n*是草图的数量。

## 限制

HyperLogLog 可以估计最多包含 18,446,744,073,709,551,616 (2^64) 个成员的集合的基数。

## 了解更多

- [Redis 新数据结构：HyperLogLog](http://antirez.com/news/75)有很多关于 Redis 数据结构及其实现的详细信息。
- [Redis HyperLogLog 解释](https://www.youtube.com/watch?v=MunL8nnwscQ)展示了如何使用 Redis HyperLogLog 数据结构构建流量热图。