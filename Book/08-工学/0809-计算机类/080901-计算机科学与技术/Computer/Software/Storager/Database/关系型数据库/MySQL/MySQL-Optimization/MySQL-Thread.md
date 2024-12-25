###  常规线程状态

| 状态                                     | 描述                                                         |
| ---------------------------------------- | ------------------------------------------------------------ |
| After create                             |                                                              |
| altering table                           |                                                              |
| Analyzing                                | 线程正在计算 MyISAM 表 key 分布                              |
| checking permissions                     | 线程正在检查服务器是否具有执行语句所需的权限                 |
| Checking table                           | 线程正在执行表检查操作。                                     |
| cleaning up                              | 线程已经处理了一个命令，正在准备释放内存并重置某些状态变量。 |
| committing alter table to storage engine | 服务器已完成就地 ALTER TABLE 并正在提交结果。                |
| closing tables                           | 线程正在将已更改的表数据刷新到磁盘并关闭已使用的表。这应该是一个快速操作。如果慢的话，请验证磁盘的完整性，并检查磁盘可使用量是否足够。 |
| converting HEAP to ondisk                |                                                              |
| copy to tmp table                        |                                                              |
| Copying to group table                   |                                                              |
| Copying to tmp table                     |                                                              |
| Copying to tmp table on disk             | 服务器正在将临时结果集（太大）复制到磁盘上的临时表。         |
| Creating index                           | The thread is processing `ALTER TABLE ... ENABLE KEYS` for a `MyISAM` table. |
| Creating sort index                      | 创建排序索引（临时表）。线程正在处理使用内部临时表解析的SELECT。 |
| creating table                           |                                                              |
| Creating tmp table                       | 线程正在内存或磁盘上创建临时表。如果该表是在内存中创建的，但后来被转换为磁盘上的表，则该操作期间的状态为“Copying to tmp table on disk”。 |
| deleting from main table                 |                                                              |
| deleting from reference tables           |                                                              |
| discard_or_import_tablespace             |                                                              |
| end                                      |                                                              |
| executing                                | 线程已开始执行语句。                                         |
| Execution of init_command                |                                                              |
| freeing items                            | 线程已执行完命令。该状态之后通常会进行一些清理操作。         |
| FULLTEXT initialization                  |                                                              |
| init                                     | 初始化发生在ALTER TABLE、DELETE、INSERT、SELECT或UPDATE语句之前。服务器在此状态下采取的操作包括刷新二进制日志、InnoDB日志和一些查询缓存清理操作。 |
| Killed                                   |                                                              |
| logging slow query                       |                                                              |
| login                                    |                                                              |
| manage keys                              |                                                              |
| Opening tables                           | 线程正在尝试打开表。通常会很快，除非有东西阻止打开。例如， ALTERTABLE 或 LOCKTABLE 语句可以阻止在语句完成之前打开表。另外也可以检查 table_open_cache 值是否足够大。 |
| optimizing                               | 服务器正在对查询执行初始优化。                               |
| preparing                                | 此状态发生在查询优化期间。                                   |
| preparing for alter table                |                                                              |
| Purging old relay logs                   |                                                              |
| query end                                | 查询结束状态发生在`processing`之后，但在`freeing items`状态之前。 |
| Receiving from client                    | 服务器正在从客户端读取数据包。                               |
| Removing duplicates                      |                                                              |
| removing tmp table                       |                                                              |
| rename                                   |                                                              |
| rename result table                      |                                                              |
| Reopen tables                            |                                                              |
| Repair by sorting                        |                                                              |
| Repair done                              |                                                              |
| Repair with keycache                     |                                                              |
| Rolling back                             |                                                              |
| Saving state                             |                                                              |
| Searching rows for update                |                                                              |
| Sending data                             | 线程正在读取和处理SELECT语句，并向客户端发送数据。由于在此状态期间发生的操作往往会执行大量磁盘访问（读取），因此它通常是给定查询生命周期中运行时间最长的状态。 |
| Sending to client                        | 服务器正在向客户端写入数据包。                               |
| setup                                    |                                                              |
| Sorting for group                        |                                                              |
| Sorting for order                        |                                                              |
| Sorting index                            |                                                              |
| Sorting result                           | 对于SELECT语句，这类似于创建排序索引，但对于非临时表。       |
| starting                                 | 语句开始执行的第一阶段。                                     |
| statistics                               | 服务器正在计算统计信息以制定查询执行计划。如果一个线程长时间处于这种状态，那么服务器很可能正在执行其他工作。 |
| System lock                              |                                                              |
| update                                   |                                                              |
| Updating                                 |                                                              |
| updating main table                      |                                                              |
| updating reference tables                |                                                              |
| User lock                                |                                                              |
| User sleep                               |                                                              |
| Waiting for commit lock                  | [`FLUSH TABLES WITH READ LOCK`](https://dev.mysql.com/doc/refman/5.7/en/flush.html#flush-tables-with-read-lock) 正在等待提交锁。 |
| Waiting for global read lock             | [`FLUSH TABLES WITH READ LOCK`](https://dev.mysql.com/doc/refman/5.7/en/flush.html#flush-tables-with-read-lock) 正在等待全局读锁或正在设置全局只读系统变量。 |
| Waiting for tables                       |                                                              |
| Waiting for table flush                  | 线程正在执行FLUSH TABLES，并等待所有线程关闭其表，或者线程收到一个通知，表明表的底层结构已更改，需要重新打开表以获取新结构。但是，要重新打开表，它必须等到所有其他线程都关闭了有问题的表。 |
| Waiting for *`lock_type`* lock           |                                                              |
| Waiting on cond                          |                                                              |
| Writing to net                           |                                                              |



### 参考
[general-thread-states](https://dev.mysql.com/doc/refman/5.7/en/general-thread-states.html)