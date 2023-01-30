PostgreSQL 介绍
-------------------------
PostgreSQL是一个强大的、开源的对象关系数据库系统(ORDBMS)。
经过30多年的积极开发，在可靠性、功能健壮性和性能方面赢得了很高的声誉。
PostgreSQL 开发者把它念作 post-gress-Q-L。
Slogan 是 **"世界上最先进的开源关系型数据库"**。



### 功能列表 （13.1Released）
- 数据类型
	- 基本类型（Primitives）: Integer, Numeric, String, Boolean 
	- 结构类型（Structured）: Date/Time, Array, Range, UUID 
	- 文档类型（Document）: JSON/JSONB, XML, Key-value (Hstore) 
	- 几何类型（Geometry）: Point, Line, Circle, Polygon 
	- 复合类型（Customizations）: Composite, Custom Types

- 数据完备性
	- 唯一（UNIQUE）, 非空（NOT NULL）
	- 主键（Primary Keys）
	- 外键（Foreign Keys）
	- 排除约束（Exclusion Constraints）
	- 显式锁（Explicit Locks）,咨询锁（ Advisory Locks）

- 并发/性能
	- 索引 Indexing: B-tree, Multicolumn, Expressions, Partial
	- 高级索引 Advanced Indexing: GiST, SP-Gist, KNN Gist, GIN, BRIN, Covering indexes, Bloom filters
	- 复杂的查询计划器/优化器 Sophisticated query planner / optimizer, index-only scans, multicolumn statistics
	- 事务/嵌套事务 Transactions, Nested Transactions (via savepoints)
	- 多版本并发控制 Multi-Version concurrency Control (MVCC)
	- 数据查询与建立B树索引并行化 Parallelization of read queries and building B-tree indexes
	- 表分区 Table partitioning
	- 标准事务隔离级别 All transaction isolation levels defined in the SQL standard, including Serializable
	- 表达式即时编译 Just-in-time (JIT) compilation of expressions

- 可靠/灾难恢复
	- 预写日志记录 Write-ahead Logging (WAL)
	- 复制 Replication: Asynchronous, Synchronous, Logical
	- 时间点恢复Point-in-time-recovery (PITR), active standbys
	- 表空间 Tablespaces
- 安全
	- 身份验证 Authentication: GSSAPI, SSPI, LDAP, SCRAM-SHA-256, Certificate, and more
	- 访问控制 Robust access-control system
	- 列级和行级安全性 Column and row-level security
	- 多因素认证 Multi-factor authentication with certificates and an additional method

- 可拓展
	- 函数与存储过程 Stored functions and procedures
	- 程序语言 Procedural Languages: PL/PGSQL, Perl, Python (and many more)
	- SQL/ JSON路径表达式 SQL/JSON path expressions
	- 外部数据包装器 Foreign data wrappers: connect to other databases or streams with a standard SQL interface
	- 可定制存储接口的表 Customizable storage interface for tables
	- 许多提供附加功能的扩展 Many extensions that provide additional functionality, including PostGIS

- 国际化/文字搜索
	- 支持国际字符集 Support for international character sets, e.g. through ICU collations
	- 不区分大小写和不区分重音的归类 Case-insensitive and accent-insensitive collations
	- 全文搜索 Full-text search
	

### 参考

- [关于PostgreSQL--官网](https://www.postgresql.org/about/)
- [PostgreSQL-13.1中文手册---PostgreSQL中文社区](http://www.postgres.cn/docs/13/index.html)