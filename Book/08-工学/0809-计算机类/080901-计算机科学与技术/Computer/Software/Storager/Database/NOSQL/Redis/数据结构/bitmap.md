# Redis 位图

Redis 位图简介

位图不是实际的数据类型，而是在 String 类型上定义的一组面向位的操作，将其视为**位向量**。由于字符串是二进制安全 blob，其最大长度为 512 MB，因此它们适合设置**最多 2^32  （约 42.9 亿）个不同位**。

您可以对一个或多个字符串执行按位运算。位图用例的一些示例包括：

- 对于集合成员对应于整数 0-N 的情况，有效的集合表示。
- 对象权限，其中每一位代表一个特定的权限，类似于文件系统存储权限的方式。

## 基本命令

- [`SETBIT`](https://redis.io/commands/setbit)将提供的偏移量处的一位设置为 0 或 1。
- [`GETBIT`](https://redis.io/commands/getbit)返回给定偏移处的位值。

请参阅[位图命令的完整列表](https://redis.io/commands/?group=bitmap)。

## 例子

假设有 1000 名骑自行车者在乡村比赛，他们自行车上的传感器标记为 0-999。您希望快速确定给定传感器是否在一小时内对跟踪服务器进行了 ping 操作以检查骑手情况。

您可以使用位图来表示这种情况，该位图的键引用当前小时。

- Rider 123 在 2024 年 1 月 1 日 00:00 内对服务器进行 ping 操作。然后您可以确认骑手 123 已对服务器执行 ping 操作。您还可以检查骑手 456 是否在同一小时对服务器执行 ping 操作。

\>_ Redis CLI

```plaintext
> SETBIT pings:2024-01-01-00:00 123 1
(integer) 0
> GETBIT pings:2024-01-01-00:00 123
1
> GETBIT pings:2024-01-01-00:00 456
0
```

## 位运算

位操作分为两组：恒定时间单个位操作，例如将位设置为 1 或 0，或获取其值；以及位组操作，例如计算给定位范围内设置位的数量（例如，人口计数）。

位图的最大优点之一是它们在存储信息时通常可以极大地节省空间。例如，在一个用增量用户 ID 表示不同用户的系统中，仅使用 512 MB 内存就可以记住 40 亿个用户的单个比特信息（例如，知道用户是否想要接收新闻通讯）。

该[`SETBIT`](https://redis.io/commands/setbit)命令将位编号作为其第一个参数，将要设置该位的值作为其第二个参数，即 1 或 0。如果寻址位超出当前字符串长度，则该命令会自动放大字符串。

[`GETBIT`](https://redis.io/commands/getbit)仅返回指定索引处的位的值。超出范围的位（寻址超出目标密钥中存储的字符串长度的位）始终被视为零。

有三个对位组进行操作的命令：

1. [`BITOP`](https://redis.io/commands/bitop)在不同字符串之间执行按位运算。提供的运算有 AND、OR、XOR 和 NOT。
2. [`BITCOUNT`](https://redis.io/commands/bitcount)执行总体计数，报告设置为 1 的位数。
3. [`BITPOS`](https://redis.io/commands/bitpos)查找具有指定值 0 或 1 的第一位。

和[`BITPOS`](https://redis.io/commands/bitpos)都[`BITCOUNT`](https://redis.io/commands/bitcount)能够对字符串的字节范围进行操作，而不是对字符串的整个长度进行操作。我们可以简单地看到位图中设置的位数。

\>_ Redis CLI

```plaintext
> BITCOUNT pings:2024-01-01-00:00
(integer) 1
```



爪哇

Python

例如，假设您想了解网站用户每日访问的最长连续时间。您从零开始计算天数，即您公开网站的日期，并在[`SETBIT`](https://redis.io/commands/setbit)用户每次访问该网站时设置一点。作为位索引，您只需获取当前的 unix 时间，减去初始偏移量，然后除以一天中的秒数（通常为 3600*24）。

这样，对于每个用户，您都会有一个包含每天访问信息的小字符串。可以[`BITCOUNT`](https://redis.io/commands/bitcount)轻松获取给定用户访问网站的天数，同时只需几次[`BITPOS`](https://redis.io/commands/bitpos)调用，或者简单地获取并分析客户端的位图，就可以轻松计算最长的连续访问时间。

将位图拆分为多个键很简单，例如为了对数据集进行分片，并且通常最好避免使用巨大的键。要将位图拆分到不同的键上，而不是将所有位设置为一个键，一个简单的策略就是为每个键存储 M 位，并使用 获取键名称和`bit-number/M`在键内寻址的第 N 位`bit-number MOD M`。

## 表现

[`SETBIT`](https://redis.io/commands/setbit)且[`GETBIT`](https://redis.io/commands/getbit)均为 O(1)。 [`BITOP`](https://redis.io/commands/bitop)是 O(n)，其中*n*是比较中最长字符串的长度。

## 了解更多

- [Redis 位图解释](https://www.youtube.com/watch?v=oj8LdJQjhJo)教您如何在在线游戏中使用位图进行地图探索。
- [Redis 大学的 RU101](https://university.redis.com/courses/ru101/)详细介绍了 Redis 位图。