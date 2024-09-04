[TOC]

# InnoDB 限制

## 单表最大字段（列）数

MySQL 对每个表有 4096 列的硬限制，但对于给定的表，有效最大值可能会更少。

MySQL8.0(innodb) 支持最大字段个数：1017

A table can contain a maximum of 1017 columns. Virtual generated columns are included in this limit.

```sql
select version();
8.0.25

create table test_table_max_column_1018
(
  column_0001 int not null primary key,
  column_0002 int ,
  column_0003 int ,
  column_0004 int ,
  column_0005 int ,
  ...
  column_1016 int,
  column_1017 int,
  column_1018 int
);
Too many columns


create table test_table_max_column_1017
(
  column_0001 int not null primary key,
  column_0002 int ,
  column_0003 int ,
  column_0004 int ,
  column_0005 int ,
  ...
  column_1016 int,
  column_1017 int
);
completed in 247 ms
```

### 单表索引数限制
单表支持最大二级索引数：64
A table can contain a maximum of 64 secondary indexes.



### 多列索引最多允许有 16 列
超过限制将返回错误。

  ```sql
  ERROR 1070 (42000): Too many key parts specified; max 16 parts allowed
  ```

### 单行数据量限制

MySQL 表的内部表示也具有 65,535 字节的最大行大小限制。

 [`BLOB`](https://dev.mysql.com/doc/refman/5.7/en/blob.html)列 [`TEXT`](https://dev.mysql.com/doc/refman/5.7/en/blob.html)仅对行大小限制贡献 9 到 12 个字节，因为它们的内容与行的其余部分分开存储。

对于 4KB、8KB、16KB 和 32KB 页面大小，最大行大小（不包括存储在页外的任何可变长度列）**略小于页的一半**。
例如，默认 innodb_page_size = 16KB 的最大行大小约为 8KB。
但是，对于InnoDB 64KB 的页面大小，最大行大小约为 16KB。
LONGBLOB和 LONGTEXT 列必须小于 4GB，并且总行大小（包括BLOB和 TEXT列）必须小于 4GB。

如果一行的长度小于半页，则所有行都存储在本地页内。如果超过半页，则选择可变长度列用于外部页外存储，直到该行适合半页，如第 14.12.2 节 “文件空间管理”中所述。


# 参考
 - [innodb limits](https://dev.mysql.com/doc/refman/8.0/en/innodb-limits.html)