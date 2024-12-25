MySQL 临时表
---------------------

### 两种临时表

- 内部临时表
- 外部临时表

#### 内部临时表
在某些情况下，服务器在处理语句时创建内部临时表。
用户无法直接控制何时发生这种情况。
服务器（V5.7）在以下情况下创建临时表：

- Evaluation of UNION statements, with some exceptions described later.
UNION语句的求值，后面将介绍一些例外情况。
- Evaluation of some views, such those that use the TEMPTABLE algorithm, UNION, or aggregation.
处理某些视图，使用了临时表算法
- Evaluation of derived tables (see Section 13.2.11.8, “Derived Tables”).
处理派生表

- Evaluation of common table expressions (see Section 13.2.15, “WITH (Common Table Expressions)”).
公用表表达式

- Tables created for subquery or semijoin materialization (see Section 8.2.2, “Optimizing Subqueries, Derived Tables, View References, and Common Table Expressions”).
为子查询或半连接物化创建的表

- Evaluation of statements that contain an ORDER BY clause and a different GROUP BY clause, or for which the ORDER BY or GROUP BY contains columns from tables other than the first table in the join queue.
包含ORDER BY子句和其他GROUP BY子句的语句的计算，或者ORDER BY或GROUP BY包含来自连接队列中第一个表以外的表的列

- Evaluation of DISTINCT combined with ORDER BY may require a temporary table.
DISTINCT 和 ORDER BY 联合使用可能需要临时表

- For queries that use the SQL_SMALL_RESULT modifier, MySQL uses an in-memory temporary table, unless the query also contains elements (described later) that require on-disk storage.
对于使用SQL_SMALL_RESULT 修饰符的查询，MySQL使用内存中的临时表，除非查询还包含需要磁盘存储的元素

- To evaluate INSERT ... SELECT statements that select from and insert into the same table, MySQL creates an internal temporary table to hold the rows from the SELECT, then inserts those rows into the target table. See Section 13.2.6.1, “INSERT ... SELECT Statement”.
insert ....table select....table语句需要临时表

- Evaluation of multiple-table UPDATE statements.
多表更新

- Evaluation of GROUP_CONCAT() or COUNT(DISTINCT) expressions.
GROUP_CONCAT() 或者 COUNT(DISTINCT)  表达式

- Evaluation of window functions (see Section 12.21, “Window Functions”) uses temporary tables as necessary.
窗口函数

确定语句是否使用临时表，可以使用explain命令查看extra栏的输出是否为use temporary. [对于派生表和物化表不一定显示这样]

#### 外部临时表
外部临时表也可称为会话临时表，这种临时表只对当前用户可见，它的数据和表结构都存储在内存中。当前会话中断或结束后，数据表数据就会丢失，MySQL 会自动删除表并释放其所占空间。

##### 创建临时表
CREATE TEMPORARY TABLE <表名>...
WITH cte AS (SELECT 1 AS col_a, 2 AS col_b)
SELECT * FROM cte AS t1 JOIN cte AS t2;

##### 查看临时表
SHOW CREATE TABLE <表名>; 

##### 重命名临时表
ALTER TABLE old_name RENAME new_name;

##### 删除临时表
DROP TABLE <表名>;



### 参考
- https://dev.mysql.com/doc/refman/5.7/en/internal-temporary-tables.html
- https://dev.mysql.com/doc/refman/8.0/en/internal-temporary-tables.html
- https://dev.mysql.com/doc/refman/8.0/en/temporary-table-problems.html