MySQL MVCC
---------------------
Multi-Version Concurrency Control 
多版本并发控制

MVCC不只使用在MySQL中，Oracle、PostgreSQL，以及其他一些数据库系统也同样使用它。


### 相关概念
#### 当前读
读取的记录是当前最新版本
对读取的记录进行加锁（保证其他并发事务不能修改当前记录）

注意：
串行化事务隔离级别下的所有读都是当前读

举例：
select lock in share mode(共享锁), 
select for update ; update, insert ,delete(排他锁)

#### 快照读
不加锁的常规select （前提：事务隔离级别低于串行化）
