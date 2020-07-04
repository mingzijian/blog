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
 - ROW
 - MIXED
 ```sql
 show variables like "%log_bin%";//查看binlog相关设置
 ```
 
#### 使用场景
 - MySQL 主从复制
 - 数据恢复

#### 文件类型
 - 二进制日志索引文件(文件名后缀为.index)
 - 二进制日志文件(文件名后缀为.00000*)
   记录数据库所有的DDL和DML(除了数据查询语句select)语句事件。

#### 慢查询日志(slow log)

```sql
 show variables like "%slow%"; //查看慢查询日志相关设置  
 
 set slow_query_log='ON'; //启用慢查询  

 set global long_query_time=2; //设置成2秒 
```
 
#### 中继日志(relay log)
	用于主从复制。从节点读主节点的bin log，检测到ChangeData后先写入relay log，再从relay log中解析重放SQL。
#### Innodb日志(redo log , undo log)
 - redo log
   记录做了哪些变更，用于恢复。一般是物理日志，记录的是物理页的修改。同一个事务中，可能会有多次记录，最后一个提交的事务记录会覆盖所有未提交的事务记录。具有幂等性。
   - redo log buffer
   - redo log file
 - undo log
   记录变更之前的数据，用于回滚。一般是逻辑日志，记录的是数据行的修改。

