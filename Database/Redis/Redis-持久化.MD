Redis-持久化
-------------------

## 持久化流程

## 持久化方式

### RDB  (Redis Database)
全量
以指定的时间间隔执行数据集的时间点快照。
#### 优缺点
##### 优点

- 适合备份
- 对性能的影响低
- 大数据集恢复速度快
##### 缺点
- 丢失数据多

### AOF（Append Only File）
增量
记录服务器接收到的每个写入操作，这些操作将在服务器启动时再次播放，重建原始数据集。
#### 优缺点
##### 优点
- 自动安全重写
- 丢失数据少
##### 缺点
- 大数据集持久化恢复慢
- AOF文件通常会比相同数据集的等效RDB文件大

#### AOF持久化刷写数据到磁盘的3种模式
- appendfsync always （总是强制同步）
  每次追加AOF命令时，就立即强制写入磁盘。
  丢数据最少，但是速度最慢，一般不推荐使用。
- appendfsync no （从不强制同步）
  将写入时机交给OS控制（通常为30秒左右1次，取决于内核的精确调整）。
  速度最快，但是丢数据最多，一般不推荐使用。
- appendfsync everysec （每秒强制同步）
  每隔一秒钟，将追加的AOF命令强制写入一次磁盘。
  速度与数据完整性的折中，推荐使用。
  如果发生故障，可能会丢失1秒的数据。
  
## 数据恢复
![Redis数据恢复](https://images.gitee.com/uploads/images/2020/0908/125142_69418b17_536895.png "Redis数据恢复.png")

### 当AOF和RDB同时存在
Redis重启时，如果AOF和RDB同时存在，将优先使用AOF重建数据集，因为AOF保存的数据更完整。
In the case both AOF and RDB persistence are enabled and Redis restarts the AOF file will be used to reconstruct the original dataset since it is guaranteed to be the most complete.

## 参考
- https://redis.io/topics/persistence
