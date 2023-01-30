[TOC]

# InnoDB 限制

## 单表最大字段（列）数

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


# 参考
 - [innodb limits](https://dev.mysql.com/doc/refman/8.0/en/innodb-limits.html)