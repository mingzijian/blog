Spring 事务
-----------------

### Spring事务管理方式

1.编程式事务：在代码中硬编码（不推荐使用）。

2.声明式事务：在配置文件中配置（推荐使用），分为基于XML的声明式事务和基于注解的声明式事务。

### Spring事务隔离级别

在TransactionDefinition接口中定义了五个表示隔离级别的常量：

 - ISOLATION_DEFAULT：
	使用后端数据库默认的隔离级别，Mysql默认采用的REPEATABLE_READ隔离级别；Oracle默认采用的READ_COMMITTED隔离级别。

 - ISOLATION_READ_UNCOMMITTED：
	最低的隔离级别，允许读取尚未提交的数据变更，可能会导致脏读、幻读或不可重复读。

 - ISOLATION_READ_COMMITTED：
	允许读取并发事务已经提交的数据，可以阻止脏读，但是幻读或不可重复读仍有可能发生

 - ISOLATION_REPEATABLE_READ：
	对同一字段的多次读取结果都是一致的，除非数据是被本身事务自己所修改，可以阻止脏读和不可重复读，但幻读仍有可能发生。

 - ISOLATION_SERIALIZABLE：
	最高的隔离级别，完全服从ACID的隔离级别。所有的事务依次逐个执行，这样事务之间就完全不可能产生干扰，也就是说，该级别可以防止脏读、不可重复读以及幻读。但是这将严重影响程序的性能。通常情况下也不会用到该级别。

### Spring事务传播

在TransactionDefinition接口中定义了八个表示事务传播行为的常量。

支持当前事务的情况：

 - PROPAGATION_REQUIRED：
    如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。

 - PROPAGATION_SUPPORTS： 
	如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行。

 - PROPAGATION_MANDATORY： 
	如果当前存在事务，则加入该事务；如果当前没有事务，则抛出异常。（mandatory：强制性）。

不支持当前事务的情况：

 - PROPAGATION_REQUIRES_NEW： 
	创建一个新的事务，如果当前存在事务，则把当前事务挂起。

 - PROPAGATION_NOT_SUPPORTED： 
	以非事务方式运行，如果当前存在事务，则把当前事务挂起。

 - PROPAGATION_NEVER： 
	以非事务方式运行，如果当前存在事务，则抛出异常。

其他情况：

 - PROPAGATION_NESTED：
	 如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；如果当前没有事务，则该取值等价于PROPAGATION_REQUIRED。

#### 事务传播原理

Spring AOP + ThreadLocal + rollbackFor

1，Spring AOP 感知 @Transaction 注解，判断开启事务并注入目标方法

2，将当前事务属性存入 ThreadLocal

3，出现异常时匹配 rollbackFor，从 ThreadLocal 取出事务执行回滚

#### 事务传播不生效情况（加了@Transaction 注解但事务回滚不符合预期）

##### 1，事务方法未被 Spring AOP 感知

​		 案例：同一个类 `ClassA` 中的方法 `nonTransactionMethod()` 调用 `transactionMethod()`

​		 原因：虽然 `transactionMethod()` 加了注解，但是 `nonTransactionMethod()` 没加注解，且同一个类中的方法调用不使用 Spring 代理不会触发 AOP。即 Spring 不会将事务注入 `transactionMethod()`

​		 方案：

| 方案  | 说明                                                         | 场景分析                                        |
| ----- | ------------------------------------------------------------ | ----------------------------------------------- |
| 方案1 | 将 `nonTransactionMethod()` 也加上事务 `@Transaction` 注解   | 要求：业务上允许`nonTransactionMethod()` 加事务 |
| 方案2 | 将 `transactionMethod()`  挪至另外一个类 `ClassB` 中, 将 `ClassB classB` 注入 `classA` ，通过 `classB.transactionMethod()` 调用，触发 AOP 注入事务 | 代码挪动成本较高，评估业务模型是否合理          |
| 方案3 | 将`ClassA classA` 注入自身 `classA`  ，通过 `classA.transactionMethod()` 调用，触发 AOP 注入事务 | 骚操作，低成本                                  |

   

##### 2，事务方法不在同一个 Thread

异步调用的方法不在同一个 Thread 中，无法通过 ThreadLocal 共享信息，即无法传递事务。考虑使用分布式事务解决方案。

##### 3，事务方法异常未命中 rollbackFor

 Spring 默认的触发事务回滚异常类型为 RuntimeException 。使用 `@Transaction` 注解注入事务时，建议指定 rollbackFor 。