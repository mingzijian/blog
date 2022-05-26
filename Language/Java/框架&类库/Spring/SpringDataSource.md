# DataSource



# DataSource 配置

## 数据库常用配置

| 配置                                                | 描述                                                         | 建议     |
| --------------------------------------------------- | ------------------------------------------------------------ | -------- |
| spring.datasource.validation-query                  | 在连接池里的连接返回给调用者或连接池时，要执行的验证 SQL 查询 | SELECT 1 |
| spring.datasource.driverClassName                   | 驱动的全限定类名。默认根据 URL 自动检测。                    |          |
| spring.datasource.url                               | 数据库的 JDBC URL                                            |          |
| spring.datasource.username                          | 数据库的登录用户名                                           |          |
| spring.datasource.password                          | 数据库的登录密码                                             |          |
| spring.datasource.test-on-borrow                    | 从连接池中借用连接时是否要进行测试                           |          |
| spring.datasource.max-wait                          | 连接池在等待返回连接时，最长等待多少毫秒再抛出异常           |          |
| spring.datasource.max-active                        | 连接池中的最大活跃连接数                                     |          |
| spring.datasource.test-while-idle                   | 在连接空闲时是否要进行测试                                   |          |
| spring.datasource.test-on-return                    | 在将连接归还到连接池时是否要进行测试                         |          |
| spring.datasource.validation-interval               | 执行连接验证的间隔时间，单位为毫秒                           |          |
| spring.datasource.time-between-eviction-runs-millis | 在两次空闲连接验证、弃用连接清理和空闲池大小调整之间睡眠的毫秒数 |          |
| spring.datasource.min-idle                          | 连接池里始终应该保持的最小连接数(用于DBCP和Tomcat连接池)     |          |
| spring.datasource.max-idle                          | 连接池中的最大空闲连接数                                     |          |
| spring.datasource.initial-size                      | 在连接池启动时要建立的连接数                                 |          |
| spring.datasource.remove-abandoned-timeout          | 连接在多少秒后应该考虑弃用                                   |          |
| spring.datasource.remove-abandoned                  | 被弃用的连接在到达弃用超时后是否应该被移除                   |          |
| spring.datasource.default-transaction-isolation     | 连接的默认事务隔离级别                                       |          |
| spring.datasource.min-evictable-idle-time-millis    | 一个空闲连接被空闲连接释放器（如果存在的话）优雅地释放前，最短会在连接池里停 留多少时间。 |          |



## 其他配置 

| 配置                                             | 描述                                                         | 建议 |
| ------------------------------------------------ | ------------------------------------------------------------ | ---- |
| spring.datasource.name                           | 数据源的名称                                                 |      |
| spring.datasource.username                       | 数据库的登录用户名                                           |      |
| spring.datasource.password                       | 数据库的登录密码                                             |      |
| spring.datasource.url                            | 数据库的 JDBC URL                                            |      |
| spring.datasource.driver-class-name              | JDBC 驱动的全限定类名。默认根据 URL 自动检测                 |      |
| spring.datasource.pool-name                      | 连接池名称                                                   |      |
| spring.datasource.max-active                     | 连接池中的最大活跃连接数                                     |      |
| spring.datasource.connection-timeout             | 连接超时（单位毫秒）                                         |      |
| spring.datasource.max-age                        | 连接池中连接的最长寿命                                       |      |
| spring.datasource.max-idle                       | 连接池中的最大空闲连接数                                     |      |
| spring.datasource.max-lifetime                   | 连接池中连接的最长寿命（单位为毫秒）                         |      |
| spring.datasource.max-open-prepared-statements   | 开启状态的 PreparedStatement 的数量上限                      |      |
| spring.datasource.max-wait                       | 连接池在等待返回连接时，最长等待多少毫秒再抛出异常           |      |
| spring.datasource.maximum-pool-size              | 连接池能达到的最大规模，包含空闲连接的数量和使用中的连接数量 |      |
| spring.datasource.min-evictable-idle-time-millis | 一个空闲连接被空闲连接释放器（如果存在的话）优雅地释放前，最短会在连接池里停 留多少时间 |      |
| spring.datasource.min-idle                       | 连接池里始终应该保持的最小连接数。（用于 DBCP 和 Tomcat 连接池） |      |
| spring.datasource.minimum-idle                   | HikariCP 试图在连接池里维持的最小空闲连接数                  |      |
| spring.datasource.alternate-username-allowed     | 是否允许其它用户名                                           |      |
| spring.datasource.auto-commit                    | 更新操作是否自动提交                                         |      |
| spring.datasource.abandon-when-percentage-full   | 一个百分比形势的阈值，超过该阈值则关闭并报告被弃用的连接     |      |
| spring.datasource.catalog                        | 默认的 Catalog 名称                                          |      |
| spring.datasource.commit-on-return               | 在连接归还时，连接池是否要提交挂起的事务                     |      |
| spring.datasource.connection-init-sql            | 在所有新连接创建时都会执行的 SQL 语句，该语句会在连接加入连接池前执行 |      |
| spring.datasource.connection-init-sqls           | 在物理连接第一次创建时执行的 SQL 语句列表（用于 DBCP 连接池） |      |
| spring.datasource.connection-properties.[key]    | 设置创建连接时使用的属性（用于 DBCP 连接池）                 |      |
| spring.datasource.continue-on-error              | 初始化数据库时发生错误不要终止（默认值： false）             |      |
| spring.datasource.data                           | 指向数据（数据库操纵语言，Data Manipulation Language，DML）脚本资源的引用 |      |
| spring.datasource.data-source-class-name         | 用于获取连接的数据源的全限定类名                             |      |
| spring.datasource.data-source-jndi               | 用于获取连接的数据源的 JNDI 位置                             |      |
| spring.datasource.data-source-properties.[key]   | 设置创建数据源时使用的属性（用于 Hikari 连接池）             |      |
| spring.datasource.db-properties                  | 设置创建数据源时使用的属性（用于 Tomcat 连接池）             |      |
| spring.datasource.default-auto-commit            | 连接上的操作是否自动提交                                     |      |
| spring.datasource.default-catalog                | 连接的默认 Catalog                                           |      |
| spring.datasource.default-read-only              | 连接的默认只读状态                                           |      |
| spring.datasource.default-transaction-isolation  | 连接的默认事务隔离级别                                       |      |
| spring.datasource.fair-queue                     | 是否以 FIFO 方式返回连接                                     |      |
| spring.datasource.health-check-properties.[key]  | 设置要纳入健康检查的属性（用于 Hikari 连接池）               |      |
| spring.datasource.idle-timeout                   | 连接池中的连接能保持闲置状态的最长时间，单位为毫秒。（默认值： 10 ） |      |
|   spring.datasource.ignore-exception-on-pre-load | 初始化数据库连接池时是否要忽略连接                                           |      |
|spring.datasource.init-sql|在连接第一次创建时运行的自定义查询| |
|spring.datasource.initial-size |在连接池启动时要建立的连接数||
|spring.datasource.initialization-fail-fast |在连接池创建时，如果达不到最小连接数是否要抛出异常（默认值： true ）||
|spring.datasource.initialize |使用 data.sql 初始化数据库（默认值： true ）||
|spring.datasource.isolate-internal-queries |是否要隔离内部请求（默认值： false ）||
|spring.datasource.jdbc-interceptors| 一个分号分隔的类名列表，这些类都扩展了 JdbcInterceptor 类这些拦截器会插入 java.sql.Connection 象的操作链里（用于 Tomcat 连接池）||
|spring.datasource.jmx-enabled| 开启 JMX 支持（如果底层连接池提供该功能的话）（默认值： false）||
|spring.datasource.jndi-name| 数据源的 JNDI 位置。设置了该属性则忽略类、URL、用户名和密码属性||
|spring.datasource.leak-detection-threshold| 用来检测 Hikari 连接池连接泄露的阈值，单位为毫秒||
|spring.datasource.log-abandoned| 是否针对弃用语句或连接的应用程序代码记录下跟踪栈用于 DBCP 连接池（默认值： false ）||
|spring.datasource.log-validation-errors| 在使用 Tomcat 连接池时是否要记录验证错误||
|spring.datasource.login-timeout| 连接数据库的超时时间（单位为秒）||
|spring.datasource.num-tests-per-eviction-run |空闲对象释放器线程（如果存在的话）每次运行时要检查的对象数||
|spring.datasource.platform| 在 Schema 资源（schema-${platform}.sql）里要使用的平台（默认值： all ）||
|spring.datasource.pool-prepared-statements| 是否要将 Statement 放在池里||
|spring.datasource.propagate-interrupt-state| 对于等待连接的中断线程，是否要传播中断状态||
|spring.datasource.read-only| 在使用 Hikari 连接池时将数据源设置为只读||
|spring.datasource.register-mbeans| Hikari 连接池是否要注册 JMX MBean||
|spring.datasource.remove-abandoned| 被弃用的连接在到达弃用超时后是否应该被移除||
|spring.datasource.remove-abandoned-timeout| 连接在多少秒后应该考虑弃用||
|spring.datasource.rollback-on-return| 在连接归还连接池时，是否要回滚挂起的事务||
|spring.datasource.schema| Schema（数据定义语言，Data Definition Language，DDL）脚本资源的引用||
|spring.datasource.separator| SQL 初始化脚本里的语句分割符。（默认值： ; ）||
|spring.datasource.sql-script-encoding| SQL 脚本的编码||
|spring.datasource.suspect-timeout| 在记录一个疑似弃用连接前要等待多少秒||
|spring.datasource.test-on-borrow| 从连接池中借用连接时是否要进行测试||
|spring.datasource.test-on-connect| 在建立连接时是否要进行测试||
|spring.datasource.test-on-return| 在将连接归还到连接池时是否要进行测试||
|spring.datasource.test-while-idle| 在连接空闲时是否要进行测试||
|spring.datasource.time-between-eviction-runs-millis| 在两次空闲连接验证、弃用连接清理和空闲池大小调整之间睡眠的毫秒数||
|spring.datasource.transaction-isolation| 在使用 Hikari 连接池时设置默认事务隔离级别||
|spring.datasource.use-disposable-connection-facade| 连接是否要用一个门面（facade）封装起来，在调用了 Connection.close() 后就不能 再使用这个连接了||
|spring.datasource.use-equals| 在比较方法名时是否使用 String.equals() 来代替 == ||
|spring.datasource.use-lock| 在操作连接对象时是否要加锁||
|spring.datasource.validation-interval| 执行连接验证的间隔时间，单位为毫秒||
|spring.datasource.validation-query| 在连接池里的连接返回给调用者或连接池时，要执行的验证 SQL 查询||
|spring.datasource.validation-query-timeout| 在连接验证查询执行失败前等待的超时时间，单位为秒||
|spring.datasource.validation-timeout| 在连接验证失败前等待的超时时间，单位为秒（用于 Hikari 连接池）||
|spring.datasource.validator-class-name |可选验证器类的全限定类名，用于执行测试查询||
|spring.datasource.xa.data-source-class-name| XA 数据源的全限定类名| |
|spring.datasource.xa.properties| 要传递给 XA 数据源的属性||
|spring.datasource.allow-pool-suspension| 是否允许池暂停（pool suspension）。在开启池暂停后会有性能会受到一定影响，除非你 真的需要这个功能（例如在冗余的系统下），否则不要开启它。该属性只在使用 Hikari 数 据库连接池时有用。（默认值： false 。）||


https://druid.apache.org/docs/latest/configuration/index.html



