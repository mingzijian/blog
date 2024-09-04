Spring Cloud Alibaba
---------------------------------

为分布式应用开发提供一站式解决方案。

### 特性

- 流控与服务降级
- 服务注册与发现
- 分布式配置
- 事件驱动
- 消息总线
- 分布式事务
- Dubbo RPC



## Spring Cloud Alibaba 介绍

Spring Cloud Alibaba 是对 Spring Cloud 的标准实现。

![spring-cloud-alibaba](https://gitee.com/mingzijian/resources/raw/master/picgo/2024-03/spring-cloud-alibaba-img-ca9c0e5c600bfe0c3887ead08849a03c.png)



## Spring Cloud Alibaba 最佳实践

![项目结构示意图](https://gitee.com/mingzijian/resources/raw/master/picgo/2024-03/1-c85476f8de6c2534f2aad23bbc798fb9.png)

### Nacos :  服务注册和配置中心

[Nacos](https://nacos.io/docs/latest/what-is-nacos/) /nɑ:kəʊs/ 是 Dynamic **Na**ming and **Co**nfiguration **S**ervice 的首字母简称，一个更易于构建云原生应用的动态服务发现、配置管理和服务管理平台。

![nacos_arch.jpg](https://gitee.com/mingzijian/resources/raw/master/picgo/2024-03/1561217892717-1418fb9b-7faa-4324-87b9-f1740329f564.jpeg)

### SCG： Spring Cloud Gateway （网关）

#### 特性

- 基于 Spring 框架和 Spring Boot 构建
- 能够匹配任何请求属性上的路由
- 针对路由定制的断言和过滤器
- 断路器集成
- Spring Cloud Discovery 客户端集成
- 易于编写的断言和过滤器
- 请求速率限制
- 路径重写（重定向）

### Sentinel：熔断限流

Sentinel 是面向分布式、多语言异构化服务架构的**流量治理组件**，主要以流量为切入点，从流量路由、流量控制、流量整形、熔断降级、系统自适应过载保护、热点流量防护等多个维度来帮助开发者**保障微服务的稳定性**。

#### Sentinel 架构

![arch overview](https://gitee.com/mingzijian/resources/raw/master/picgo/2024-03/sentinel-slot-chain-architecture.png)

#### Sentinel 特性

- *丰富的应用场景*： Sentinel 承接了阿里巴巴近 10 年的双十一大促流量的核心场景，例如秒杀（即突发流量控制在系统容量可以承受的范围）、消息削峰填谷、实时熔断下游不可用应用等。
- *完备的实时监控*： Sentinel 同时提供实时的监控功能。您可以在控制台中看到接入应用的单台机器秒级数据，甚至 500 台以下规模的集群的汇总运行情况。
- *广泛的开源生态*： Sentinel 提供开箱即用的与其它开源框架/库的整合模块，例如与 Spring Cloud、Dubbo、gRPC 的整合。您只需要引入相应的依赖并进行简单的配置即可快速地接入 Sentinel。
- *完善的 SPI 扩展点*： Sentinel 提供简单易用、完善的 SPI 扩展点。您可以通过实现扩展点，快速的定制逻辑。例如定制规则管理、适配数据源等。

#### Sentinel 的主要工作机制如下

- 对主流框架提供适配或者显示的 API，来定义需要保护的资源，并提供设施对资源进行实时统计和调用链路分析。
- 根据预设的规则，结合对资源的实时统计信息，对流量进行控制。同时，Sentinel 提供开放的接口，方便您定义及改变规则。
- Sentinel 提供实时的监控系统，方便您快速了解目前系统的状态。

#### Sentinel SPI 拓展

![Slot Chain SPI](https://gitee.com/mingzijian/resources/raw/master/picgo/2024-03/46783631-93324d00-cd5d-11e8-8ad1-a802bcc8f9c9.png)



### Seata：分布式事务



### RocketMQ：消息队列





### 参考

 - [spring-cloud-alibaba](https://spring.io/projects/spring-cloud-alibaba)
 - [Sentinel 介绍](https://sentinelguard.io/zh-cn/docs/introduction.html)