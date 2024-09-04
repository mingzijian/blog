MySQL join
-----------------
join: 多张表的连接

### join方式
- left join
- right join
- inner join （交集）
- outer join （并集）
- nature join
- cross join

#### nature join （自然连接）
##### using
MySQL中连接SQL语句中，`on`子句的语法格式为：
`on table1.column_name = table2.column_name`。
当模式设计对连接表的列采用了相同的命名样式时，
就可以使用 `using` 语法来简化` on `语法，
格式为：`using(column_name)`。
`using`会去除`using`指定的列，而`on`不会。
自然连接就是`using`子句的简化版。
```
SELECT * FROM table1 JOIN table2 USING(column_name) 

SELECT * FROM table1 NATURAL JOIN table2
```

#### cross join （交叉连接，笛卡尔积）
`cross join` 结果集是两个关联表的行的乘积。
通常，如果每个表分别具有`n`行和`m`行，则结果集将具有`n*m`行。

### join算法
- Nested Loop Join
- Hash Join
- Sort Merge Join

MySQL 8.0.18之前使用`Nested Loop Join`算法
MySQL 8.0.18及之后使用`Hash Join`算法（针对任何有等价连接条件且没有索引可以应用于任何连接条件的查询）

#### NLJ (Nested-Loop Join) 嵌套循环连接

##### SNL (Simple Nested-Loop)
SNLJ就是两层循环全量扫描连接的两张表，得到符合条件的两条记录则输出，这也就是让两张表做笛卡尔积，比较次数是R * S，是比较暴力的算法，会比较耗时。
```
---------------------------------------
Table   Join Type
t1      range
t2      ref
t3      ALL
---------------------------------------
for each row in t1 matching range {
  for each row in t2 matching reference key {
    for each row in t3 {
      if row satisfies join conditions, send to client
    }
  }
}
```

##### INL (Index Nested-Loop) 索引嵌套循环

使用`索引`

##### BNL (Block Nested-Loop) 块嵌套循环
从MySQL8.0.20开始，对块嵌套循环的支持被删除，
服务器在以前使用块嵌套循环的地方使用哈希连接。

```
for each row in t1 matching range {
  for each row in t2 matching reference key {
    store used columns from t1, t2 in join buffer
    if buffer is full {
      for each row in t3 {
        for each t1, t2 combination in join buffer {
          if row satisfies join conditions, send to client
        }
      }
      empty join buffer
    }
  }
}

if buffer is not empty {
  for each row in t3 {
    for each t1, t2 combination in join buffer {
      if row satisfies join conditions, send to client
    }
  }
}
```

#### Hash Join 哈希连接
将两表中的小表（称S）作为hash表，然后去扫描另一个表(称M)的每一行数据，用得出来的行数据根据连接条件去映射建立的hash表，hash表是放在内存中的，这样可以很快的得到对应的S表与M表相匹配的行。

#### Sort Merge Join 排序合并连接

首先对两个表按照关联的字段进行排序，分别从两个表中取出一行数据进行匹配，如果合适放入结果集；
不匹配将较小的那行丢掉继续匹配另一个表的下一行，依次处理直到将两表的数据取完。
`merge join`的很大一部分开销花在排序上，也是同等条件下差于`hash join`的一个主要原因。

### 参考
- [Nested-Loop Join 算法](https://dev.mysql.com/doc/refman/8.0/en/nested-loop-joins.html)
- [Hash Join 优化](https://dev.mysql.com/doc/refman/8.0/en/hash-joins.html)


