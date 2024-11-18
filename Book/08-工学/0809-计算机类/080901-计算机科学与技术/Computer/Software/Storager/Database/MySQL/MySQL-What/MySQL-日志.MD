MySQL 日志
------------------

### 日志分类
 - 错误日志(error log)
 - 常规日志(general log)
 - 二进制日志(bin log)
 - 慢查询日志(slow log)
 - 中继日志(relay log)
 - Innodb日志(innodb redo log)

#### 错误日志(error log)
	错误日志是一个文本文件。它记录了MySQL Server每次启动和关闭的详细信息以及运行过程中所有较为严重的警告和错误信息。
#### 常规日志(general log)
	记录所有的SQL语句执行，影响性能。该日志一般是关闭的，可以在辅助排查和分析问题时短暂开启。
#### 二进制日志(bin log)
	记录了所有的DDL和DML语句（除了数据查询语句select）,以事件形式记录，还包含语句所执行的消耗的时间，MySQL的二进制日志是事务安全型的。
##### 格式
binlog的三种格式：STATEMENT、ROW、MIXED 。
 - STATEMENT
	基于SQL语句的复制。记录每一条会修改数据的SQL。
	优点：不需要记录每一行的变化，`binlog`日志量小，`IO`性能高。
	缺点：在某些情况下会导致主从数据不一致，比如执行`sysdate()`、`slepp()`等。
 - ROW
	基于行的复制。
	优点：不会出现某些特定情况下的存储过程、或function、或trigger的调用和触发无法被正确复制的问题。
	缺点：记录每一行的变化，`binlog`日志量大，`IO`性能低。
 - MIXED
	基于`STATMENT`和`ROW`两种模式的混合复制。
	一般操作使用`STATMENT`模式，`STATMENT`模式无法复制的操作使用`ROW`模式。
	
 ```sql
 # 查看binlog相关设置
 show variables like "%log_bin%";
 
 show variables like "%binlog_format%";
 ```
在`MySQL5.7.7`之前，默认的格式是`STATEMENT`，`MySQL5.7.7`之后，默认值是`ROW`。
日志格式通过`binlog-format`指定。


##### 使用场景
 - MySQL 主从复制
 - 数据恢复

##### 文件类型
 - 二进制日志索引文件(文件名后缀为.index)
 - 二进制日志文件(文件名后缀为.00000*)
   记录数据库所有的DDL和DML(除了数据查询语句select)语句事件。

##### 刷盘
```
vim /etc/my.conf
...
sync_binlog = 0
...

```
```sql
select @@sync_binlog;
```

###### sync_binlog
binlog同步参数。
该参数的有效值为0、1、N：（MySQL5.7.7及之后版本默认值为：1）

- 0:不去强制要求，由操作系统决定何时binlog写入磁盘。
- 1: 每次commit都将binlog写入磁盘，相当于是同步写入磁盘，不经过操作系统的缓存。
- N:每N个事务提交后，才将binlog写入磁盘。

#### 慢查询日志(slow log)

```sql
 show variables like "%slow%"; //查看慢查询日志相关设置  
 
 set slow_query_log='ON'; //启用慢查询  

 set global long_query_time=2; //设置成2秒 
```

#### 中继日志(relay log)
	用于主从复制。从节点读主节点的bin log，检测到ChangeData后先写入relay log，再从relay log中解析重放SQL。
#### Innodb日志(redo log , undo log)
##### redo log

```sql
# innodb 日志文件 相关配置
show variables like "%innodb_log_file%";
>
# 单个日志文件大小
innodb_log_file_size      1572864000
# 日志文件数量
innodb_log_files_in_group 2

```

![image-20240325210048467](https://gitee.com/mingzijian/resources/raw/master/picgo/2024-03/image-20240325210048467.png)

   记录做了哪些变更，用于恢复。
   一般是物理日志，记录的是物理页的修改。
   同一个事务中，可能会有多次记录，最后一个提交的事务记录会覆盖所有未提交的事务记录。
   具有幂等性。

   - redo log buffer （日志缓冲-内存）
   - redo log file （日志文件-磁盘）

###### redo log与binlog区别

| 比较     | redo log                                                     | binlog                                                       |
| -------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 文件大小 | `redo log `的大小是**固定**的。                              | `binlog `可通过**配置**参数 `max_binlog_size `设置每个` binlog `文件的大小。 |
| 实现方式 | `redo log `是 **`InnoDB `引擎层**实现的，并不是所有引擎都有。 | `binlog `是 **`Server` 层**实现的，所有引擎都可以使用 `binlog `日志 |
| 记录方式 | redo log 采用**循环写**的方式记录，当写到结尾时，会回到开头循环写日志。 | binlog通过**追加**的方式记录，当文件大小大于给定值后，后续的日志会记录到新的文件上 |
| 适用场景 | `redo log `适用于崩溃恢复(crash-safe)                        | `binlog `适用于主从复制和数据恢复                            |


###### innodb_flush_log_at_trx_commit
事务日志刷写参数。
```
vim /etc/my.conf
...
innodb_flush_log_at_trx_commit = 2
...

```

```sql
select @@innodb_flush_log_at_trx_commit;
```

该参数的有效值为0、1、2：
- 0：事务提交时不会立即将`redo log buffer`中日志写入到`os buffer`，而是依靠InnoDB主线程每秒写入`os buffer`并调用`fsync()`写入到`redo log file`中。MySQL进程崩溃就会导致数据丢失。
	优点：性能最高
	缺点：安全性最差
	
- 1：默认值。每次事务提交都会将`redo log buffer`写入`os buffer`并调用`fsync()`刷到`redo log file`中。
	优点：安全性最高
	缺点：性能最差
	
- 2：每次提交事务都会写入`os buffer`，然后是每秒调用`fsync()`将`os buffer`写入到 `redo log file`。注意：并不能保证100%每秒一定都会刷到磁盘，这要取决于进程的调度。操作系统崩溃才会导致数据丢失。
	优点：折中方案，比0安全，比1性能高
	缺点：不如0性能高，不如1安全

##### undo log
   记录变更之前的数据，用于回滚。一般是逻辑日志，记录的是数据行的修改。
   数据库事务四大特性中有一个是`原子性`，具体来说就是`原子性`是指对数据库的一系列操作，要么全部成功，要么全部失败，不可能出现部分成功的情况。
   `原子性`底层就是通过`undo log`实现的。 
   `undo log`主要记录了数据的逻辑变化，比如一条`INSERT`
语句，对应一条`DELETE`的 undo log ，对于每个`UPDATE`语句，对应一条相反的`UPDATE`的`undo log`，这样在发生错误时，就能回滚到事务之前的数据状态。

