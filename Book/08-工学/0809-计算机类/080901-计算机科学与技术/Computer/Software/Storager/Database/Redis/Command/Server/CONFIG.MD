CONFIG
------------
配置

### CONFIG GET
CONFIG GET 命令用于取得运行中的 Redis 服务器的配置参数(configuration parameters)

```
>config get aof*
 1)  "aof-rewrite-incremental-fsync"
 2)  "yes"
 3)  "aof-load-truncated"
 4)  "yes"
```

### CONFIG SET
CONFIG SET 命令可以动态地调整 Redis 服务器的配置(configuration)而无须重启。

你可以使用它修改配置参数，或者改变 Redis 的持久化(Persistence)方式。

CONFIG SET 可以修改的配置参数可以使用命令 `CONFIG GET *` 来列出，所有被 CONFIG SET 修改的配置参数都会立即生效。
```
> CONFIG GET slowlog-max-len
1) "slowlog-max-len"
2) "1024"

> CONFIG SET slowlog-max-len 10240
OK

> CONFIG GET slowlog-max-len
1) "slowlog-max-len"
2) "10240"
```

###  CONFIG RESETSTAT

重置 `INFO`命令中的某些统计数据，包括：
- Keyspace hits (键空间命中次数)
- Keyspace misses (键空间不命中次数)
- Number of commands processed (执行命令的次数)
- Number of connections received (连接服务器的次数)
- Number of expired keys (过期key的数量)
- Number of rejected connections (被拒绝的连接数量)
- Latest fork(2) time(最后执行 fork(2) 的时间)
- The `aof_delayed_fsync` counter(`aof_delayed_fsync` 计数器的值)

### CONFIG REWRITE
CONFIG REWRITE 命令对启动 Redis 服务器时所指定的 redis.conf 文件进行改写： 因为 CONFIG SET 命令可以对服务器的当前配置进行修改， 而修改后的配置可能和 redis.conf 文件中所描述的配置不一样， CONFIG REWRITE 的作用就是通过尽可能少的修改， 将服务器当前所使用的配置记录到 redis.conf 文件中。

重写会以非常保守的方式进行：

原有 redis.conf 文件的整体结构和注释会被尽可能地保留。
如果一个选项已经存在于原有 redis.conf 文件中 ， 那么对该选项的重写会在选项原本所在的位置（行号）上进行。
如果一个选项不存在于原有 redis.conf 文件中， 并且该选项被设置为默认值， 那么重写程序不会将这个选项添加到重写后的 redis.conf 文件中。
如果一个选项不存在于原有 redis.conf 文件中， 并且该选项被设置为非默认值， 那么这个选项将被添加到重写后的 redis.conf 文件的末尾。
未使用的行会被留白。 比如说， 如果你在原有 redis.conf 文件上设置了数个关于 save 选项的参数， 但现在你将这些 save 参数的一个或全部都关闭了， 那么这些不再使用的参数原本所在的行就会变成空白的。
即使启动服务器时所指定的 redis.conf 文件已经不再存在， CONFIG REWRITE 命令也可以重新构建并生成出一个新的 redis.conf 文件。

另一方面， 如果启动服务器时没有载入 redis.conf 文件， 那么执行 CONFIG REWRITE 命令将引发一个错误。

对 redis.conf 文件的重写是原子性的， 并且是一致的： 如果重写出错或重写期间服务器崩溃， 那么重写失败， 原有 redis.conf 文件不会被修改。 如果重写成功， 那么 redis.conf 文件为重写后的新文件。