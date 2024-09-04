Spring Boot
-----------
Spring Boot使您可以轻松地创建独立的、生产级的、基于Spring的应用程序，您可以“just run”。

## 特性
### 独立程序
创建独立的Spring应用程序。
#### 启动失败提示
如果应用程序无法启动，注册的FailureAnalyzer将有机会提供一条专门的错误消息和解决问题的具体操作。
#### 延迟初始化

#### 自定义横幅（Banner）

#### 自定义SpringApplication

#### 流式调用的构建器 API

#### 可用性

#### 事件与监听

#### WEB环境

#### 访问应用程序参数

####  ApplicationRunner与 CommandLineRunner

#### 程序退出

#### 管理员功能

#### 程序启动跟踪

### 内嵌容器
springboot内置了servlet容器，项目可以直接打包成jar形式，使用java -jar命令运行，而不必像以前打包成war包形式放到tomcat下运行。正因为这个机制，结合Jenkins、Docker自动化运维得以实现。

### 简化构建
提供自定义的“启动器`starter`”依赖项来简化构建配置。

### 生产准备
springboot提供的actuator插件提供了大量的生产级特性，可以帮助监控和管理springBoot应用，比如健康检查、审计、统计和HTTP追踪。这在微服务中，可以通过actutor提供的端点与外部应用监控系统进行整合，比如Prometheus、DataDog来进行服务监控。

### 自动装配
尽可能地自动配置Spring和第三方库。
springboot能很方便的使用第三方包，只需做简单的配置即可使用，例如：jdbc、web-mvc等。

### 简化配置
完全没有代码生成，也不需要XML配置。

- 让编码变得简单
- 让部署变得简单
	内嵌Tomact/Jetty/Undertow (无需额外部署WAR文件)
- 让配置变得简单
	自动化配置,约定优于配置,可以完全不使用xml配置
- 让监控变得简单
	健康检查
- 让扩展变得简单
- 让依赖变得简单
- 让测试变得简单

## Spring Boot 启动过程
### 初始化
SpringApplication初始化模块，配置一些基本的环境变量、资源、监视器、构造函数
### 启动程序
具体应用启动程序的实现，包括进程监控模块、加载配置环境模块和创建上下文环境模块

**设置webApplicationType（web应用类型）**
 webApplicationType是启动流程中一个比较重要的属性，SpringBoot根据它的类型来创建Environment对象和应用上下文对象（ApplicationContext）

**准备应用上下文环境（Environment）**
 根据上一步推断的webApplicationType创建不同类型的Environment，并且将用户的profile文件读取到Environment中

**读取profile**

**创建并配置应用上下文对象（ApplicationContext）**
 根据webApplicationType创建不同实现的ApplicationContext

**刷新应用上下文对象（refresh）**
 `AbstractApplicationContext`抽象类定义了上下文对象初始化核心流程，`SpringBoot`以`BeanFactoryPostProcessor`的方式实现包扫描、自动配置，将Bean预先加载成`BeanDefinition`后并实例化

**后续处理**
 发布应用已启动事件并且调用容器中的Runner

### 自动配置

自动配置模块，该模块实现了SpringBoot的自动配置

#### 参考
 - [SpringBoot项目](https://spring.io/projects/spring-boot)
 - [SpringBoot参考文档](https://docs.spring.io/spring-boot/docs/current/reference/html)

