Spring Projects
------------------------
从小开始，按需加载。Spring 是模块化设计的。



[TOC]

#### Spring Boot
	独立创建，“just run”。
##### 特性
  - 自动配置
  - 内置容器
  - 健康检查

#### Spring Framework
	基础架构支持，综合编程与配置。
##### 核心技术
 - dependency injection, 
   DI，依赖注入。
 - events,
   事件。观察者模式，事件驱动。
 - resources, 
   资源。
 - i18n, 
   internationalization，即国际化。首字符`i` + `18`位中间字符 + 末字符`n`
 - validation, 
   验证。
 - data binding, 
   数据绑定。
 - type conversion, 
   类型转换。
 - SpEL, 
   Spring Expression Language，即Spring表达式语言，是比JSP的EL更强大的一种表达式语言。 
    用法：
     - `@Value` 注解形式
     - `<bean>` xml配置
     - `Expression` 对象
 - AOP.
   Aspect Oriented Programming，即面向切面编程。
#### Spring Data
##### JDBC
##### LDAP
##### MongoDB
##### Redis
	- RedisTemplate
	- 集群模式，哨兵模式
	- JDK, String, JSON and Spring Object/XML mapping serializers.
##### R2DBC
##### REST
##### Apache
##### Pivotal GemFire
##### Couchbase
##### Elasticssearch
##### Envers
##### Neo5J
##### Hadoop

#### Spring Cloud
##### Spring Cloud Azure
##### Spring Cloud Alibaba
为分布式应用开发提供一站式解决方案。

###### 特性
 - 流量控制和服务降级
   Sentinel
 - 服务注册和发现
   Nacos
 - 分布式配置
   Nacos
 - 事件驱动
   Spring Cloud Stream RocketMQ Binder
 - 消息总线
   Spring Cloud Bus RocketMQ
 -  分布式事务
   Seata
 -  Dubbo RPC
 -  阿里云OSS

##### Spring Cloud Bus
使用轻量级消息代理将分布式系统的节点连接起来。(AMQP and Kafka)

##### Spring Cloud for Amazon Web Services

#### Spring Cloud Data Flow
#### Spring Security
##### Spring Security Kerberos
##### ~~Spring Security OAuth~~

##### Spring Security SAML
#### Spring Session

#### Spring Integration

#### Spring HATEOAS
#### Spring REST Docs
#### Spring Batch
#### Spring AMQP

	Spring AMQP项目将Spring的核心概念应用于基于AMQP的消息传递解决方案的开发。AMQP，即Advanced Message Queuing Protocol，一个提供统一消息服务的应用层标准高级消息队列协议，是应用层协议的一个开放标准，为面向消息的中间件设计。
#### Spring for Android
#### Spring CredHub
#### Spring Flo
一个JavaScript库，它为管道和简单图形提供了一个基本的可嵌入H5可视化构建器。
这个库被用作Spring云数据流中流式构建器的基础。

Flo包括集成流设计器的所有基本元素，如连接器、控制节点、选项板、状态转换和图形拓扑重要的是，它有一个文本shell、DSL支持和一个图形画布，用于创建和查看全面的工作流。

##### 特性
 - 使使用DSL、图形画布或两者同时创建、管理和监视流管道
 - 通过DSL编写管道，并提供内容辅助和自动完成
 - 查看分布式部署中模块的可视化表示
 - 在GUI中使用自动调整和网格布局功能，以更轻松，更高效地组织管道

#### Spring for Apache Kafka
#### Spring LDAP
LDAP （Lightweight Directory Access Protocol） 轻型目录访问协议

#### Spring Mobile
#### Spring Roo
#### Spring Shell
用以轻松构建一个功能齐全的Shell（又称命令行）应用程序。

#### Spring Statemachine
#### Spring Vault
#### Spring Web Flow
#### Spring Web Services

#### 参考
 - https://spring.io/projects
