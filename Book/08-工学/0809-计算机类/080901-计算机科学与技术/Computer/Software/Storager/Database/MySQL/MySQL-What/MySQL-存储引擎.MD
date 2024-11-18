MySQL 存储引擎
------------------------
存储引擎是数据库的核心，对于mysql来说，存储引擎是以插件的形式运行的。

SHOW ENGINES ;(MySQL5.7)

| Engine             | Support | Comment                                                      | Transactions | XA   | Savepoints |
| ------------------ | ------- | ------------------------------------------------------------ | ------------ | ---- | ---------- |
| InnoDB             | DEFAULT | Supports transactions, row-level locking, and foreign keys   | YES          | YES  | YES        |
| MRG_MYISAM         | YES     | Collection of identical MyISAM tables                        | NO           | NO   | NO         |
| MEMORY             | YES     | Hash based, stored in memory, useful for temporary tables    | NO           | NO   | NO         |
| BLACKHOLE          | YES     | /dev/null storage engine (anything you write to it disappears) | NO           | NO   | NO         |
| MyISAM             | YES     | MyISAM storage engine                                        | NO           | NO   | NO         |
| CSV                | YES     | CSV storage engine                                           | NO           | NO   | NO         |
| ARCHIVE            | YES     | Archive storage engine                                       | NO           | NO   | NO         |
| PERFORMANCE_SCHEMA | YES     | Performance Schema                                           | NO           | NO   | NO         |
| FEDERATED          | NO      | Federated MySQL storage engine                               |              |      |            |



## 各存储引擎功能摘要

| 功能                                                   | MyISAM | Memory  | InnoDB | Archive | NDB   |
| :----------------------------------------------------- | :----- | :------ | :----- | :------ | :---- |
| B-tree indexes B树索引                                 | Yes    | Yes     | Yes    | No      | No    |
| Backup/point-in-time recovery 备份恢复                 | Yes    | Yes     | Yes    | Yes     | Yes   |
| Cluster database support 集群数据库支持                | No     | No      | No     | No      | Yes   |
| Clustered indexes 聚簇索引                             | No     | No      | Yes    | No      | No    |
| Compressed data 压缩数据                               | Yes    | No      | Yes    | Yes     | No    |
| Data caches 数据缓存                                   | No     | N/A     | Yes    | No      | Yes   |
| Encrypted data 加密数据                                | Yes    | Yes     | Yes    | Yes     | Yes   |
| Foreign key support 外键                               | No     | No      | Yes    | No      | Yes   |
| Full-text search indexes 全文搜索索引                  | Yes    | No      | Yes    | No      | No    |
| Geospatial data type support 空间数据类型支持          | Yes    | No      | Yes    | Yes     | Yes   |
| Geospatial indexing support 空间索引支持               | Yes    | No      | Yes    | No      | No    |
| Hash indexes 哈希索引                                  | No     | Yes     | No     | No      | Yes   |
| Index caches 索引缓存                                  | Yes    | N/A     | Yes    | No      | Yes   |
| Locking granularity 锁粒度                             | Table  | Table   | Row    | Row     | Row   |
| MVCC                                                   | No     | No      | Yes    | No      | No    |
| Replication support 复制支持                           | Yes    | Limited | Yes    | Yes     | Yes   |
| Storage limits 存储限制                                | 256TB  | RAM     | 64TB   | None    | 384EB |
| T-tree indexes  T树索引                                | No     | No      | No     | No      | Yes   |
| Transactions 事务                                      | No     | No      | Yes    | No      | Yes   |
| Update statistics for data dictionary 数据字典更新统计 | Yes    | Yes     | Yes    | Yes     | Yes   |





## InnoDB

## MyISAM

## Memory

## Archive

## BLACKHOLE存储引擎
存储BLACKHOLE引擎充当 “黑洞”，接受数据但将其丢弃并且不存储它。
查询总是返回空结果。
```sql
mysql> CREATE TABLE test(i INT, c CHAR(10)) ENGINE = BLACKHOLE;
Query OK, 0 rows affected (0.03 sec)

mysql> INSERT INTO test VALUES(1,'record one'),(2,'record two');
Query OK, 2 rows affected (0.00 sec)
Records: 2  Duplicates: 0  Warnings: 0

mysql> SELECT * FROM test;
Empty set (0.00 sec)
```